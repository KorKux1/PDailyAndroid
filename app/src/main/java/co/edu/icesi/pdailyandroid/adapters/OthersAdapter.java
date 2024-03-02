package co.edu.icesi.pdailyandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.viewmodel.OtherViewModel;

public class OthersAdapter extends BaseAdapter {

    private final ArrayList<OtherViewModel> others;

    public OthersAdapter(ArrayList<OtherViewModel> others) {
        this.others = others;
    }

    @Override
    public int getCount() {
        return others.size();
    }

    @Override
    public Object getItem(int position) {
        return others.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.othercell, null, false);
        TextView othersTitle = row.findViewById(R.id.othersTitle);
        TextView othersType = row.findViewById(R.id.othersType);
        TextView othersDose = row.findViewById(R.id.othersDose);
        TextView othersDate = row.findViewById(R.id.othersDate);
        TextView othersEvery = row.findViewById(R.id.othersEvery);
        TextView othersHour = row.findViewById(R.id.othersHour);

        othersTitle.setText(others.get(position).getOthersTitle());
        othersType.setText("Tipo: " + others.get(position).getOthersType());
        othersDose.setText("Dosis: " + others.get(position).getOthersDose());
        othersDate.setText(others.get(position).getOthersDate());
        othersEvery.setText("Cada: " + others.get(position).getOthersEvery());
        othersHour.setText(others.get(position).getOthersHour());

        return row;
    }

    public OtherViewModel select(int position) {
        return others.get(position);
    }
}
