package co.edu.icesi.pdailyandroid.modals;

import androidx.appcompat.app.AppCompatActivity;
import co.edu.icesi.pdailyandroid.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class BodyModal extends AppCompatActivity {


    private static final String PIERNA_IZQUIERDA_FRONTAL = "6b3f6738-b471-40d5-acd9-3d8dfd7e3854";
    private static final String PIERNA_DERECHA_FRONTAL = "e64a9508-0031-42f4-86af-1925188b3488";
    private static final String PIERNA_DERECHA_TRASERA = "fb625c59-ca31-4007-ade2-14863cc9ebd4";
    private static final String PIE_IZQUIERDO_TRASERO = "7642195e-15af-453f-aaca-a37bbfe09272";
    private static final String PALMA_MANO_DERECHA = "bd0823d7-990d-4562-a132-7bf65a657a40";
    private static final String MANO_DERECHA_EXTERIOR = "55980899-26dd-4e9d-88e9-0c171c31c825";
    private static final String BRAZO_IZQUIERDO_FRONTAL = "7bf8b809-53ae-4d26-8430-631117b13604";
    private static final String BRAZO_DERECHO_TRASERO = "7d1b6d3f-9d9b-4c8b-9f38-d1750fbe4933";
    private static final String ESPALDA = "85efc822-8693-4147-8e39-9f2bfd4ad1cb";
    private static final String ROSTRO = "30c0bca3-0189-4962-be52-e02de69a2664";
    private static final String PIE_DERECHO_TRASERO = "35ad0d07-3654-442e-8a8a-96beec296925";
    private static final String PIE_IZQUIERDO_FRONTAL = "3570a4ae-0f03-4bd4-bdb3-3da27b0c0fca";
    private static final String PIERNA_IZQUIERDA_TRASERA = "6346f102-9cf5-4047-b829-62ea188df7ef";
    private static final String CABEZA_TRASERA = "2f80921c-51cb-4171-9fbe-5cbad30a03b5";
    private static final String BRAZO_DERECHO_FRONTAL = "210cc5e3-5819-4fd7-81ee-5d18c88e274a";
    private static final String TRONCO = "bd441a93-0fdf-4f75-8025-420efe2bf518";
    private static final String BRAZO_IZQUIERDO_TRASERO = "193781e1-ac2c-48b1-8f61-791d5b4de0a5";
    private static final String PIE_DERECHO_FRONTAL = "662ca513-37d1-43d7-9e28-693083697e2c";
    private static final String PALMA_MANO_IZQUIERDA = "4cde7fa8-4e24-4cef-8cc1-78090ae85a94";
    private static final String MANO_IZQUIERDA_EXTERIOR = "03800d04-b814-4833-be69-d35b742c41a9";


    private Button turnButton;
    private Button finishButton;
    private ImageView bodyImage;
    private String name;

    private boolean bodyIsFront = true;

    private ArrayList<String> zones;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_selector_modal);

        name = getIntent().getExtras().get("name").toString();

        turnButton = findViewById(R.id.turnButton);
        finishButton = findViewById(R.id.finishButton);
        bodyImage = findViewById(R.id.bodyImage);
        zones = new ArrayList<>();


        turnButton.setOnClickListener((v)->{
            bodyIsFront = !bodyIsFront;
            bodyImage.setImageResource(bodyIsFront?R.drawable.cuerpo1a:R.drawable.cuerpo1b);
            showIndicators();
        });

        finishButton.setOnClickListener((v)->{
            Intent i = new Intent();
            i.putExtra("name", name);
            i.putExtra( "bodyList", new Gson().toJson(zones) );
            setResult(RESULT_OK, i);
            finish();
        });
        showIndicators();
    }

    public void onZoneClick(View b){
        Button button = (Button) b;
        String zone = null;
        switch (button.getId()){
            case R.id.zone0:
                if(bodyIsFront) zone = ROSTRO;
                else zone = CABEZA_TRASERA;
                break;
            case R.id.zone1:
                if(bodyIsFront) zone = TRONCO;
                else zone = ESPALDA;
                break;
            case R.id.zone2:
                if(bodyIsFront) zone = BRAZO_DERECHO_FRONTAL;
                else zone = BRAZO_DERECHO_TRASERO;
                break;
            case R.id.zone3:
                if(bodyIsFront) zone = BRAZO_IZQUIERDO_FRONTAL;
                else zone = BRAZO_IZQUIERDO_TRASERO;
                break;
            case R.id.zone4:
                if(bodyIsFront) zone = MANO_DERECHA_EXTERIOR;
                else zone = PALMA_MANO_DERECHA;
                break;
            case R.id.zone5:
                if(bodyIsFront) zone = MANO_IZQUIERDA_EXTERIOR;
                else zone = PALMA_MANO_IZQUIERDA;
                break;
            case R.id.zone6:
                if(bodyIsFront) zone = PIERNA_DERECHA_FRONTAL;
                else zone = PIERNA_DERECHA_TRASERA;
                break;
            case R.id.zone7:
                if(bodyIsFront) zone = PIERNA_IZQUIERDA_FRONTAL;
                else zone = PIERNA_IZQUIERDA_TRASERA;
                break;
            case R.id.zone8:
                if(bodyIsFront) zone = PIE_DERECHO_FRONTAL;
                else zone = PIE_DERECHO_TRASERO;
                break;
            case R.id.zone9:
                if(bodyIsFront) zone = PIE_IZQUIERDO_FRONTAL;
                else zone = PIE_IZQUIERDO_TRASERO;
                break;
        }
        if(zones.contains(zone)){
            int index = zones.indexOf(zone);
            zones.remove( index );
        }else{
            zones.add( zone );
        }
        showIndicators();
    }

    private void showIndicators() {
        hideAllIndicators();
        if(bodyIsFront){
            if(zones.contains(ROSTRO)) findViewById(R.id.indicator0).setVisibility(View.VISIBLE);
            if(zones.contains(TRONCO)) findViewById(R.id.indicator1).setVisibility(View.VISIBLE);
            if(zones.contains(BRAZO_DERECHO_FRONTAL)) findViewById(R.id.indicator2).setVisibility(View.VISIBLE);
            if(zones.contains(BRAZO_IZQUIERDO_FRONTAL)) findViewById(R.id.indicator3).setVisibility(View.VISIBLE);
            if(zones.contains(MANO_DERECHA_EXTERIOR)) findViewById(R.id.indicator4).setVisibility(View.VISIBLE);
            if(zones.contains(MANO_IZQUIERDA_EXTERIOR)) findViewById(R.id.indicator5).setVisibility(View.VISIBLE);
            if(zones.contains(PIERNA_DERECHA_FRONTAL)) findViewById(R.id.indicator6).setVisibility(View.VISIBLE);
            if(zones.contains(PIERNA_IZQUIERDA_FRONTAL)) findViewById(R.id.indicator7).setVisibility(View.VISIBLE);
            if(zones.contains(PIE_DERECHO_FRONTAL)) findViewById(R.id.indicator8).setVisibility(View.VISIBLE);
            if(zones.contains(PIE_IZQUIERDO_FRONTAL)) findViewById(R.id.indicator9).setVisibility(View.VISIBLE);
        }else{
            if(zones.contains(CABEZA_TRASERA)) findViewById(R.id.indicator0).setVisibility(View.VISIBLE);
            if(zones.contains(ESPALDA)) findViewById(R.id.indicator1).setVisibility(View.VISIBLE);
            if(zones.contains(BRAZO_DERECHO_TRASERO)) findViewById(R.id.indicator2).setVisibility(View.VISIBLE);
            if(zones.contains(BRAZO_IZQUIERDO_TRASERO)) findViewById(R.id.indicator3).setVisibility(View.VISIBLE);
            if(zones.contains(PALMA_MANO_DERECHA)) findViewById(R.id.indicator4).setVisibility(View.VISIBLE);
            if(zones.contains(PALMA_MANO_IZQUIERDA)) findViewById(R.id.indicator5).setVisibility(View.VISIBLE);
            if(zones.contains(PIERNA_DERECHA_TRASERA)) findViewById(R.id.indicator6).setVisibility(View.VISIBLE);
            if(zones.contains(PIERNA_IZQUIERDA_TRASERA)) findViewById(R.id.indicator7).setVisibility(View.VISIBLE);
            if(zones.contains(PIE_DERECHO_TRASERO)) findViewById(R.id.indicator8).setVisibility(View.VISIBLE);
            if(zones.contains(PIE_IZQUIERDO_TRASERO)) findViewById(R.id.indicator9).setVisibility(View.VISIBLE);
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

    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        i.putExtra("name", name);
        setResult(RESULT_CANCELED, i);
        finish();
    }
}
