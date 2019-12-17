package co.edu.icesi.pdailyandroid.modals;

import androidx.appcompat.app.AppCompatActivity;
import co.edu.icesi.pdailyandroid.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class BodySelectorModal extends AppCompatActivity {

    private Button turnButton;
    private Button finishButton;
    private ImageView bodyImage;

    private boolean bodyIsFront = false;

    private ArrayList<Integer> zones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_selector_modal);

        turnButton = findViewById(R.id.turnButton);
        finishButton = findViewById(R.id.finishButton);
        bodyImage = findViewById(R.id.bodyImage);
        zones = new ArrayList<>();


        turnButton.setOnClickListener((v)->{
            bodyImage.setImageResource(bodyIsFront?R.drawable.cuerpo1a:R.drawable.cuerpo1b);
            bodyIsFront = !bodyIsFront;
            showIndicators();
        });

        finishButton.setOnClickListener((v)->{
            finish();
        });
    }

    public void onZoneClick(View b){
        Button button = (Button) b;

        int zone = -1;
        switch (button.getId()){
            case R.id.zone0:
                zone = 0;
                break;
            case R.id.zone1:
                zone = 1;
                break;
            case R.id.zone2:
                zone = 2;
                break;
            case R.id.zone3:
                zone = 3;
                break;
            case R.id.zone4:
                zone = 4;
                break;
            case R.id.zone5:
                zone = 5;
                break;
            case R.id.zone6:
                zone = 6;
                break;
            case R.id.zone7:
                zone = 7;
                break;
            case R.id.zone8:
                zone = 8;
                break;
            case R.id.zone9:
                zone = 9;
                break;
        }
        zone = bodyIsFront ? zone:zone+10;
        if(zones.contains(zone)){
            zones.remove( Integer.valueOf(zone) );
        }else{
            zones.add( zone );
        }

        showIndicators();
    }

    private void showIndicators() {
        hideAllIndicators();
        if(bodyIsFront){
            if(zones.contains(0)) findViewById(R.id.indicator0).setVisibility(View.VISIBLE);
            if(zones.contains(1)) findViewById(R.id.indicator1).setVisibility(View.VISIBLE);
            if(zones.contains(2)) findViewById(R.id.indicator2).setVisibility(View.VISIBLE);
            if(zones.contains(3)) findViewById(R.id.indicator3).setVisibility(View.VISIBLE);
            if(zones.contains(4)) findViewById(R.id.indicator4).setVisibility(View.VISIBLE);
            if(zones.contains(5)) findViewById(R.id.indicator5).setVisibility(View.VISIBLE);
            if(zones.contains(6)) findViewById(R.id.indicator6).setVisibility(View.VISIBLE);
            if(zones.contains(7)) findViewById(R.id.indicator7).setVisibility(View.VISIBLE);
            if(zones.contains(8)) findViewById(R.id.indicator8).setVisibility(View.VISIBLE);
            if(zones.contains(9)) findViewById(R.id.indicator9).setVisibility(View.VISIBLE);
        }else{
            if(zones.contains(10)) findViewById(R.id.indicator0).setVisibility(View.VISIBLE);
            if(zones.contains(11)) findViewById(R.id.indicator1).setVisibility(View.VISIBLE);
            if(zones.contains(12)) findViewById(R.id.indicator2).setVisibility(View.VISIBLE);
            if(zones.contains(13)) findViewById(R.id.indicator3).setVisibility(View.VISIBLE);
            if(zones.contains(14)) findViewById(R.id.indicator4).setVisibility(View.VISIBLE);
            if(zones.contains(15)) findViewById(R.id.indicator5).setVisibility(View.VISIBLE);
            if(zones.contains(16)) findViewById(R.id.indicator6).setVisibility(View.VISIBLE);
            if(zones.contains(17)) findViewById(R.id.indicator7).setVisibility(View.VISIBLE);
            if(zones.contains(18)) findViewById(R.id.indicator8).setVisibility(View.VISIBLE);
            if(zones.contains(19)) findViewById(R.id.indicator9).setVisibility(View.VISIBLE);
        }
    }

    private void hideAllIndicators() {
        findViewById(R.id.indicator0).setVisibility(View.GONE);
        findViewById(R.id.indicator1).setVisibility(View.GONE);
        findViewById(R.id.indicator2).setVisibility(View.GONE);
        findViewById(R.id.indicator3).setVisibility(View.GONE);
        findViewById(R.id.indicator4).setVisibility(View.GONE);
        findViewById(R.id.indicator5).setVisibility(View.GONE);
        findViewById(R.id.indicator6).setVisibility(View.GONE);
        findViewById(R.id.indicator7).setVisibility(View.GONE);
        findViewById(R.id.indicator8).setVisibility(View.GONE);
        findViewById(R.id.indicator9).setVisibility(View.GONE);
    }

}
