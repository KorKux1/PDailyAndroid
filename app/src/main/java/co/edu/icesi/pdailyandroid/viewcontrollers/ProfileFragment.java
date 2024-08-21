package co.edu.icesi.pdailyandroid.viewcontrollers;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import java.util.Date;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.model.dto.AnimicEventDTO;
import co.edu.icesi.pdailyandroid.model.session.SessionData;
import co.edu.icesi.pdailyandroid.model.viewmodel.AnimicTypes;
import co.edu.icesi.pdailyandroid.services.SessionManager;
import co.edu.icesi.pdailyandroid.services.WebserviceConsumer;

public class ProfileFragment extends Fragment {

    private ConstraintLayout messageContainer;

    private TextView statusMessage;
    private ImageView statusFace;
    private Button sendBtn;

    private Button statusBtn2;
    private Button statusBtn3;
    private Button statusBtn4;
    private Button statusBtn5;
    private Button statusBtn6;
    private Button statusBtn7;
    private Button statusBtn8;
    private Button statusBtn9;
    private Button statusBtn10;

    private int statusValue;
    private int statusIcon;

    private AnimicTypes types;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        messageContainer = v.findViewById(R.id.messageContainer);
        statusMessage = v.findViewById(R.id.statusMessage);
        statusFace = v.findViewById(R.id.statusFace);
        sendBtn = v.findViewById(R.id.sendBtn);
        statusBtn2 = v.findViewById(R.id.statusBtn2);
        statusBtn3 = v.findViewById(R.id.statusBtn3);
        statusBtn4 = v.findViewById(R.id.statusBtn4);
        statusBtn5 = v.findViewById(R.id.statusBtn5);
        statusBtn6 = v.findViewById(R.id.statusBtn6);
        statusBtn7 = v.findViewById(R.id.statusBtn7);
        statusBtn8 = v.findViewById(R.id.statusBtn8);
        statusBtn9 = v.findViewById(R.id.statusBtn9);
        statusBtn10 = v.findViewById(R.id.statusBtn10);

        statusBtn2.setOnClickListener(this::doStatusAssess);
        statusBtn3.setOnClickListener(this::doStatusAssess);
        statusBtn4.setOnClickListener(this::doStatusAssess);
        statusBtn5.setOnClickListener(this::doStatusAssess);
        statusBtn6.setOnClickListener(this::doStatusAssess);
        statusBtn7.setOnClickListener(this::doStatusAssess);
        statusBtn8.setOnClickListener(this::doStatusAssess);
        statusBtn9.setOnClickListener(this::doStatusAssess);
        statusBtn10.setOnClickListener(this::doStatusAssess);

        types = AnimicTypes.getReference();

        sendBtn.setOnClickListener(view -> {
            sendBtn.setText("...");
            sendBtn.setEnabled(false);
            SessionManager sessionManager = new SessionManager(
                getActivity().getApplicationContext());
            SessionData sessionData = sessionManager.loadLoginData();
            AnimicEventDTO eventDTO = new AnimicEventDTO(
                sessionData.getPatientId(),
                types.getTypeIDByScore(statusValue),
                new Date(System.currentTimeMillis()));
            WebserviceConsumer consumer = new WebserviceConsumer();
            consumer.postAnimic(eventDTO, sessionData.getToken())
                .withEndAction(response ->
                    getActivity().runOnUiThread(
                        () -> {
                            sendBtn.setText(null);
                            sendBtn.setBackgroundResource(R.drawable.chulo_btn);
                        })
                ).execute();
        });

        statusBtn10.post(() -> doStatusAssess(statusBtn10));

        return v;
    }

    public void doStatusAssess(View sender) {
        deselectAll();

        if (sender.equals(statusBtn2)) {
            statusValue = 9;
            statusIcon = R.drawable.rostro9;
        } else if (sender.equals(statusBtn3)) {
            statusValue = 8;
            statusIcon = R.drawable.rostro8;
        } else if (sender.equals(statusBtn4)) {
            statusValue = 7;
            statusIcon = R.drawable.rostro7;
        } else if (sender.equals(statusBtn5)) {
            statusValue = 6;
            statusIcon = R.drawable.rostro6;
        } else if (sender.equals(statusBtn6)) {
            statusValue = 5;
            statusIcon = R.drawable.rostro5;
        } else if (sender.equals(statusBtn7)) {
            statusValue = 4;
            statusIcon = R.drawable.rostro4;
        } else if (sender.equals(statusBtn8)) {
            statusValue = 3;
            statusIcon = R.drawable.rostro3;
        } else if (sender.equals(statusBtn9)) {
            statusValue = 2;
            statusIcon = R.drawable.rostro2;
        } else if (sender.equals(statusBtn10)) {
            statusValue = 1;
            statusIcon = R.drawable.rostro1;
        }

        statusMessage.setText(types.getLabelByScore(statusValue));
        statusFace.setImageResource(statusIcon);
        sender.setBackgroundColor(Color.WHITE);
        sendBtn.setEnabled(true);
        sendBtn.setText(R.string.register_symptoms);
        sendBtn.setBackgroundColor(Color.rgb(0, 153, 204));

        if (statusValue == 1){
            messageContainer.setY(sender.getY() + sender.getHeight() + 60);
            sendBtn.setX(messageContainer.getX() + messageContainer.getWidth() / 2 - sendBtn.getWidth() / 2);
            sendBtn.setY(messageContainer.getY() + messageContainer.getHeight() + 50);
        } else if  (statusValue < 5) {
            messageContainer.setY(sender.getY() + sender.getHeight() / 2);
            sendBtn.setX(messageContainer.getX() + messageContainer.getWidth() / 2 - sendBtn.getWidth() / 2);
            sendBtn.setY(messageContainer.getY() + messageContainer.getHeight() + 24);
        } else {
            messageContainer.setY(sender.getY() + (int) (1.5 * sender.getHeight()) - messageContainer.getHeight());
            sendBtn.setX(messageContainer.getX() + messageContainer.getWidth() / 2 - sendBtn.getWidth() / 2);
            sendBtn.setY(messageContainer.getY() - sendBtn.getHeight() - 24);
        }
    }

    private void deselectAll() {
        statusBtn10.setBackgroundColor(Color.rgb(24, 255, 0));
        statusBtn9.setBackgroundColor(Color.rgb(116, 255, 0));
        statusBtn8.setBackgroundColor(Color.rgb(158, 255, 0));
        statusBtn7.setBackgroundColor(Color.rgb(255, 255, 0));
        statusBtn6.setBackgroundColor(Color.rgb(255, 241, 1));
        statusBtn5.setBackgroundColor(Color.rgb(255, 178, 0));
        statusBtn4.setBackgroundColor(Color.rgb(255, 139, 0));
        statusBtn3.setBackgroundColor(Color.rgb(255, 43, 0));
        statusBtn2.setBackgroundColor(Color.rgb(255, 0, 2));
    }
}
