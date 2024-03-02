package co.edu.icesi.pdailyandroid.viewcontrollers;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

import co.edu.icesi.pdailyandroid.DashBoard;
import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.app.App;
import co.edu.icesi.pdailyandroid.misc.dialogs.HourDialog;
import co.edu.icesi.pdailyandroid.model.dto.FoodScheduleDTO;
import co.edu.icesi.pdailyandroid.model.dto.ScheduleTimeDTO;
import co.edu.icesi.pdailyandroid.receivers.broadcast.AlarmReceiver;
import co.edu.icesi.pdailyandroid.receivers.broadcast.NotificationReceiver;
import co.edu.icesi.pdailyandroid.util.DateUtils;

public class FoodFragment extends Fragment implements View.OnClickListener, HourDialog.OnHourChoose {

    public static final int ALARM_BREAKFAST = 0;
    public static final int ALARM_LUNCH = 1;
    public static final int ALARM_DINNER = 2;
    TextView breakfast_hour;
    TextView lunch_hour;
    TextView dinner_hour;
    private AlarmManager alarmMgr;
    private Intent breakfastIntent;
    private Intent lunchIntent;
    private Intent dinnerIntent;
    private DashBoard parentActivity;

    public FoodFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentActivity = (DashBoard) getActivity();

        View v = inflater.inflate(R.layout.fragment_food, container, false);
        breakfast_hour = v.findViewById(R.id.breakfast_hour);
        lunch_hour = v.findViewById(R.id.lunch_hour);
        dinner_hour = v.findViewById(R.id.dinner_hour);

        breakfast_hour.setText(PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("FOOD01", "-"));
        lunch_hour.setText(PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("FOOD02", "-"));
        dinner_hour.setText(PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("FOOD03", "-"));

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

        return v;
    }

    private void setupFoodAlarms(FoodScheduleDTO schedule, boolean updateAlarms) {
        PendingIntent breakfastPendingIntent = PendingIntent.getBroadcast(App.getAppContext(), ALARM_BREAKFAST, breakfastIntent, PendingIntent.FLAG_IMMUTABLE);
        PendingIntent lunchPendingIntent = PendingIntent.getBroadcast(App.getAppContext(), ALARM_LUNCH, lunchIntent, PendingIntent.FLAG_IMMUTABLE);
        PendingIntent dinnerPendingIntent = PendingIntent.getBroadcast(App.getAppContext(), ALARM_DINNER, dinnerIntent, PendingIntent.FLAG_IMMUTABLE);

        if (schedule == null) {
            // Disable alarms. Allow custom values
            alarmMgr.cancel(breakfastPendingIntent);
            alarmMgr.cancel(lunchPendingIntent);
            alarmMgr.cancel(dinnerPendingIntent);
            return;
        }

        ArrayList<ScheduleTimeDTO> times = schedule.getPlan().getTimes();
        if (times.size() != 3) {
            return; // Invalid number of times for food schedule
        }

        times.sort(Comparator.comparingInt(ScheduleTimeDTO::getHour));
        ScheduleTimeDTO breakfast = times.get(0);
        ScheduleTimeDTO lunch = times.get(1);
        ScheduleTimeDTO dinner = times.get(2);

        breakfast_hour.setText(breakfast.get12HString().toUpperCase());
        lunch_hour.setText(lunch.get12HString().toUpperCase());
        dinner_hour.setText(dinner.get12HString().toUpperCase());

        //AlarmManager alarmManager = (AlarmManager) requireContext().getSystemService(Context.ALARM_SERVICE);
        Intent intentBreakfast = new Intent(getContext(), NotificationReceiver.class);
        intentBreakfast.putExtra("type", "Breakfast");
        intentBreakfast.putExtra("notification_title", "Título de la Notificación");
        intentBreakfast.putExtra("notification_text", "Texto de la Notificación");

        Intent intentLunch= new Intent(getContext(), NotificationReceiver.class);
        intentLunch.putExtra("type", "Breakfast");
        intentLunch.putExtra("notification_title", "Título de la Notificación");
        intentLunch.putExtra("notification_text", "Texto de la Notificación");

        Intent intentDinner= new Intent(getContext(), NotificationReceiver.class);
        intentDinner.putExtra("type", "Breakfast");
        intentDinner.putExtra("notification_title", "Título de la Notificación");
        intentDinner.putExtra("notification_text", "Texto de la Notificación");

        int flag = 0;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            flag = PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT;
        }
        else {
            flag = PendingIntent.FLAG_UPDATE_CURRENT;
        }

        PendingIntent pendingIntentBreakfast = PendingIntent.getBroadcast(getContext(), 0, intentBreakfast, flag);
        PendingIntent pendingIntentLunch = PendingIntent.getBroadcast(getContext(), 0, intentBreakfast, flag);
        PendingIntent pendingIntentDinner = PendingIntent.getBroadcast(getContext(), 0, intentDinner, flag);




        if (updateAlarms) {
            alarmMgr.cancel(breakfastPendingIntent);
            alarmMgr.cancel(lunchPendingIntent);
            alarmMgr.cancel(dinnerPendingIntent);
            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, breakfast.getCalendarRepresentation().getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntentBreakfast);
            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, lunch.getCalendarRepresentation().getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntentLunch);
            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, dinner.getCalendarRepresentation().getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntentDinner);
        }
    }

    @Override
    public void onClick(View v) {
        ArrayList<FoodScheduleDTO> schedules = parentActivity.getSessionManager().loadSchedulesData().getFoodSchedules();
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
        dialog.show(parentActivity.getSupportFragmentManager(), "hourDialog");
    }

    @Override
    public void onHour(View view, Date datetime) {
        TextView tv = (TextView) view;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datetime);
        String hourStr = DateUtils.getHourString(calendar);
        tv.setText(hourStr);

        switch (tv.getId()) {
            case R.id.breakfast_hour:
                PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString("FOOD01", tv.getText().toString()).apply();
                PendingIntent breakfastPendingIntent = PendingIntent.getBroadcast(App.getAppContext(), ALARM_BREAKFAST, breakfastIntent, PendingIntent.FLAG_IMMUTABLE);
                alarmMgr.cancel(breakfastPendingIntent);
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, breakfastPendingIntent);
                break;
            case R.id.lunch_hour:
                PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString("FOOD02", tv.getText().toString()).apply();
                PendingIntent lunchPendingIntent = PendingIntent.getBroadcast(App.getAppContext(), ALARM_LUNCH, lunchIntent, PendingIntent.FLAG_IMMUTABLE);
                alarmMgr.cancel(lunchPendingIntent);
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, lunchPendingIntent);
                break;
            case R.id.dinner_hour:
                PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString("FOOD03", tv.getText().toString()).apply();
                PendingIntent dinnerPendingIntent = PendingIntent.getBroadcast(App.getAppContext(), ALARM_DINNER, dinnerIntent, PendingIntent.FLAG_IMMUTABLE);
                alarmMgr.cancel(dinnerPendingIntent);
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, dinnerPendingIntent);
                break;
        }
    }

    @Override
    public void onResume() {
        ArrayList<FoodScheduleDTO> currentSchedules = parentActivity.getSessionManager().loadSchedulesData().getFoodSchedules();
        FoodScheduleDTO currentSchedule = currentSchedules != null && !currentSchedules.isEmpty() ? currentSchedules.get(0) : null;
        parentActivity.getUpdateUserDataThread((updated) -> {
            if (updated) {
                ArrayList<FoodScheduleDTO> newSchedules = parentActivity.getSessionManager().loadSchedulesData().getFoodSchedules();
                FoodScheduleDTO newSchedule = newSchedules != null && !newSchedules.isEmpty() ? newSchedules.get(0) : null;
                // TODO: bug. current schedules loaded previously. are null only the first time
                boolean updateAlarms = newSchedule != null && !newSchedule.equals(currentSchedule);
                getActivity().runOnUiThread(() -> setupFoodAlarms(newSchedule, updateAlarms));
            } else {
                getActivity().runOnUiThread(() -> setupFoodAlarms(currentSchedule, false));
            }
        }).start();

        super.onResume();
    }
}
