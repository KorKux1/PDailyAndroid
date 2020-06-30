package co.edu.icesi.pdailyandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import co.edu.icesi.pdailyandroid.cognosis.data.DataScore;

public class SelectionActivity extends AppCompatActivity {
    ImageView bgapp;
    Animation bganim;
    ConstraintLayout textHolder, seleccion, practicas;


    private Button bsOne, bsTwo, bsThree, bsFour, bsFive, bsSix, bsSeven, bsEight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        DataScore dataScore = DataScore.getInstance();

        bgapp = (ImageView) findViewById(R.id.fondo_animado);
        textHolder = (ConstraintLayout) findViewById(R.id.Holder);
        seleccion = (ConstraintLayout) findViewById(R.id.seleccion);
        practicas = (ConstraintLayout) findViewById(R.id.practicas);
        bganim = AnimationUtils.loadAnimation(this,R.anim.bganim);


        Display mDisplay  = getWindowManager().getDefaultDisplay();
        float height = mDisplay.getHeight();

        bgapp.animate().translationY((float) (-height*1.5)).setDuration(1000).setStartDelay(1500);

        textHolder.startAnimation(bganim);
        seleccion.startAnimation(bganim);
        practicas.startAnimation(bganim);

        bsOne = findViewById(R.id.ButtonSelectionOne);
        bsTwo = findViewById(R.id.ButtonSelectionTwo);
        bsThree = findViewById(R.id.ButtonSelectionThree);
        bsFour = findViewById(R.id.ButtonSelectionFour);

        bsSix = findViewById(R.id.ButtonSelectionSix);
        bsSeven = findViewById(R.id.ButtonSelectionSeven);
        bsEight = findViewById(R.id.ButtonSelectionEight);

        bsOne.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ExplainActivity.class);
                intent.putExtra("EXTRA_FILENAME", "PD-NMS");
                dataScore.setTestSelected("PD-NMS");
                startActivity(intent);
            }
        });

        bsTwo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ExplainActivity.class);
                intent.putExtra("EXTRA_FILENAME", "PD-CFRS");
                dataScore.setTestSelected("PD-CFRS");
                startActivity(intent);
            }
        });

        bsThree.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ExplainActivity.class);
                intent.putExtra("EXTRA_FILENAME", "Congelamiento de la marcha");
                dataScore.setTestSelected("Congelamiento de la marcha");
                startActivity(intent);
            }
        });

        bsFour.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ExplainActivity.class);
                intent.putExtra("EXTRA_FILENAME", "PHQ-9");
                dataScore.setTestSelected("PHQ-9");
                startActivity(intent);
            }
        });

        bsSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ExplainActivity.class);
                intent.putExtra("EXTRA_FILENAME", "TMT");
                dataScore.setTestSelected("TMT");
                startActivity(intent);
            }
        });

        bsSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ExplainActivity.class);
                intent.putExtra("EXTRA_FILENAME", "GONGO");
                dataScore.setTestSelected("GONGO");
                startActivity(intent);
            }
        });

        bsEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ExplainActivity.class);
                intent.putExtra("EXTRA_FILENAME", "WordsACA");

                startActivity(intent);
            }
        });
    }
}
