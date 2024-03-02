package co.edu.icesi.pdailyandroid.viewcontrollers;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import co.edu.icesi.pdailyandroid.DashBoard;
import co.edu.icesi.pdailyandroid.R;

public class WakeUpQuestionsFragment extends Fragment {



    public WakeUpQuestionsFragment() {
            // Required empty public constructor
        }


        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View v = inflater.inflate(R.layout.fragment_wakeup_questions, container, false);
            Button navigateButton = v.findViewById(R.id.enviar);


            navigateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProfileFragment fragment = new ProfileFragment();
                    if (getActivity() != null){
                        getActivity().getSupportFragmentManager().beginTransaction().replace(
                                R.id.frameLayout, fragment
                        ).commit();
                    }

//                    FragmentManager fragmentManager = getChildFragmentManager();
//                    fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();

//                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                    ft.replace(R.id.frameLayout, actualFragment);
//                    ft.commit();

                }
            });


            return v;
        }


}
