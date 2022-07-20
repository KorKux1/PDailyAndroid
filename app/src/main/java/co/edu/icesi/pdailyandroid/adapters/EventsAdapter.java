package co.edu.icesi.pdailyandroid.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.viewmodel.EventViewModel;

public class EventsAdapter extends BaseAdapter {

    private ArrayList<EventViewModel> events;
    private int markedPosition = -1;

    public EventsAdapter(ArrayList<EventViewModel> events) {
        this.events = events;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.eventcell, null, false);
        TextView symptomLabel = row.findViewById(R.id.symptomLabel);
        ImageView symptomEvaluatedSign = row.findViewById(R.id.symptomEvaluatedSign);

        symptomLabel.setText(events.get(position).getName());
        if (events.get(position).isEvaluated()) {
            symptomEvaluatedSign.setVisibility(View.VISIBLE);
        } else {
            symptomEvaluatedSign.setVisibility(View.GONE);
        }
        if (this.markedPosition == position) {
            row.setBackgroundColor(Color.rgb(150, 150, 150));
        }
        return row;
    }

    public EventViewModel select(int position) {
        return events.get(position);
    }

    public void mark(int position) {
        this.markedPosition = position;
        notifyDataSetChanged();
    }

    public boolean isAnyItemSelected() {
        return markedPosition != -1;
    }

    public String getNameOfItemMarked() {
        return events.get(markedPosition).getName();
    }

    public int getPositionOf(String name) {
        int index = -1;
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getName().equals(name)) {
                index = i;
            }
        }
        return index;
    }
}
