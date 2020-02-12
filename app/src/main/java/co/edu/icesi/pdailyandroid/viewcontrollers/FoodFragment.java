package co.edu.icesi.pdailyandroid.viewcontrollers;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.dialogs.HourDialog;
import co.edu.icesi.pdailyandroid.util.DateUtils;


public class FoodFragment extends Fragment implements View.OnClickListener, HourDialog.OnHourChoose {


    public FoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_food, container, false);
        root.findViewById(R.id.breakfast_hour).setOnClickListener(this);
        root.findViewById(R.id.lunch_hour).setOnClickListener(this);
        root.findViewById(R.id.dinner_hour).setOnClickListener(this);
        return root;
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
    }
}
