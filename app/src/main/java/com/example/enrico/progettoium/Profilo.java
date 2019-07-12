package com.example.enrico.progettoium;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

public class Profilo extends AppCompatActivity {

    EditText indirizzo, cellulare, email, carta_credito;
    TextView nome_cognome, msg_aggiornamento_dati;
    Button salva_dati;

    private String new_indirizzo, new_email, new_cellulare, new_carta_credito;
    Utente utente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_stat_name);
        toolbar.setTitle("Il mio profilo");// your drawable
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
            }
        });

        nome_cognome = (TextView)findViewById(R.id.nome_cognome);
        indirizzo=(EditText)findViewById(R.id.indirizzo);
        cellulare=(EditText)findViewById(R.id.cellulare);
        email=(EditText)findViewById(R.id.email);
        msg_aggiornamento_dati=(TextView)findViewById(R.id.msg_aggiornamento_dati);
        carta_credito=(EditText)findViewById(R.id.carta_credito);
        salva_dati=(Button)findViewById(R.id.salva_dati);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Menu.UTENTE_EXTRA);

        utente=(Utente)obj;

        nome_cognome.setText( utente.getNome() + " " + utente.getCognome());

        if(utente.getNewEmail()==null)
            email.setText(utente.getEmail());
        else
            email.setText(utente.getNewEmail());

        if(utente.getNewNrCartaCredito()==-1)
            carta_credito.setText(""+ utente.getNrCartaCredito());
        else
            carta_credito.setText("" + utente.getNewNrCartaCredito());

        if(utente.getNewIndirizzo()==null)
            indirizzo.setText(utente.getIndirizzo());
        else
            indirizzo.setText(utente.getNewIndirizzo());

        if(utente.getNewNrTelefono()==-1)
            cellulare.setText("" + utente.getNrTelefono());
        else
            cellulare.setText("" + utente.getNewNrTelefono());


        salva_dati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_indirizzo=indirizzo.getText().toString();
                new_email=email.getText().toString();
                new_carta_credito=carta_credito.getText().toString();
                new_cellulare=cellulare.getText().toString();

                utente.setNewIndirizzo(new_indirizzo);
                utente.setNewEmail(new_email);
                utente.setNewNrCartaCredito(Long.valueOf(new_carta_credito));
                utente.setNewNrTelefono(Long.valueOf(new_cellulare));

                Snackbar.make(v, "I tuoi dati sono stati aggiornati", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                //salva_dati.setVisibility(View.GONE);
            }
        });
    }

}
