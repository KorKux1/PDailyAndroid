package co.edu.icesi.pdailyandroid.viewcontrollers;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.UUID;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.games.BananaGame;
import co.edu.icesi.pdailyandroid.games.WormGame;
import co.edu.icesi.pdailyandroid.model.dto.FcmDTO;
import co.edu.icesi.pdailyandroid.model.dto.FoodDTO;
import co.edu.icesi.pdailyandroid.services.HTTPSWebUtilDomi;
import co.edu.icesi.pdailyandroid.util.Constants;

public class SupportFragment extends Fragment {

    public SupportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_support, container, false);
        v.findViewById(R.id.pushDebug).setOnClickListener(
                view -> {
                    new Thread(
                            ()->{
                                HTTPSWebUtilDomi util = new HTTPSWebUtilDomi();
                                FcmDTO fcmDTO = new FcmDTO(
                                        "/topics/"+Constants.patientID,
                                        new FoodDTO(UUID.randomUUID().toString(),"food","12345")
                                );
                                String json = new Gson().toJson(fcmDTO);
                                Log.e(">>>",json);
                                util.POSTtoFCM(Constants.API_KEY, json);
                            }
                    ).start();

                }
        );
        return v;
    }


}
