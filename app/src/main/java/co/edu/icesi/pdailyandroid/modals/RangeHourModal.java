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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_range_hour_modal);

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
                runAnimation("con");
                break;
            case "Lentificación":
                runAnimation("con");
                break;
            case "Discinesias":
                runAnimation("con");
                break;
            case "Temblor":
                runAnimation("tem");
                break;
            case "Tropezones":
                runAnimation("tro");
                break;
            case "Caídas":
                runAnimation("con");
                break;
        }

    }


    private int contador = 0;
    private boolean animLive = true;
    private boolean sequence = false;

    public void runAnimation(String prefix) {
        Log.e(">>>","Animation started!");
        sequence = false;
        new Thread(() -> {
            try {
                while (animLive) {


                    Thread.sleep(20);


                    while (sequence) {
                    }
                    sequence = true;

                    contador += 1;
                    if (contador >= 19) {
                        contador = 0;
                        Thread.sleep(500);
                    }

                    runOnUiThread(()->{
                        String name = prefix+""+contador;
                        int drawableResourceId = this.getResources().getIdentifier(name, "drawable", this.getPackageName());
                        modalImage.setImageResource(drawableResourceId);
                        sequence = false;
                    });
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    protected void onPause() {
        animLive = false;
        sequence = false;
        super.onPause();
    }

    @Override
    public void onHour(View view, String hour, Date datetime) {
        Log.e(">>>>>",hour);
        TextView destinationView = (TextView) view;
        destinationView.setText(hour);
    }
}
