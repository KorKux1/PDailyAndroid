package co.edu.icesi.pdailyandroid.viewcontrollers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import co.edu.icesi.pdailyandroid.DashBoard;
import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.app.App;
import co.edu.icesi.pdailyandroid.model.dto.FoodScheduleDTO;
import co.edu.icesi.pdailyandroid.model.dto.ScheduleTimeDTO;
import co.edu.icesi.pdailyandroid.receivers.broadcast.AlarmReceiver;
import co.edu.icesi.pdailyandroid.misc.dialogs.HourDialog;
import co.edu.icesi.pdailyandroid.services.SessionManager;
import co.edu.icesi.pdailyandroid.util.DateUtils;
import co.edu.icesi.pdailyandroid.util.PendingIntentUtils;


public class FoodFragment extends Fragment implements View.OnClickListener, HourDialog.OnHourChoose {

    public static final int ALARM_BREAKFAST = 0;
    public static final int ALARM_LUNCH = 1;
    public static final int ALARM_DINNER = 2;
    private AlarmManager alarmMgr;
    private Intent breakfastIntent;
    private Intent lunchIntent;
    private Intent dinnerIntent;

    private SessionManager sessionManager;

    TextView breakfast_hour;
    TextView lunch_hour;
    TextView dinner_hour;

    public FoodFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sessionManager = new SessionManager(getActivity().getApplicationContext());

        View root = inflater.inflate(R.layout.fragment_food, container, false);

        breakfast_hour = root.findViewById(R.id.breakfast_hour);
        lunch_hour = root.findViewById(R.id.lunch_hour);
        dinner_hour = root.findViewById(R.id.dinner_hour);

        breakfast_hour.setText(PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("FOOD01", breakfast_hour.getText().toString()));
        lunch_hour.setText(PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("FOOD02", lunch_hour.getText().toString()));
        dinner_hour.setText(PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("FOOD03", dinner_hour.getText().toString()));

        breakfast_hour.setOnClickListener(this);
        lunch_hour.setOnClickListener(this);
        dinner_hour.setOnClickListener(this);

        alarmMgr = (AlarmManager) App.getAppContext().getSystemService(Context.ALARM_SERVICE);
        breakfastIntent = new Intent(App.getAppContext(), AlarmReceiver.class);
        breakfastIntent.putExtra("type", "FOOD01");
        lunchIntent = new Intent(App.getAppContext(), AlarmReceiver.class);
        lunchIntent.putExtra("type", "FOOD02");
        dinnerIntent = new Intent(App.getAppContext(), AlarmReceiver.class);
        dinnerIntent.putExtra("type", "FOOD03");

        if (!PendingIntentUtils.isPendingIntentRegistered(ALARM_BREAKFAST, breakfastIntent)) {
            Calendar breakfastCalendar = getCalendarOfHour(breakfast_hour.getText().toString());
            PendingIntent breakfastPendingIntent = PendingIntent.getBroadcast(App.getAppContext(), ALARM_BREAKFAST, breakfastIntent, 0);
            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, breakfastCalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, breakfastPendingIntent);
        }

        if (!PendingIntentUtils.isPendingIntentRegistered(ALARM_LUNCH, lunchIntent)) {
            Calendar lunchCalendar = getCalendarOfHour(lunch_hour.getText().toString());
            PendingIntent lunchPendingIntent = PendingIntent.getBroadcast(App.getAppContext(), ALARM_LUNCH, lunchIntent, 0);
            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, lunchCalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, lunchPendingIntent);
        }

        if (!PendingIntentUtils.isPendingIntentRegistered(ALARM_DINNER, dinnerIntent)) {
            Calendar dinnerCalendar = getCalendarOfHour(dinner_hour.getText().toString());
            PendingIntent dinnerPendingIntent = PendingIntent.getBroadcast(App.getAppContext(), ALARM_DINNER, dinnerIntent, 0);
            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, dinnerCalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, dinnerPendingIntent);
        }

        return root;
    }

    private void setupFoodAlarms(FoodScheduleDTO schedule, boolean updateAlarms) {
        ArrayList<ScheduleTimeDTO> times = schedule.getMetadata().getTimes();
        if (times.size() != 3) {
            return; // Invalid number of times for food schedule
        }

        Collections.sort(times, Comparator.comparingInt(ScheduleTimeDTO::getHour));
        ScheduleTimeDTO breakfast = times.get(0);
        ScheduleTimeDTO lunch = times.get(1);
        ScheduleTimeDTO dinner = times.get(2);

        breakfast_hour.setText(breakfast.get12HString().toUpperCase());
        lunch_hour.setText(lunch.get12HString().toUpperCase());
        dinner_hour.setText(dinner.get12HString().toUpperCase());

        if (updateAlarms) {
            PendingIntent breakfastPendingIntent = PendingIntent.getBroadcast(App.getAppContext(), ALARM_BREAKFAST, breakfastIntent, 0);
            PendingIntent lunchPendingIntent = PendingIntent.getBroadcast(App.getAppContext(), ALARM_LUNCH, lunchIntent, 0);
            PendingIntent dinnerPendingIntent = PendingIntent.getBroadcast(App.getAppContext(), ALARM_DINNER, dinnerIntent, 0);

            alarmMgr.cancel(breakfastPendingIntent);
            alarmMgr.cancel(lunchPendingIntent);
            alarmMgr.cancel(dinnerPendingIntent);

            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, breakfast.getCalendarRepresentation().getTimeInMillis(), AlarmManager.INTERVAL_DAY, breakfastPendingIntent);
            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, lunch.getCalendarRepresentation().getTimeInMillis(), AlarmManager.INTERVAL_DAY, lunchPendingIntent);
            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, dinner.getCalendarRepresentation().getTimeInMillis(), AlarmManager.INTERVAL_DAY, dinnerPendingIntent);
        }
    }

    private Calendar getCalendarOfHour(String hour) {
        String aux = hour.replace(" PM", "");
        aux = aux.replace(" AM", "");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR, Integer.parseInt(aux.split(":")[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(aux.split(":")[1]));
        calendar.set(Calendar.AM_PM, hour.contains("AM") ? Calendar.AM : Calendar.PM);
        Log.e(">>>", "" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
        return calendar;
    }

    @Override
    public void onClick(View v) {
        ArrayList<FoodScheduleDTO> schedules = sessionManager.loadSchedulesData().getFoodSchedules();
        if (schedules == null || schedules.isEmpty()) {
            // Allow to setup hours if no schedules are defined from the server
            showHourDialog(v);
        }
    }

    public void showHourDialog(View v) {
        HourDialog dialog = new HourDialog();
        dialog.setOriginView(v);
        dialog.setHour(((TextView) v).getText().toString());
        dialog.setOnHourChooseListener(this);
        dialog.show(getActivity().getSupportFragmentManager(), "hourDialog");
    }

    @Override
    public void onHour(View view, Date datetime) {
        TextView tv = (TextView) view;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datetime);
        tv.setText(DateUtils.getHourInString(calendar));

        switch (tv.getId()) {
            case R.id.breakfast_hour:
                PreferenceManager.getDefaultSharedPreferences(getActivity()).edit()
                        .putString("FOOD01", tv.getText().toString()).apply();
                PendingIntent breakfastPendingIntent = PendingIntent.getBroadcast(App.getAppContext(), ALARM_BREAKFAST, breakfastIntent, 0);
                alarmMgr.cancel(breakfastPendingIntent);
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, getCalendarOfHour(tv.getText().toString()).getTimeInMillis(), AlarmManager.INTERVAL_DAY, breakfastPendingIntent);
                break;
            case R.id.lunch_hour:
                PreferenceManager.getDefaultSharedPreferences(getActivity()).edit()
                        .putString("FOOD02", tv.getText().toString()).apply();
                PendingIntent lunchPendingIntent = PendingIntent.getBroadcast(App.getAppContext(), ALARM_LUNCH, lunchIntent, 0);
                alarmMgr.cancel(lunchPendingIntent);
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, getCalendarOfHour(tv.getText().toString()).getTimeInMillis(), AlarmManager.INTERVAL_DAY, lunchPendingIntent);
                break;
            case R.id.dinner_hour:
                PreferenceManager.getDefaultSharedPreferences(getActivity()).edit()
                        .putString("FOOD03", tv.getText().toString()).apply();
                PendingIntent dinnerPendingIntent = PendingIntent.getBroadcast(App.getAppContext(), ALARM_DINNER, dinnerIntent, 0);
                alarmMgr.cancel(dinnerPendingIntent);
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, getCalendarOfHour(tv.getText().toString()).getTimeInMillis(), AlarmManager.INTERVAL_DAY, dinnerPendingIntent);
                break;
        }
    }

    @Override
    public void onResume() {
        ArrayList<FoodScheduleDTO> currentSchedules = sessionManager.loadSchedulesData().getFoodSchedules();
        FoodScheduleDTO currentSchedule = currentSchedules == null || currentSchedules.isEmpty() ? null : currentSchedules.get(0);

        ((DashBoard) getActivity()).getUpdateUserDataThread((updated) -> {
            if (updated) {
                ArrayList<FoodScheduleDTO> newSchedules = sessionManager.loadSchedulesData().getFoodSchedules();
                FoodScheduleDTO newSchedule = newSchedules == null || newSchedules.isEmpty() ? null : newSchedules.get(0);
                getActivity().runOnUiThread(() -> {
                    setupFoodAlarms(newSchedule, !currentSchedule.equals(newSchedule));
                });
            } else {
                getActivity().runOnUiThread(() -> {
                    setupFoodAlarms(currentSchedule, false);
                });
            }
        }).start();

        super.onResume();
    }
}
