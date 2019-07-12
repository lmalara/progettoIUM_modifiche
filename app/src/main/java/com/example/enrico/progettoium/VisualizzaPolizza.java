package com.example.enrico.progettoium;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.Serializable;
import java.util.Iterator;

public class VisualizzaPolizza extends AppCompatActivity {

    Polizza polizza;
    public static final String POLIZZA_EXTRA="com.example.enrico.progettoium.Polizza";

    TextView infoVeicolo, statoPolizza, accessori, totalePremio;
    String listaAccessori;
    Button pagaOra, download;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza_polizza);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(LeMiePolizze.POLIZZA_EXTRA);

            polizza=(Polizza)obj;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_stat_name);
        toolbar.setTitle("Dettaglio polizza");// your drawable
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
            }
        });


        infoVeicolo=(TextView)findViewById(R.id.infoVeicolo);
        totalePremio=(TextView)findViewById(R.id.totalePremio);
        statoPolizza=(TextView)findViewById(R.id.statoPolizza);
        accessori=(TextView)findViewById(R.id.accessori);

        pagaOra=(Button)findViewById(R.id.pagaOra);
        download=(Button)findViewById(R.id.downloadCertificato);

        infoVeicolo.setText("Veicolo:                " + polizza.getVeicolo().getModello() + "\n" +
                            "Targa:                    " + polizza.getVeicolo().getTarga() + "\n\n" +
                            "N° Polizza:            " + polizza.getNumeroPolizza() + "\n"+
                            "Classe di Merito: " + polizza.getClasseMerito() + "\n" +
                            "Data Scadenza:   " + polizza.getMeseScadenza()+ "/" +
                                                polizza.getAnnoScadenza() + "\n"

        );
        totalePremio.setText("Premio:                 € " + polizza.getPrezzo() + "0");


        listaAccessori=polizza.getAccessori().toString();
        accessori.setText(listaAccessori);



        if(polizza.isAttiva()==true){
            statoPolizza.setText("POLIZZA ATTIVA");
            statoPolizza.setTextColor(Color.rgb(0,153,51));
        }
        if(polizza.isInScadenza()==true){
            statoPolizza.setText("POLIZZA IN SCADENZA");
            //colore giallo
            statoPolizza.setTextColor(Color.rgb(255,215,0));
            pagaOra.setVisibility(View.VISIBLE);
        }
        if(polizza.isAttiva()==false){
            statoPolizza.setText("POLIZZA SCADUTA");
            statoPolizza.setTextColor(Color.RED);
            pagaOra.setVisibility(View.VISIBLE);
        }
/*
        if(polizza.getAnnoScadenza()<2019){
            statoPolizza.setText("POLIZZA SCADUTA");
            statoPolizza.setTextColor(Color.RED);
            pagaOra.setVisibility(View.VISIBLE);
        }
        else if(polizza.getAnnoScadenza()==2019){
                if(polizza.getMeseScadenza()>=2){
                    statoPolizza.setText("POLIZZA ATTIVA");
                    statoPolizza.setTextColor(Color.GREEN);
                }
                else{
                    statoPolizza.setText("POLIZZA SCADUTA");
                    statoPolizza.setTextColor(Color.RED);
                    pagaOra.setVisibility(View.VISIBLE);
                }
             }
             else{
                statoPolizza.setText("POLIZZA ATTIVA");
                statoPolizza.setTextColor(Color.GREEN);
             }


             if(polizza.getMeseScadenza()==2 && polizza.getAnnoScadenza()==2019){
                statoPolizza.setText("POLIZZA IN SCADENZA");
                //colore arancione
                statoPolizza.setTextColor(Color.rgb(255,215,0));
                pagaOra.setVisibility(View.VISIBLE);
             }
*/
        pagaOra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aggiornaIcona(1);
                //pagaOra.setVisibility(View.GONE);
                Intent pagamento = new Intent(VisualizzaPolizza.this,
                        Pagamento.class);

                pagamento.putExtra(POLIZZA_EXTRA, polizza);
                startActivity(pagamento);
            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Download in corso ...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }


    public void aggiornaIcona(int stato){
        switch (stato){
            case 1:
                statoPolizza.setText("POLIZZA ATTIVA");
                statoPolizza.setTextColor(Color.GREEN);
                break;
            case -1:
                statoPolizza.setText("POLIZZA SCADUTA");
                statoPolizza.setTextColor(Color.GREEN);

                break;
            case 0:
                statoPolizza.setText("POLIZZA IN SCADENZA");
                statoPolizza.setTextColor(Color.rgb(255, 215 ,0));
                break;
        }
    }
}
