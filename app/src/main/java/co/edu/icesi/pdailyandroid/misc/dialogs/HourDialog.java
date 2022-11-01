package co.edu.icesi.pdailyandroid.misc.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;

import co.edu.icesi.pdailyandroid.R;

public class HourDialog extends DialogFragment {

    private TimePicker picker;
    private Button okButton;
    private String hour;
    private OnHourChoose onHourChooseListener;
    private View originView;

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setOriginView(View originView) {
        this.originView = originView;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.hour_dialog, null);
        picker = view.findViewById(R.id.timepicker);
        okButton = view.findViewById(R.id.okButton);

        int h = 0;
        int m = 0;
        if (!hour.equals("-")) {
            String[] time = hour.substring(0, hour.length() - 3).split(":");
            h = Integer.parseInt(time[0]);
            m = Integer.parseInt(time[1]);
            String t = hour.substring(hour.length() - 2);

            if (t.equals("PM") && h < 12) {
                h += 12;
            }

            picker.setHour(h);
            picker.setMinute(m);
        }

        okButton.setOnClickListener((v) -> {
            int hh = picker.getHour();
            int mm = picker.getMinute();

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, hh);
            cal.set(Calendar.MINUTE, mm);
            cal.set(Calendar.SECOND, 0);
            onHourChooseListener.onHour(originView, cal.getTime());

            this.dismiss();
        });

        return view;
    }

    public void setOnHourChooseListener(OnHourChoose onHourChooseListener) {
        this.onHourChooseListener = onHourChooseListener;
    }

    public interface OnHourChoose {
        void onHour(View view, Date datetime);
    }

}
