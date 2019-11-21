package co.edu.icesi.pdailyandroid.modals;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import co.edu.icesi.pdailyandroid.R;
import co.edu.icesi.pdailyandroid.viewmodel.EventViewModel;

public class RangeHourModal extends AppCompatActivity {

    private ImageView modalImage;
    private EventViewModel event;
    private TextView titleEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_range_hour_modal);

        event = (EventViewModel) getIntent().getExtras().getSerializable("event");

        modalImage = findViewById(R.id.modalImage);
        titleEvent = findViewById(R.id.titleEvent);

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
                        Log.e(">>>","atrapado!");
                    }
                    sequence = true;

                    contador += 1;
                    if (contador >= 19) {
                        contador = 0;
                        Thread.sleep(500);
                    }

                    runOnUiThread(()->{
                        String name = prefix+""+contador;
                        Log.e(">>>","frame: "+name);
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
}
