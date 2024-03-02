package co.edu.icesi.pdailyandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.model.dto.MedicineScheduleDTO;

public class LevoAdapter extends BaseAdapter {

    private final ArrayList<MedicineScheduleDTO> schedules;

    public LevoAdapter(ArrayList<MedicineScheduleDTO> others) {
        this.schedules = others;
    }

    @Override
    public int getCount() {
        return schedules.size();
    }

    @Override
    public Object getItem(int position) {
        return schedules.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.levocell, null, false);

        TextView levoTitle = row.findViewById(R.id.levoTitle);
        TextView levoDetail = row.findViewById(R.id.levoDetail);
        TextView levoDose = row.findViewById(R.id.levoDose);
        TextView levoDays = row.findViewById(R.id.levoDays);
        TextView levoHours = row.findViewById(R.id.levoHours);

        MedicineScheduleDTO schedule = schedules.get(position);

        DecimalFormat df = new DecimalFormat("0.#####");

        levoTitle.setText(schedule.getTypeFamily());
        levoDetail.setText(schedule.getTypeString());
        levoDose.setText(df.format(schedule.getScheduledDose()) + " cada vez");
        levoDays.setText(schedule.getPlan().getDaysString());
        levoHours.setText(schedule.getPlan().getTimesString());

        return row;
    }

    public MedicineScheduleDTO select(int position) {
        return schedules.get(position);
    }
}
