package com.example.enrico.progettoium;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.Serializable;

public class MostraPreventivo extends AppCompatActivity {

    TextView datiVeicolo, prezzo;
    Polizza polizza;
    Button accetta, rifiuta;
    public static final String POLIZZA_EXTRA="com.example.enrico.progettoium.Polizza";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_preventivo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_stat_name);
        toolbar.setTitle("Dettaglio preventivo");// your drawable
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
            }
        });

        datiVeicolo = findViewById(R.id.datiVeicoloMostraPreventivo);
        prezzo = findViewById(R.id.prezzoPreventivo);
        accetta = findViewById(R.id.accettaPreventivo);
        rifiuta = findViewById(R.id.rifiutaPreventivo);


        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Preventivi.POLIZZA_EXTRA);

        polizza = (Polizza)obj;

        datiVeicolo.setText("\n- Targa:         " +polizza.getVeicolo().getTarga()+
                "\n- Modello:    " + polizza.getVeicolo().getModello() +
                "\n- Accessori: "+polizza.getAccessori().toString() + "\n");

        prezzo.setText("\nPremio calcolato: € " + Float.toString(polizza.getPrezzo()) +"0\n");


        accetta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pagamento = new Intent(MostraPreventivo.this,
                        Pagamento.class);

                pagamento.putExtra(POLIZZA_EXTRA, polizza);
                startActivity(pagamento);
            }
        });

        rifiuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accetta.setVisibility(View.GONE);
                rifiuta.setVisibility(View.GONE);
                Snackbar.make(v, "Il preventivo sarà eliminato definitivamente nelle prossime 24 ore.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
}
