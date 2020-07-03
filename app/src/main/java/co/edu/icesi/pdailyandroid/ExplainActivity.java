package co.edu.icesi.pdailyandroid;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import co.edu.icesi.pdailyandroid.services.App;
import co.edu.icesi.pdailyandroid.util.JsonReaderUtils;
import pl.droidsonroids.gif.GifImageView;

public class ExplainActivity extends AppCompatActivity {

    private TextView explainType;
    private  String type;
    private Button chao;
    ConstraintLayout body;
    GifImageView GIF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain);

        type = getIntent().getStringExtra("EXTRA_FILENAME");
        chao = findViewById(R.id.chao);
        body = (ConstraintLayout) findViewById(R.id.body);
        GIF = (GifImageView) findViewById(R.id.GIF);

        explainType = findViewById(R.id.explainType);
        switch (type) {
            case "PD-NMS":
                explainType.setText(Html.fromHtml("A continuación se mostrará una serie de problemas.<br><br>Por favor, marque la casilla <b>SÍ</b> si ha tenido alguno durante el mes\n" +
                        "pasado. Si no ha tenido este problema durante el mes pasado\n" +
                        "marque <b>NO</b>."));
                body.setBackgroundResource(R.drawable.instruction_nms);
                GIF.setImageResource(R.drawable.type_b_gif);
                break;

            case "PD-CFRS":
                explainType.setText(Html.fromHtml("Para contestar las siguientes preguntas debe evitar pensar en sus sintomas motores y enfocarse en las dificultades cognitivas <b>(falta de atención, de memoria, lentitud mental, etc)</b> y como han afectado nuestro día a día durante las ultimas <b>dos semanas</b> ."));
                body.setBackgroundResource(R.drawable.instruction_cfrs);
                GIF.setImageResource(R.drawable.type_a_gif);
                break;

            case "Congelamiento de la marcha":
                explainType.setText(Html.fromHtml("A continuación se le mostrará una pregunta.<br><br>Responda que tan frecuente sintió este problema en el último mes "));
                body.setBackgroundResource(R.drawable.instruction_marcha);
                GIF.setImageResource(R.drawable.type_c_gif);
                break;

            case "PHQ-9":
                explainType.setText(Html.fromHtml("A continuación se le mostrar una serie de problemas.<br><br>Durante las últimas dos semanas:<br><b>¿Qué tan seguido ha sentido molestias debiado a los siguientes problemas?</b>"));
                body.setBackgroundResource(R.drawable.instruction_phq);
                GIF.setImageResource(R.drawable.type_a_gif);
                break;

            case "TMT":
                explainType.setText(Html.fromHtml("<b>TMT</b><br>Dibuje una línea alternando entre números y letras, respetando el orden numérico y alfabético.<br><br>Comience en <b>1</b> y finalice en <b>E</b>."));
                body.setBackgroundResource(R.drawable.instruction_moca);
                GIF.setImageResource(R.drawable.tmt_gif);
                break;

            case "WordsA":
                explainType.setText(Html.fromHtml("<b>Palabras</b><br>A continuación se mostrara una lista de palabras.<br><br>Lea cuidadosamente en voz alta la lista de palbras e intente guardarlas en su memoria"));
                body.setBackgroundResource(R.drawable.instruction_moca);
                GIF.setImageResource(R.drawable.words_a_gif);
                break;

            case "WordsACA":
                explainType.setText(Html.fromHtml("<b>Palabras</b><br>A continuación se mostrara una lista de palabras.<br><br>Lea cuidadosamente en voz alta la lista de palbras e intente guardarlas en su memoria"));
                body.setBackgroundResource(R.drawable.instruction_ca);
                GIF.setImageResource(R.drawable.words_a_gif);
                break;

            case "Subtract":
                explainType.setText(Html.fromHtml("<b>Resta</b><br>Por favor, reste de <b>7</b> en <b>7</b> comenzando en el número 100"));
                body.setBackgroundResource(R.drawable.instruction_moca);
                GIF.setImageResource(R.drawable.substract_gif);
                break;

            case "WordsB":
                explainType.setText(Html.fromHtml("<b>Palabras</b><br>A continuación se le iran mostran agrupaciones de palabras.<br><br>Seleccione las palabras que reconozca del listado que se le mostro previamente"));
                body.setBackgroundResource(R.drawable.instruction_moca);
                GIF.setImageResource(R.drawable.words_b_gif);
                break;

            case "WordsBCA":
                explainType.setText(Html.fromHtml("<b>Palabras</b><br>A continuación se le iran mostran agrupaciones de palabras.<br><br>Seleccione las palabras que reconozca del listado que se le mostro previamente"));
                body.setBackgroundResource(R.drawable.instruction_ca);
                GIF.setImageResource(R.drawable.words_b_gif);
                break;

            case "Letras":
                explainType.setText(Html.fromHtml("<b>Letras</b><br>A continuación se mostrara una secuencia de letras.<br><br>Por favor, toque la pantalla SOLO cuando la letra <b>A</b> aparezca en pantalla."));
                body.setBackgroundResource(R.drawable.instruction_moca);
                GIF.setImageResource(R.drawable.letters_gif);
                break;

            case "GONGO":
                explainType.setText(Html.fromHtml("Identifique la letra P.<br><br>Cada vez que aparezca la letra <b>P</b> en la pantalla, presione <b>SI</b>, de lo contrario presione <b>NO</b>."));
                body.setBackgroundResource(R.drawable.instruction_go);
                GIF.setImageResource(R.drawable.go_gif);
                break;

            case "clock":
                explainType.setText(Html.fromHtml("<b>Reloj</b><br>A continuación encontrarás un lienzo en blanco donde puedes dibujar.<br><br>Dibuja un reloj que marque exactamente las <b>11:10</b>"));
                body.setBackgroundResource(R.drawable.instruction_ca);
                GIF.setImageResource(R.drawable.clock_gif);
                break;
            case "speech":
                explainType.setText(Html.fromHtml("<b>Dictado</b><br>A continuación veras una letra en pantallas.<br><br>Tienes un minuto para grabar la mayor cantidad de palabras que comiencen con esta letra"));
                body.setBackgroundResource(R.drawable.instruction_ca);
                GIF.setImageResource(R.drawable.words_a_gif);
                break;
        }



        chao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent;
                switch (type) {
                    case "PD-NMS":
                        intent= new Intent(getBaseContext(), FormActivity.class);
                        intent.putExtra("EXTRA_FILENAME", "PD-NMS");
                        startActivity(intent);
                        break;

                    case "PD-CFRS":
                        intent= new Intent(getBaseContext(), FormActivity.class);
                        intent.putExtra("EXTRA_FILENAME", "PD-CFRS");
                        startActivity(intent);
                        break;

                    case "Congelamiento de la marcha":
                        intent= new Intent(getBaseContext(), FormActivity.class);
                        intent.putExtra("EXTRA_FILENAME", "Congelamiento de la marcha");
                        startActivity(intent);
                        break;

                    case "PHQ-9":
                        intent= new Intent(getBaseContext(), FormActivity.class);
                        intent.putExtra("EXTRA_FILENAME", "PHQ-9");
                        startActivity(intent);
                        break;

                    case "TMT":
                        intent= new Intent(getBaseContext(), MocaActivity.class);
                        intent.putExtra("EXTRA_FILENAME", "TMT");
                        startActivity(intent);
                        break;

                    case "WordsA":
                        intent= new Intent(getBaseContext(), MocaActivity.class);
                        intent.putExtra("EXTRA_FILENAME", "WordsA");
                        startActivity(intent);
                        break;

                    case "WordsACA":
                        intent= new Intent(getBaseContext(), CaTestActivity.class);
                        intent.putExtra("EXTRA_FILENAME", "WordsACA");
                        startActivity(intent);
                        break;

                    case "Subtract":
                        intent= new Intent(getBaseContext(), MocaActivity.class);
                        intent.putExtra("EXTRA_FILENAME", "Subtract");
                        startActivity(intent);
                        break;

                    case "Letras":
                        intent= new Intent(getBaseContext(), MocaActivity.class);
                        intent.putExtra("EXTRA_FILENAME", "Letras");
                        startActivity(intent);
                        break;

                    case "WordsB":
                        intent= new Intent(getBaseContext(), MocaActivity.class);
                        intent.putExtra("EXTRA_FILENAME", "WordsB");
                        startActivity(intent);
                        break;

                    case "WordsBCA":
                        intent= new Intent(getBaseContext(), CaTestActivity.class);
                        intent.putExtra("EXTRA_FILENAME", "WordsBCA");
                        startActivity(intent);
                        break;

                    case "GONGO":
                        intent= new Intent(getBaseContext(), GongoTaskActivity.class);
                        intent.putExtra("EXTRA_FILENAME", "GONGO");
                        startActivity(intent);
                        break;

                    case "clock":
                        intent= new Intent(getBaseContext(), CaTestActivity.class);
                        intent.putExtra("EXTRA_FILENAME", "clock");
                        startActivity(intent);
                        break;

                    case "speech":
                        intent= new Intent(getBaseContext(), CaTestActivity.class);
                        intent.putExtra("EXTRA_FILENAME", "speech");
                        startActivity(intent);
                        break;
                }


            }
        });

    }
}