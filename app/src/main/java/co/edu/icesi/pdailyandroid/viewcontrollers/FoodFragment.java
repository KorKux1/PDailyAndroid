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

import java.util.Calendar;
import java.util.Date;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.app.App;
import co.edu.icesi.pdailyandroid.receivers.broadcast.AlarmReceiver;
import co.edu.icesi.pdailyandroid.misc.dialogs.HourDialog;
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

    public FoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_food, container, false);

        TextView breakfast_hour = root.findViewById(R.id.breakfast_hour);
        TextView lunch_hour = root.findViewById(R.id.lunch_hour);
        TextView dinner_hour = root.findViewById(R.id.dinner_hour);

        breakfast_hour.setText( PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("FOOD01", breakfast_hour.getText().toString()) );
        lunch_hour.setText( PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("FOOD02", lunch_hour.getText().toString()) );
        dinner_hour.setText( PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("FOOD03", dinner_hour.getText().toString()) );

        breakfast_hour.setOnClickListener(this);
        lunch_hour.setOnClickListener(this);
        dinner_hour.setOnClickListener(this);

        alarmMgr = (AlarmManager) App.getAppContext().getSystemService(Context.ALARM_SERVICE);
        breakfastIntent = new Intent(App.getAppContext(), AlarmReceiver.class);
        breakfastIntent.putExtra("type","FOOD01");
        lunchIntent = new Intent(App.getAppContext(), AlarmReceiver.class);
        lunchIntent.putExtra("type","FOOD02");
        dinnerIntent = new Intent(App.getAppContext(), AlarmReceiver.class);
        dinnerIntent.putExtra("type","FOOD03");

        if(!PendingIntentUtils.isPendingIntentRegistered(ALARM_BREAKFAST, breakfastIntent)) {
            PendingIntent breakfastPendingIntent = PendingIntent.getBroadcast(App.getAppContext(), ALARM_BREAKFAST, breakfastIntent, 0);
            String breakfastHour = breakfast_hour.getText().toString();
            Calendar breakfastCalendar = getCalendarOfHour(breakfastHour);
            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, breakfastCalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, breakfastPendingIntent);
        }

        if(!PendingIntentUtils.isPendingIntentRegistered(ALARM_LUNCH, lunchIntent)) {
            PendingIntent lunchPendingIntent = PendingIntent.getBroadcast(App.getAppContext(), ALARM_LUNCH, lunchIntent, 0);
            String lunchHour = lunch_hour.getText().toString();
            Calendar lunchCalendar = getCalendarOfHour(lunchHour);
            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, lunchCalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, lunchPendingIntent);
        }

        if(!PendingIntentUtils.isPendingIntentRegistered(ALARM_DINNER, dinnerIntent)) {
            PendingIntent dinnerPendingIntent = PendingIntent.getBroadcast(App.getAppContext(), ALARM_DINNER, dinnerIntent, 0);
            String dinnerHour = dinner_hour.getText().toString();
            Calendar dinnerCalendar = getCalendarOfHour(dinnerHour);
            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, dinnerCalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, dinnerPendingIntent);
        }

        return root;
    }

    private Calendar getCalendarOfHour(String hour) {
        String aux = hour.replace(" PM", "");
        aux = aux.replace(" AM", "");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR, Integer.parseInt(  aux.split(":")[0]  ));
        calendar.set(Calendar.MINUTE, Integer.parseInt(  aux.split(":")[1]  ));
        calendar.set(Calendar.AM_PM, hour.contains("AM") ? Calendar.AM : Calendar.PM);

        Log.e(">>>",""+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE));

        return calendar;
    }

    @Override
    public void onClick(View v) {
        showHourDialog(v);
    }

    public void showHourDialog(View v){
        HourDialog dialog = new HourDialog();
        dialog.setOriginView(v);
        dialog.setHour( ( (TextView) v ).getText().toString() );
        dialog.setOnHourChooseListener(this);
        dialog.show(getActivity().getSupportFragmentManager(), "hourDialog");
    }

    @Override
    public void onHour(View view, Date datetime) {
        TextView tv = (TextView) view;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datetime);
        tv.setText(DateUtils.getHourInString(calendar));

        switch (tv.getId()){
            case R.id.breakfast_hour:
                PreferenceManager.getDefaultSharedPreferences(getActivity())
                        .edit().putString("FOOD01", tv.getText().toString())
                        .apply();

                PendingIntent breakfastPendingIntent = PendingIntent.getBroadcast(App.getAppContext(), ALARM_BREAKFAST, breakfastIntent, 0);
                alarmMgr.cancel(breakfastPendingIntent);
                String breakfast = tv.getText().toString();
                Calendar breakFastCalendar = getCalendarOfHour(breakfast);
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, breakFastCalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, breakfastPendingIntent);
                break;
            case R.id.lunch_hour:
                PreferenceManager.getDefaultSharedPreferences(getActivity())
                        .edit().putString("FOOD02", tv.getText().toString())
                        .apply();

                PendingIntent lunchPendingIntent = PendingIntent.getBroadcast(App.getAppContext(), ALARM_LUNCH, lunchIntent, 0);
                alarmMgr.cancel(lunchPendingIntent);
                String lunchHour = tv.getText().toString();
                Calendar lunchCalendar = getCalendarOfHour(lunchHour);
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, lunchCalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, lunchPendingIntent);
                break;
            case R.id.dinner_hour:
                PreferenceManager.getDefaultSharedPreferences(getActivity())
                        .edit().putString("FOOD03", tv.getText().toString())
                        .apply();

                PendingIntent dinnerPendingIntent = PendingIntent.getBroadcast(App.getAppContext(), ALARM_DINNER, dinnerIntent, 0);
                alarmMgr.cancel(dinnerPendingIntent);
                String dinnerHour = tv.getText().toString();
                Calendar dinnerCalendar = getCalendarOfHour(dinnerHour);
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, dinnerCalendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, dinnerPendingIntent);
                break;
        }
    }
}
