package co.edu.icesi.pdailyandroid;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.service.notification.NotificationListenerService;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Calendar;

import co.edu.icesi.pdailyandroid.receivers.broadcast.NotificationReceiver;
import co.edu.icesi.pdailyandroid.services.AuthService;
import co.edu.icesi.pdailyandroid.services.PDailyHttpClient;
import co.edu.icesi.pdailyandroid.services.SessionManager;
import co.edu.icesi.pdailyandroid.services.UserInfoService;
import co.edu.icesi.pdailyandroid.viewcontrollers.NotificationPermissionFragment;
import co.edu.icesi.pdailyandroid.viewcontrollers.WakeUpQuestionsFragment;

public class MainActivity extends AppCompatActivity {

    FirebaseMessaging messaging;
    private TextView errorHint;
    private EditText usernameET, passwordET;
    private SessionManager sessionManager;
    private AuthService authService;
    private UserInfoService userInfoService;

    private static final int NOTIFICATION_PERMISSION_REQUEST_CODE = 123;


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
        PDailyHttpClient.trustAllCertificates();


        validateAuthentication();
    }



    private void validateAuthentication() {
        if (sessionManager.isLoggedIn()) {
            new Thread(() -> {
                boolean updated = userInfoService.updateSchedulesCollectionFromServer();
                if (updated) {
                    runOnUiThread(this::moveToNextPage);
                }
            }).start();
        }
    }

    public void authenticate(View v) {
        Button button = (Button) v;
        button.setText(R.string.loading);
        button.setBackgroundColor(Color.parseColor("#AAAAAA"));
        button.setEnabled(false);
        new Thread(() -> {
            String userName = usernameET.getText().toString();
            String password = passwordET.getText().toString();
            boolean authenticatedAndUpdated = authService.authenticate(userName, password) &&
                    userInfoService.updateSchedulesCollectionFromServer();

            runOnUiThread(() -> {
                if (authenticatedAndUpdated) {
                    moveToNextPage();
                } else {
                    errorHint.setVisibility(View.VISIBLE);
                }

                button.setText(R.string.login);
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
