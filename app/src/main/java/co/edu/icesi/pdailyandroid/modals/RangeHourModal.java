package co.edu.icesi.pdailyandroid.modals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.dialogs.BodyDialog;
import co.edu.icesi.pdailyandroid.dialogs.HourDialog;
import co.edu.icesi.pdailyandroid.model.Event;
import co.edu.icesi.pdailyandroid.temporals.EventTemporal;
import co.edu.icesi.pdailyandroid.viewmodel.EventViewModel;

public class RangeHourModal extends AppCompatActivity implements HourDialog.OnHourChoose {

    private ImageView modalImage;
    private EventViewModel event;
    private TextView titleEvent;

    private TextView fromhour;
    private TextView tohour;
    private Button nextButton;

    private int[] congelamiento, temblor, tropezones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_range_hour_modal);

        congelamiento = new int[19];
        congelamiento[0] = R.drawable.con0;
        congelamiento[1] = R.drawable.con1;
        congelamiento[2] = R.drawable.con2;
        congelamiento[3] = R.drawable.con3;
        congelamiento[4] = R.drawable.con4;
        congelamiento[5] = R.drawable.con5;
        congelamiento[6] = R.drawable.con6;
        congelamiento[7] = R.drawable.con7;
        congelamiento[8] = R.drawable.con8;
        congelamiento[9] = R.drawable.con9;
        congelamiento[10] = R.drawable.con10;
        congelamiento[11] = R.drawable.con11;
        congelamiento[12] = R.drawable.con12;
        congelamiento[13] = R.drawable.con13;
        congelamiento[14] = R.drawable.con14;
        congelamiento[15] = R.drawable.con15;
        congelamiento[16] = R.drawable.con16;
        congelamiento[17] = R.drawable.con17;
        congelamiento[18] = R.drawable.con18;

        temblor = new int[19];
        temblor[0] = R.drawable.tem0;
        temblor[1] = R.drawable.tem1;
        temblor[2] = R.drawable.tem2;
        temblor[3] = R.drawable.tem3;
        temblor[4] = R.drawable.tem4;
        temblor[5] = R.drawable.tem5;
        temblor[6] = R.drawable.tem6;
        temblor[7] = R.drawable.tem7;
        temblor[8] = R.drawable.tem8;
        temblor[9] = R.drawable.tem9;
        temblor[10] = R.drawable.tem10;
        temblor[11] = R.drawable.tem11;
        temblor[12] = R.drawable.tem12;
        temblor[13] = R.drawable.tem13;
        temblor[14] = R.drawable.tem14;
        temblor[15] = R.drawable.tem15;
        temblor[16] = R.drawable.tem16;
        temblor[17] = R.drawable.tem17;
        temblor[18] = R.drawable.tem18;

        tropezones = new int[19];
        tropezones[0] = R.drawable.tro0;
        tropezones[1] = R.drawable.tro1;
        tropezones[2] = R.drawable.tro2;
        tropezones[3] = R.drawable.tro3;
        tropezones[4] = R.drawable.tro4;
        tropezones[5] = R.drawable.tro5;
        tropezones[6] = R.drawable.tro6;
        tropezones[7] = R.drawable.tro7;
        tropezones[8] = R.drawable.tro8;
        tropezones[9] = R.drawable.tro9;
        tropezones[10] = R.drawable.tro10;
        tropezones[11] = R.drawable.tro11;
        tropezones[12] = R.drawable.tro12;
        tropezones[13] = R.drawable.tro13;
        tropezones[14] = R.drawable.tro14;
        tropezones[15] = R.drawable.tro15;
        tropezones[16] = R.drawable.tro16;
        tropezones[17] = R.drawable.tro17;
        tropezones[18] = R.drawable.tro18;

        event = (EventViewModel) getIntent().getExtras().getSerializable("event");

        modalImage = findViewById(R.id.modalImage);
        titleEvent = findViewById(R.id.titleEvent);
        nextButton = findViewById(R.id.nextButton);

        fromhour = findViewById(R.id.fromhour);
        tohour = findViewById(R.id.tohour);

        fromhour.setOnClickListener(
            (v)->{
                HourDialog dialog = new HourDialog();
                dialog.setOriginView(v);
                dialog.setHour( ( (TextView) v ).getText().toString() );
                dialog.setOnHourChooseListener(this);
                dialog.show(getSupportFragmentManager(), "hourDialog");
            }
        );

        tohour.setOnClickListener(
                (v)->{
                    HourDialog dialog = new HourDialog();
                    dialog.setOriginView(v);
                    dialog.setHour( ( (TextView) v ).getText().toString() );
                    dialog.setOnHourChooseListener(this);
                    dialog.show(getSupportFragmentManager(), "hourDialog");
                }
        );

        nextButton.setOnClickListener(
                (v)->{
                    Intent i = new Intent();
                    i.putExtra("title", titleEvent.getText().toString());
                    i.putExtra("from", fromhour.getText().toString());
                    i.putExtra("to", tohour.getText().toString());

                    setResult(RESULT_OK, i);
                    finish();
                }
        );

        titleEvent.setText(event.getName());
        switch (event.getName()){
            case "Congelamiento":
                runAnimation(congelamiento);
                break;
            case "Lentificación":
                runAnimation(congelamiento);
                break;
            case "Discinesias":
                runAnimation(temblor);
                break;
            case "Temblor":
                runAnimation(temblor);
                break;
            case "Tropezones":
                runAnimation(tropezones);
                break;
            case "Caídas":
                runAnimation(tropezones);
                break;
        }

    }


    private int contador = 0;
    private boolean animLive = true;

    public void runAnimation(int[] array) {
        new Thread(() -> {
                try {
                    while (animLive) {

                        runOnUiThread(() -> {
                            modalImage.setImageResource(array[(contador<0 && contador>array.length)?0:contador]);
                        });

                        Thread.sleep(80);

                        contador += 1;
                        if (contador >= array.length) {
                            contador = 0;
                            Thread.sleep(500);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

    }

    @Override
    protected void onPause() {
        animLive = false;
        super.onPause();
    }

    @Override
    public void onHour(View view, String hour, Date datetime) {
        Log.e(">>>>>",hour);
        TextView destinationView = (TextView) view;
        destinationView.setText(hour);
    }
}
