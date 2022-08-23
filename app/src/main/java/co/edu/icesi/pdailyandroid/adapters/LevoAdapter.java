package co.edu.icesi.pdailyandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.model.dto.MedicineScheduleDTO;

public class LevoAdapter extends BaseAdapter {

    private ArrayList<MedicineScheduleDTO> schedules;

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
        TextView levoWindow = row.findViewById(R.id.levoWindow);
        TextView levoHours = row.findViewById(R.id.levoHours);

        MedicineScheduleDTO schedule = schedules.get(position);

        levoTitle.setText(schedule.getTypeLabel());

        String detail = (int) schedule.getTypeQuantity() +
                schedule.getTypeUnits();
        levoDetail.setText("x" + detail);

        String dose = "Toma " + schedule.getScheduledDose() + " de " + detail;
        levoDose.setText(dose);

        levoDays.setText(schedule.getPlan().getDaysString());

        String window = "Desde " + schedule.getPlan().getStartDateString();
        String end = schedule.getPlan().getEndDateString();
        if (end != null) {
            window = window + "\nHasta " + end;
        }
        levoWindow.setText(window);

        levoHours.setText(schedule.getPlan().getTimesString());

        return row;
    }

    public MedicineScheduleDTO select(int position) {
        return schedules.get(position);
    }
}
