package co.edu.icesi.pdailyandroid.dialogs;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import co.edu.icesi.pdailyandroid.R;

public class HourDialog extends DialogFragment {

    private TimePicker picker;
    private Button okButton;
    private View origin;
    private OnHourChoose onHourChooseListener;

    public void setOriginView(View origin){
        this.origin = origin;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        String texto = ((TextView) origin).getText().toString();
        String textoMod = texto.substring(0, texto.length()-3);
        String[] time = textoMod.split(":");


        View view = getLayoutInflater().inflate(R.layout.hour_dialog, null);
        picker = view.findViewById(R.id.timepicker);
        okButton = view.findViewById(R.id.okButton);


        picker.setHour( texto.contains("PM")?Integer.parseInt(time[0])+12 : Integer.parseInt(time[0]) );
        picker.setMinute( Integer.parseInt(time[1]) );

        okButton.setOnClickListener((v)->{
            int hour = picker.getHour();
            int minute = picker.getMinute();

            String output = "" + (hour>=13?Math.abs(hour-12):hour) + ":" + (minute<=9?"0"+minute:""+minute) + " " + (hour>=13?"PM":"AM");
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.MINUTE, minute);
            cal.set(Calendar.HOUR_OF_DAY, hour);
            cal.set(Calendar.SECOND, 0);
            Date outdate = cal.getTime();
            onHourChooseListener.onHour(origin, output, outdate);
            this.dismiss();
        });

        return view;
    }

    public void setOnHourChooseListener(OnHourChoose onHourChooseListener){
        this.onHourChooseListener = onHourChooseListener;
    }

    public interface OnHourChoose{
        void onHour(View view, String hour, Date datetime);
    }

}
