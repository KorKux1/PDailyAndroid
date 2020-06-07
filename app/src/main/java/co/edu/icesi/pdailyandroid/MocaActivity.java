package co.edu.icesi.pdailyandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import co.edu.icesi.pdailyandroid.cognosis.builder.Form;
import co.edu.icesi.pdailyandroid.cognosis.fragments.TMT;
import co.edu.icesi.pdailyandroid.cognosis.fragments.TypeA;

public class MocaActivity extends AppCompatActivity {

    TMT tmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moca);

        updateFragmentTMT();
    }


    protected void updateFragmentTMT() {
        tmt = new TMT();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, tmt);
        transaction.commit();
    }
}