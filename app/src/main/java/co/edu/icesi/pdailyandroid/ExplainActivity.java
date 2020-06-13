package co.edu.icesi.pdailyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ExplainActivity extends AppCompatActivity {

    private TextView explainType;
    private  String type;
    private Button chao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain);

        type = getIntent().getStringExtra("EXTRA_FILENAME");
        chao = findViewById(R.id.chao);

        explainType = findViewById(R.id.explainType);
        switch (type) {
            case "PD-NMS":
                explainType.setText("A continuación se le mostrara una serie de problemas. Por favor, marque la casilla (SI) si ha tenido alguno durante el ultimo mes, marque (NO) si no ha tenido ese problema dutante el ultimo mes. Si ha tenido el problema pero no durante el ultimo mes, tambien debe marcar (NO).");
                break;

            case "PD-CFRS":
                explainType.setText("Para contestar las siguientes preguntas ustede debe evitar pensar en sus sintomas motores y enfocarse en las dificultades cognitivas (falta de atención de memoria, enlentecimiento mental, etc) han afectado nuestros día a día durante las ultimas dos semanas.");
                break;

            case "Congelamiento de la marcha":
                explainType.setText("A continuación se le mostrará una pregunta, responda que tan frecuente sintió este problema en el último mes ");
                 break;

            case "PHQ-9":
                explainType.setText("A continuación se le mostrar una serie de problemas. Durante las últimas dos semanas, ¿qué tan seguido ha sentido molestias debiado a los siguientes problemas?");
                break;

            case "TMT":
                explainType.setText("Construya una secuencia número-alfabética lógica en el menor tiempo posible uniendo los siguientes numero y letras, iniciando desde el número (1) y finalizando en la letra (E). ");
                break;

            case "WordsA":
                explainType.setText("Lea en voz alta la siguiente lista de palbras e intente memorizarlas");
                break;

            case "WordsB":
                explainType.setText("A continuacion ne se que tienes que hacer jijiji");
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

                    case "WordsB":
                        intent= new Intent(getBaseContext(), MocaActivity.class);
                        intent.putExtra("EXTRA_FILENAME", "WordsB");
                        startActivity(intent);
                        break;
                }


            }
        });

    }
}