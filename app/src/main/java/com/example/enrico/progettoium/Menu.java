package com.example.enrico.progettoium;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.Serializable;


public class Menu extends AppCompatActivity {


    TextView benvenuto;
    Utente utente=null;
    Button il_mio_profilo, le_mie_polizze, preventivi, contatta_agenzia, logout;


    public static int state=-1; //-1: polizze scadute, 0: polizze in scadenza, 1: polizze attive

    public static final String UTENTE_EXTRA="com.example.enrico.progettoium.Utente";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //recupero degli id dall'xml
        benvenuto = (TextView)findViewById(R.id.benvenuto);
        il_mio_profilo =(Button)findViewById(R.id.il_mio_profilo_button);
        le_mie_polizze=(Button)findViewById(R.id.le_mie_polizze_button);
        preventivi=(Button)findViewById(R.id.preventivi_button);
        contatta_agenzia=(Button)findViewById((R.id.contatta_agenzia_button));
        logout=(Button) findViewById(R.id.logout_button);

        Intent intent = getIntent();
        Serializable obj = intent.getSerializableExtra(Login.UTENTE_EXTRA);

        if(obj!=null){
            utente=(Utente)obj;
            benvenuto.setText("Benvenuto, " + utente.getNome() + " " + utente.getCognome());
        }

        il_mio_profilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profilo = new Intent(Menu.this,
                        Profilo.class);
                profilo.putExtra(UTENTE_EXTRA, utente);
                startActivity(profilo);
            }
        });

        le_mie_polizze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent le_mie_polizze = new Intent(Menu.this, LeMiePolizze.class);
                startActivity(le_mie_polizze);
            }
        });

        preventivi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent preventivi = new Intent(Menu.this, Preventivi.class);
                startActivity(preventivi);
            }
        });

        contatta_agenzia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contatta = new Intent(Menu.this, Contatti.class);
                startActivity(contatta);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(Menu.this,
                        Login.class);

                startActivity(login);
            }
        });
    }

    public void changeState(int state){
        if(state>-2 && state<2)
            this.state=state;
    }
}
