package com.example.enrico.progettoium;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.text.style.ForegroundColorSpan;

import java.util.ArrayList;

public class LeMiePolizze extends AppCompatActivity {

    Veicolo v1 = new Veicolo("Toyota Yaris", "EK729FG", 1400);
    Veicolo v2 = new Veicolo("Fiat Punto", "BN780AA", 1300);
    Veicolo v3 = new Veicolo("Lancia Ypsilon", "DE154LR", 1200 );
    Polizza p1 = new Polizza(v1, 1, 10);
    Polizza p2 = new Polizza(v2, 2, 10);
    Polizza p3 = new Polizza(v3, 3, 10);

    public TextView modello_v1, modello_v2, modello_v3;
    Button vedi1, vedi2, vedi3, modifica1, modifica2, modifica3;

    public static final String POLIZZA_EXTRA="com.example.enrico.progettoium.Polizza";
    public static ArrayList<Polizza> polizze = new ArrayList<>(3);

    public static int stato1=1, stato2=-1, stato3=0; //1:attiva, -1:scaduta, 0:in scadenza

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_le_mie_polizze);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_stat_name);
        toolbar.setTitle("Le mie polizze");// your drawable
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
            }
        });


        p1.setAnnoScadenza(2019);
        p1.setMeseScadenza(5);
        p1.setAttiva(true);
        p1.setInScadenza(false);

        p2.setAnnoScadenza(2018);
        p2.setMeseScadenza(12);
        p2.setAttiva(false);
        p2.setInScadenza(false);

        p3.setAnnoScadenza(2019);
        p3.setMeseScadenza(2);
        p3.setAttiva(true);
        p3.setInScadenza(true);

        /** ACCESSORI **/
        p1.setKasko(true);
        p1.setGuida_esperta(true);
        p1.aggiornaAccessori();

        p2.setIncendio(true);
        p2.setCristalli(true);
        p2.aggiornaAccessori();

        p3.setFurto(true);
        p3.setAssistenza_stradale(true);
        p3.aggiornaAccessori();

        polizze.add(p1);
        polizze.add(p2);
        polizze.add(p3);

        modello_v1 = (TextView)findViewById(R.id.modello_v1);
        modello_v2 = (TextView)findViewById(R.id.modello_v2);
        modello_v3 = (TextView)findViewById(R.id.modello_v3);

        String s1= "Toyota Yaris\nAttiva";
        SpannableString ss1=  new SpannableString(s1);
        ss1.setSpan(new RelativeSizeSpan(0.8f), 13, ss1.length(), 0); // set size
        modello_v1.setText(ss1);
        modello_v1.setVisibility(View.VISIBLE);

        String s2= "Fiat Punto\nScaduta";
        SpannableString ss2=  new SpannableString(s2);
        ss2.setSpan(new RelativeSizeSpan(0.8f), 11,ss2.length(), 0); // set size
        ss2.setSpan(new ForegroundColorSpan(Color.RED), 11,ss2.length(), 0); // set color
        modello_v2.setText(ss2);
        modello_v2.setVisibility(View.VISIBLE);

        String s3= "Lancia Ypsilon\nIn scadenza";
        SpannableString ss3=  new SpannableString(s3);
        ss3.setSpan(new RelativeSizeSpan(0.8f), 14,ss3.length(), 0); // set size
        modello_v3.setText(ss3);
        modello_v3.setVisibility(View.VISIBLE);



        vedi1=(Button)findViewById(R.id.vedi1);
        vedi2=(Button)findViewById(R.id.vedi2);
        vedi3=(Button)findViewById(R.id.vedi3);

        modifica1=(Button)findViewById(R.id.modifica1);
        modifica2=(Button)findViewById(R.id.modifica2);
        modifica3=(Button)findViewById(R.id.modifica3);


        vedi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visualizza_polizza = new Intent(LeMiePolizze.this,
                        VisualizzaPolizza.class);
                visualizza_polizza.putExtra(POLIZZA_EXTRA, p1);
                startActivity(visualizza_polizza);
            }
        });

        modello_v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visualizza_polizza = new Intent(LeMiePolizze.this,
                        VisualizzaPolizza.class);
                visualizza_polizza.putExtra(POLIZZA_EXTRA, p1);
                startActivity(visualizza_polizza);
            }
        });


        vedi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visualizza_polizza = new Intent(LeMiePolizze.this,
                        VisualizzaPolizza.class);

                visualizza_polizza.putExtra(POLIZZA_EXTRA, p2);
                startActivity(visualizza_polizza);
            }
        });
        modello_v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visualizza_polizza = new Intent(LeMiePolizze.this,
                        VisualizzaPolizza.class);
                visualizza_polizza.putExtra(POLIZZA_EXTRA, p2);
                startActivity(visualizza_polizza);
            }
        });

        vedi3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visualizza_polizza = new Intent(LeMiePolizze.this,
                        VisualizzaPolizza.class);
                visualizza_polizza.putExtra(POLIZZA_EXTRA, p3);
                startActivity(visualizza_polizza);
            }
        });

        modello_v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visualizza_polizza = new Intent(LeMiePolizze.this,
                        VisualizzaPolizza.class);
                visualizza_polizza.putExtra(POLIZZA_EXTRA, p3);
                startActivity(visualizza_polizza);
            }
        });

        modifica1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent modifica_polizza = new Intent(LeMiePolizze.this, ModificaPolizza.class);
                modifica_polizza.putExtra(POLIZZA_EXTRA, p1);
                startActivity(modifica_polizza);
            }
        });

        modifica2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent modifica_polizza = new Intent(LeMiePolizze.this, ModificaPolizza.class);
                modifica_polizza.putExtra(POLIZZA_EXTRA, p2);
                startActivity(modifica_polizza);
            }
        });

        modifica3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent modifica_polizza = new Intent(LeMiePolizze.this, ModificaPolizza.class);
                modifica_polizza.putExtra(POLIZZA_EXTRA, p3);
                startActivity(modifica_polizza);
            }
        });
    }
}