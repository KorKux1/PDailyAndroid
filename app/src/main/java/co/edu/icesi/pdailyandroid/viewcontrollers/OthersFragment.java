package co.edu.icesi.pdailyandroid.viewcontrollers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.adapters.OthersAdapter;
import co.edu.icesi.pdailyandroid.viewmodel.OtherViewModel;

public class OthersFragment extends Fragment {

    private ListView othersTable;
    private OthersAdapter adapter;
    private ArrayList<OtherViewModel> others;

    public OthersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_others, container, false);
        others = createArray();
        othersTable = v.findViewById(R.id.othersTable);
        adapter = new OthersAdapter(others);
        othersTable.setAdapter(adapter);
        return v;
    }

    public ArrayList<OtherViewModel> createArray() {
        ArrayList<OtherViewModel> out = new ArrayList<>();
        OtherViewModel s1 = new OtherViewModel("Aspirina", "normal", "400mg", "17/06/2017", "8 horas", "8am - 4pm - 12am");

        OtherViewModel s2 = new OtherViewModel("Beta", "normal", "100mg", "01/03/2015", "Cada: 6 horas", "6am - 12pm - 6pm");

        out.add(s1);
        out.add(s2);
        return out;
    }

}
