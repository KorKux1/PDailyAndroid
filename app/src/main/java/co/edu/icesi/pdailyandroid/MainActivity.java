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

public class MainActivity extends AppCompatActivity {

    FirebaseMessaging messaging;
    private TextView errorHint;
    private EditText usernameET, passwordET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        errorHint = findViewById(R.id.errorHint);
        usernameET = findViewById(R.id.usernameET);
        passwordET = findViewById(R.id.passwordET);
        messaging = FirebaseMessaging.getInstance();
        messaging.setAutoInitEnabled(true);
    }

    public void authenticate(View v) {
        Button button = (Button) v;
        button.setText("Cargando...");
        button.setBackgroundColor(Color.parseColor("#AAAAAA"));
        button.setEnabled(false);
        new Thread(
                () -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(
                            () -> {
                                String userName = usernameET.getText().toString().trim();
                                String password = passwordET.getText().toString().trim();
                                if (userName.equals("admin") && password.equals("password")) {
                                    Intent i = new Intent(this, DashBoard.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    errorHint.setVisibility(View.VISIBLE);
                                }
                                button.setText("INICIAR SESIÓN");
                                button.setEnabled(true);
                                button.setBackgroundColor(Color.parseColor("#ff0099cc"));
                            }
                    );
                }
        ).start();
    }
}
