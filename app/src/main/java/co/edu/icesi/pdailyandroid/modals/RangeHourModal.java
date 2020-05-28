package co.edu.icesi.pdailyandroid.modals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.misc.dialogs.HourDialog;
import co.edu.icesi.pdailyandroid.util.DateUtils;
import co.edu.icesi.pdailyandroid.viewmodel.EventViewModel;

public class RangeHourModal extends AppCompatActivity implements HourDialog.OnHourChoose {

    private ImageView modalImage;
    private EventViewModel event;
    private TextView titleEvent;

    private Calendar calendarFrom;
    private Calendar calendarTo;

    private TextView fromhour;
    private TextView tohour;
    private Button nextButton, backButton;

    private int[] congelamiento, temblor, tropezones, lentificacion, discinesias, caidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_range_hour_modal);

        modalImage = findViewById(R.id.modalImage);
        titleEvent = findViewById(R.id.titleEvent);
        nextButton = findViewById(R.id.nextButton);
        fromhour = findViewById(R.id.fromhour);
        tohour = findViewById(R.id.tohour);

        calendarFrom = Calendar.getInstance();
        calendarTo = Calendar.getInstance();
        calendarTo.add(Calendar.MINUTE, 10);

        fromhour.setText( DateUtils.getHourInString(calendarFrom) );
        tohour.setText( DateUtils.getHourInString(calendarTo) );

        backButton = findViewById(R.id.backButton);

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

        lentificacion = new int[36];
        lentificacion[0] = R.drawable.len1;
        lentificacion[1] = R.drawable.len2;
        lentificacion[2] = R.drawable.len3;
        lentificacion[3] = R.drawable.len4;
        lentificacion[4] = R.drawable.len5;
        lentificacion[5] = R.drawable.len6;
        lentificacion[6] = R.drawable.len7;
        lentificacion[7] = R.drawable.len8;
        lentificacion[8] = R.drawable.len9;
        lentificacion[9] = R.drawable.len10;
        lentificacion[10] = R.drawable.len11;
        lentificacion[11] = R.drawable.len12;
        lentificacion[12] = R.drawable.len13;
        lentificacion[13] = R.drawable.len14;
        lentificacion[14] = R.drawable.len15;
        lentificacion[15] = R.drawable.len16;
        lentificacion[16] = R.drawable.len17;
        lentificacion[17] = R.drawable.len18;
        lentificacion[18] = R.drawable.len19;
        lentificacion[19] = R.drawable.len20;
        lentificacion[20] = R.drawable.len21;
        lentificacion[21] = R.drawable.len22;
        lentificacion[22] = R.drawable.len23;
        lentificacion[23] = R.drawable.len24;
        lentificacion[24] = R.drawable.len25;
        lentificacion[25] = R.drawable.len26;
        lentificacion[26] = R.drawable.len27;
        lentificacion[27] = R.drawable.len28;
        lentificacion[28] = R.drawable.len29;
        lentificacion[29] = R.drawable.len30;
        lentificacion[30] = R.drawable.len31;
        lentificacion[31] = R.drawable.len32;
        lentificacion[32] = R.drawable.len33;
        lentificacion[33] = R.drawable.len34;
        lentificacion[34] = R.drawable.len35;
        lentificacion[35] = R.drawable.len36;

        discinesias = new int[19];
        discinesias[0] = R.drawable.dis0;
        discinesias[1] = R.drawable.dis1;
        discinesias[2] = R.drawable.dis2;
        discinesias[3] = R.drawable.dis3;
        discinesias[4] = R.drawable.dis4;
        discinesias[5] = R.drawable.dis5;
        discinesias[6] = R.drawable.dis6;
        discinesias[7] = R.drawable.dis7;
        discinesias[8] = R.drawable.dis8;
        discinesias[9] = R.drawable.dis9;
        discinesias[10] = R.drawable.dis10;
        discinesias[11] = R.drawable.dis11;
        discinesias[12] = R.drawable.dis12;
        discinesias[13] = R.drawable.dis13;
        discinesias[14] = R.drawable.dis14;
        discinesias[15] = R.drawable.dis15;
        discinesias[16] = R.drawable.dis16;
        discinesias[17] = R.drawable.dis17;
        discinesias[18] = R.drawable.dis18;

        caidas = new int[19];
        caidas[0] = R.drawable.cai0;
        caidas[1] = R.drawable.cai1;
        caidas[2] = R.drawable.cai2;
        caidas[3] = R.drawable.cai3;
        caidas[4] = R.drawable.cai4;
        caidas[5] = R.drawable.cai5;
        caidas[6] = R.drawable.cai6;
        caidas[7] = R.drawable.cai7;
        caidas[8] = R.drawable.cai8;
        caidas[9] = R.drawable.cai9;
        caidas[10] = R.drawable.cai10;
        caidas[11] = R.drawable.cai11;
        caidas[12] = R.drawable.cai12;
        caidas[13] = R.drawable.cai13;
        caidas[14] = R.drawable.cai14;
        caidas[15] = R.drawable.cai15;
        caidas[16] = R.drawable.cai16;
        caidas[17] = R.drawable.cai17;
        caidas[18] = R.drawable.cai18;
        

        event = (EventViewModel) getIntent().getExtras().getSerializable("event");


        fromhour.setOnClickListener(
            (v)->{
                animPause = true;
                HourDialog dialog = new HourDialog();
                dialog.setOriginView(v);
                dialog.setHour( ( (TextView) v ).getText().toString() );
                dialog.setOnHourChooseListener(this);
                dialog.show(getSupportFragmentManager(), "hourDialog");
            }
        );

        tohour.setOnClickListener(
                (v)->{
                    animPause = true;
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
                    i.putExtra("from", calendarFrom.getTime().getTime());
                    i.putExtra("to", calendarTo.getTime().getTime());
                    setResult(RESULT_OK, i);
                    finish();
                }
        );

        backButton.setOnClickListener(
                (v)->{
                    onBackPressed();
                }
        );

        titleEvent.setText(event.getName());
        switch (event.getName()){
            case "Congelamiento":
                runAnimation(congelamiento);
                break;
            case "Lentificación":
                runAnimation(lentificacion);
                break;
            case "Discinesias":
                runAnimation(discinesias);
                break;
            case "Temblor":
                runAnimation(temblor);
                break;
            case "Tropezones":
                runAnimation(tropezones);
                break;
            case "Caídas":
                runAnimation(caidas);
                break;
        }

    }


    private int contador = 0;
    private boolean animLive = true;
    private boolean animPause = false;

    public void runAnimation(int[] array) {
        new Thread(() -> {
                try {
                    while (animLive) {

                        runOnUiThread(() -> {
                            modalImage.setImageResource(array[contador]);
                        });

                        Thread.sleep(100);

                        contador += 1;
                        if (contador >= array.length) {
                            contador = 0;
                            Thread.sleep(500);
                        }
                        while(animPause){}
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
    public void onHour(View view, Date datetime) {
        animPause = false;
        TextView destinationView = (TextView) view;
        switch (view.getId()){
            case R.id.fromhour:
                calendarFrom.setTime(datetime);
                destinationView.setText(DateUtils.getHourInString(calendarFrom));
                break;
            case R.id.tohour:
                calendarTo.setTime(datetime);
                destinationView.setText(DateUtils.getHourInString(calendarTo));
                break;
        }

    }
}
