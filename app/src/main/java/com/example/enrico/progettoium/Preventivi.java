package com.example.enrico.progettoium;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Preventivi extends AppCompatActivity {

    TextView prev1, prev2, prev3;
    Polizza p1, p2, p3;
    Veicolo v1, v2, v3;
    public static final String POLIZZA_EXTRA="com.example.enrico.progettoium.Polizza";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preventivi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_stat_name);
        toolbar.setTitle("I miei preventivi");// your drawable
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
            }
        });

        prev1=(TextView)findViewById(R.id.preventivo1);
        prev2=(TextView)findViewById(R.id.preventivo2);
        prev3=(TextView)findViewById(R.id.preventivo3);

        v1 = new Veicolo("Fiat 500X", "FR852LP", 1300);
        v2 = new Veicolo("Alfa Mito", "DR829AS", 1600);
        v3 = new Veicolo("Volkswagen Golf", "CT936TV", 2000);

        p1 = new Polizza(v1, 1, 1);
        p2 = new Polizza(v2, 2, 4);
        p3 = new Polizza(v3, 3, 9);

        p1.setAssistenza_stradale(true);
        p1.setFurto(true);
        p1.aggiornaAccessori();

        p2.setCristalli(true);
        p2.setIncendio(true);
        p2.aggiornaAccessori();

        p3.setAssistenza_stradale(true);
        p3.setKasko(true);
        p3.aggiornaAccessori();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Preventivi.this,
                        NuovoPreventivo.class);
                startActivity(i);
            }
        });

        prev1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Preventivi.this,
                        MostraPreventivo.class);
                i.putExtra(POLIZZA_EXTRA, p1);
                startActivity(i);
            }
        });

        prev2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Preventivi.this,
                        MostraPreventivo.class);
                i.putExtra(POLIZZA_EXTRA, p2);
                startActivity(i);
            }
        });


        prev3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Preventivi.this,
                        MostraPreventivo.class);
                i.putExtra(POLIZZA_EXTRA, p3);
                startActivity(i);
            }
        });




    }

}
