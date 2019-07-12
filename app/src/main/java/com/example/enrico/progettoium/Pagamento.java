package com.example.enrico.progettoium;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

public class Pagamento extends AppCompatActivity {

    Polizza polizza;
    TextView modelloVeicolo, targaVeicolo, listaAccessori, prezzoDaPagare, avvenutoPagamento;
    EditText numeroCarta, meseScadenza, annoScadenza, codiceCCV;


    Button pagaOra;
    String lista_acc;

    CheckBox accettazioneContratto;
    ImageView img_pagamento;

    public static final String POLIZZA_EXTRA="com.example.enrico.progettoium.Polizza";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(LeMiePolizze.POLIZZA_EXTRA);

        polizza=(Polizza)obj;


        accettazioneContratto = findViewById(R.id.accettazioneContratto);
        modelloVeicolo = (TextView)findViewById(R.id.modelloVeicolo);
        targaVeicolo = (TextView)findViewById(R.id.targaVeicolo);
        listaAccessori = (TextView)findViewById(R.id.listaAccessori);
        prezzoDaPagare = (TextView)findViewById(R.id.prezzoDaPagare);
        numeroCarta = (EditText)findViewById(R.id.nr_carta);
        meseScadenza = (EditText)findViewById(R.id.meseScadenzaCarta);
        annoScadenza = (EditText)findViewById(R.id.annoScadenzaCarta);
        codiceCCV = (EditText)findViewById(R.id.codiceCCV);
        pagaOra = (Button)findViewById(R.id.paga);
        avvenutoPagamento = (TextView)findViewById(R.id.avvenutoPagamento);

        avvenutoPagamento.setVisibility(View.GONE);


        modelloVeicolo.setText(polizza.getVeicolo().getModello());
        targaVeicolo.setText(polizza.getVeicolo().getTarga());
        listaAccessori.setText(polizza.getAccessori().toString());
        prezzoDaPagare.setText(String.valueOf(polizza.getPrezzo()) + "0");

        img_pagamento= findViewById(R.id.img_conferma);
        img_pagamento.setVisibility(View.GONE);
        lista_acc =polizza.getAccessori().toString();
        listaAccessori.setText(Html.fromHtml("<u>"+lista_acc+"</u>"));
        //datiInformativi.setText(Html.fromHtml("Il sottoscritto contraente, dichiara di aver preso visione delle clausole indicate nel foglio illustrativo disponibile al link: " + "<u>" + "www.allianz.it/clausole_polizze" +"</u>"));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_stat_name);
        toolbar.setTitle("Pagamento");// your drawable
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
            }
        });

        pagaOra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*numero_carta = numeroCarta.getText().toString();
                mese_scadenza = meseScadenza.getText().toString();
                anno_scadenza = annoScadenza.getText().toString();
                codice_ccv = codiceCCV.getText().toString();*/

                if (checkInput()){

                    pagaOra.setVisibility(View.GONE);
                    accettazioneContratto.setVisibility(View.GONE);
                    img_pagamento.setVisibility(View.VISIBLE);
                    avvenutoPagamento.setVisibility(View.VISIBLE);
                    avvenutoPagamento.setText("Il pagamento è stato effettuato e la polizza è stata correttamente " +
                            "attivata: riceverai una mail di conferma con allegata la ricevuta");
                    avvenutoPagamento.setTextColor(Color.rgb(0,153,51));
/*
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            // this code will be executed after 2 seconds
                            Intent i = new Intent(Pagamento.this,
                                    Menu.class);
                            startActivity(i);
                        }
                    }, 7000);*/
                }

            }
        });

        listaAccessori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu = new Intent(Pagamento.this,
                        ModificaPolizza.class);
                menu.putExtra(POLIZZA_EXTRA, polizza);
                startActivity(menu);
            }
        });


/*
        datiInformativi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.allianz.it"));
                    startActivity(intent); }
        });
*/


    }

    private boolean checkInput(){

        int errors=0;

        if (numeroCarta.getText() == null || numeroCarta.getText().length()!=16) {
            errors++;
            numeroCarta.setError("Inserisci un numero di carta valido");
        }
        else
            numeroCarta.setError(null);

        if (meseScadenza.getText() == null || meseScadenza.getText().length()==0){
            errors++;
            meseScadenza.setError("Inserisci un mese valido");
        }
        else {
            if(Integer.valueOf(meseScadenza.getText().toString())>12 || Integer.valueOf(meseScadenza.getText().toString())<1 )
            {
                meseScadenza.setError("Inserisci un mese valido");
                errors++;
            }
            else
                meseScadenza.setError(null);
        }

        if (annoScadenza.getText() == null || annoScadenza.getText().length()==0){
            errors++;
            annoScadenza.setError("Inserisci un anno valido");
        }
        else{
            if(Integer.valueOf(annoScadenza.getText().toString())>2029 || Integer.valueOf(annoScadenza.getText().toString())<2019 )
            {
                annoScadenza.setError("Inserisci un anno valido");
                errors++;
            }
            else
                annoScadenza.setError(null);
        }

        if (codiceCCV.getText() == null || codiceCCV.getText().length()!=3) {
            errors++;
            codiceCCV.setError("Inserisci un codice CCV valido");
        }
        else
            codiceCCV.setError(null);

        if(!accettazioneContratto.isChecked()){
            errors++;
            accettazioneContratto.setError("");
        }
        else{
            accettazioneContratto.setError(null);
        }

        return errors==0; // ritorna true se non ci sono errori

    }
}
