package co.edu.icesi.pdailyandroid.viewcontrollers;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import java.util.Calendar;
import java.util.UUID;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.model.dto.FcmDTO;
import co.edu.icesi.pdailyandroid.model.dto.FoodDTO;
import co.edu.icesi.pdailyandroid.services.HTTPSWebUtilDomi;
import co.edu.icesi.pdailyandroid.services.HTTPWebUtilDomi;
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
                            () -> {
                                HTTPSWebUtilDomi util = new HTTPSWebUtilDomi();
                                FcmDTO fcmDTO = new FcmDTO(
                                        "/topics/" + Constants.patientID,
                                        new FoodDTO(UUID.randomUUID().toString(), "food", "12345")
                                );
                                String json = new Gson().toJson(fcmDTO);
                                Log.e(">>>", json);
                                util.POSTtoFCM(Constants.API_KEY, json);
                            }
                    ).start();

                }
        );

        Spinner spinnerMethod = v.findViewById(R.id.spinnerMethod);
        spinnerMethod.setAdapter(new ArrayAdapter<>(v.getContext(), android.R.layout.simple_list_item_1, new String[]{"GET", "POST", "PUT", "DELETE"}));
        EditText inputREST = v.findViewById(R.id.inputREST);
        EditText inputURL = v.findViewById(R.id.inputURL);

        inputREST.setText("{\n" +
                "    \"patientId\": \"DF20D5BD-F16A-48B0-9922-0D5E537DCB24\",\n" +
                "    \"date\": " + Calendar.getInstance().getTime().getTime() + "\n" +
                "}");
        inputURL.setText("http://118709758ce4.ngrok.io/event/food");

        EditText outputREST = v.findViewById(R.id.outputREST);

        v.findViewById(R.id.inputX).setOnClickListener(
                view -> {
                    inputREST.setText("");
                }
        );
        v.findViewById(R.id.outputX).setOnClickListener(
                view -> {
                    outputREST.setText("");
                }
        );

        v.findViewById(R.id.sendBTN).setOnClickListener(
                view -> {
                    new Thread(
                            () -> {
                                String method = (String) spinnerMethod.getSelectedItem();

                                HTTPSWebUtilDomi httpsutil = new HTTPSWebUtilDomi();
                                httpsutil.setHeader("Content-Type", "application/json");
                                httpsutil.setHeader("pdaily-tenant", Constants.PDAILY_PASSWORD);
                                httpsutil.setBasicAuth("admin", "admin");

                                HTTPWebUtilDomi httputil = new HTTPWebUtilDomi();
                                httputil.setHeader("Content-Type", "application/json");
                                httputil.setHeader("pdaily-tenant", Constants.PDAILY_PASSWORD);
                                httputil.setBasicAuth("admin", "admin");

                                String json = inputREST.getText().toString().trim().replace("\n", "");
                                Log.e(">>>", json);
                                String url = inputURL.getText().toString();
                                switch (method) {
                                    case "GET":
                                        String get = url.startsWith("https") ? httpsutil.syncGETrequest(url) : httputil.syncGETrequest(url);
                                        getActivity().runOnUiThread(() -> outputREST.setText(get));
                                        break;
                                    case "POST":
                                        String post = url.startsWith("https") ? httpsutil.syncPOSTRequest(url, json) : httputil.syncPOSTRequest(url, json);
                                        getActivity().runOnUiThread(() -> outputREST.setText(post));
                                        break;
                                    case "PUT":
                                        String put = url.startsWith("https") ? httpsutil.syncPUTRequest(url, json) : httputil.syncPUTRequest(url, json);
                                        getActivity().runOnUiThread(() -> outputREST.setText(put));
                                        break;
                                    case "DELETE":
                                        String delete = url.startsWith("https") ? httpsutil.syncDELETErequest(url) : httputil.syncDELETErequest(url);
                                        getActivity().runOnUiThread(() -> outputREST.setText(delete));
                                        break;
                                }

                            }
                    ).start();
                }
        );

        return v;
    }

}
