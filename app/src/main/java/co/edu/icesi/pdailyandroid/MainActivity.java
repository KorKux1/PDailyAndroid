package co.edu.icesi.pdailyandroid;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessaging;

import co.edu.icesi.pdailyandroid.services.AuthService;
import co.edu.icesi.pdailyandroid.services.PDailyHttpClient;
import co.edu.icesi.pdailyandroid.services.SessionManager;
import co.edu.icesi.pdailyandroid.services.UserInfoService;

public class MainActivity extends AppCompatActivity {

    FirebaseMessaging messaging;
    private TextView errorHint;
    private EditText usernameET, passwordET;
    private SessionManager sessionManager;
    private AuthService authService;
    private UserInfoService userInfoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        errorHint = findViewById(R.id.errorHint);
        usernameET = findViewById(R.id.usernameET);
        passwordET = findViewById(R.id.passwordET);
        messaging = FirebaseMessaging.getInstance();
        messaging.setAutoInitEnabled(true);
        sessionManager = new SessionManager(getApplicationContext());
        authService = new AuthService(sessionManager);
        userInfoService = new UserInfoService(sessionManager);
        validateAuthentication();
        PDailyHttpClient.trustAllCertificates();
    }

    private void validateAuthentication() {
        if (sessionManager.isLoggedIn()) {
            new Thread(() -> {
                boolean updated = userInfoService.updateSchedulesCollectionFromServer();
                runOnUiThread(() -> {
                    if (updated) {
                        moveToNextPage();
                    }
                });
            }).start();
        }
    }

    public void authenticate(View v) {
        Button button = (Button) v;
        button.setText("Cargando...");
        button.setBackgroundColor(Color.parseColor("#AAAAAA"));
        button.setEnabled(false);
        new Thread(() -> {
            String userName = usernameET.getText().toString();
            String password = passwordET.getText().toString();
            boolean authenticated = authService.authenticate(userName, password) &&
                    userInfoService.updateSchedulesCollectionFromServer();

            runOnUiThread(() -> {
                if (authenticated) {
                    moveToNextPage();
                } else {
                    errorHint.setVisibility(View.VISIBLE);
                }

                button.setText("INICIAR SESIÓN");
                button.setEnabled(true);
                button.setBackgroundColor(Color.parseColor("#ff0099cc"));
            });
        }).start();
    }

    private void moveToNextPage() {
        Intent i = new Intent(this, DashBoard.class);
        startActivity(i);
        finish();
    }
}
