package com.example.enrico.progettoium;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Contatti extends AppCompatActivity {

    TextView scrivi_messaggio, conferma_invio_messaggio, numero_tel_agenzia,
             msg_selezione_fascia_oraria, msg_conferma_orario;
    EditText testo_messaggio;
    Button pulsante_invia_messaggio, ok_selezione_orario;
    RadioButton scelta_invia_messaggio, scelta_chiama_agenzia, scelta_voglio_Essere_ricontattato;
    Spinner selezione_fascia_oraria;

    public static final String[] scaglioni_orari = new String[]{"9.00-12.00", "12.00-15.00", "15.00-18.00"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contatti);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_stat_name);
        toolbar.setTitle("Contatti");// your drawable
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
            }
        });

        scrivi_messaggio = (TextView) findViewById(R.id.scrivi_messaggio);
        testo_messaggio = (EditText) findViewById(R.id.testo_messaggio);
        pulsante_invia_messaggio = (Button) findViewById(R.id.pulsante_invia_messaggio);
        scelta_invia_messaggio = (RadioButton) findViewById(R.id.scelta_invia_messaggio);
        conferma_invio_messaggio=(TextView)findViewById(R.id.conferma_invio_messaggio);

        scelta_chiama_agenzia=(RadioButton)findViewById(R.id.scelta_chiama_agenzia);
        numero_tel_agenzia=(TextView)findViewById(R.id.numero_tel_agenzia);

        scelta_voglio_Essere_ricontattato=(RadioButton)findViewById(R.id.scelta_voglio_essere_ricontattato);
        selezione_fascia_oraria=(Spinner)findViewById(R.id.fascia_oraria);
        msg_selezione_fascia_oraria=(TextView)findViewById(R.id.msg_fascia_oraria);
        ok_selezione_orario=(Button)findViewById(R.id.ok_selezione_orario);
        msg_conferma_orario=(TextView)findViewById(R.id.msg_conferma_orario);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, scaglioni_orari);
        selezione_fascia_oraria.setAdapter(adapter);


    scelta_invia_messaggio.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            scrivi_messaggio.setVisibility(View.VISIBLE);
            testo_messaggio.setVisibility(View.VISIBLE);
            pulsante_invia_messaggio.setVisibility(View.VISIBLE);

            numero_tel_agenzia.setVisibility(View.GONE);
            msg_selezione_fascia_oraria.setVisibility(View.GONE);
            selezione_fascia_oraria.setVisibility(View.GONE);
            ok_selezione_orario.setVisibility(View.GONE);
            msg_conferma_orario.setVisibility(View.GONE);

            pulsante_invia_messaggio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        if(testo_messaggio.getText().length()>0) {

                            pulsante_invia_messaggio.setVisibility(View.GONE);
                            Snackbar.make(v, "Il tuo messaggio è stato inviato.\nVerrai ricontattato a breve", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                        else
                            testo_messaggio.setError("Scrivi il messaggio");

                }
            });
        }
    });

    scelta_chiama_agenzia.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            numero_tel_agenzia.setVisibility(View.VISIBLE);

            scrivi_messaggio.setVisibility(View.GONE);
            testo_messaggio.setVisibility(View.GONE);
            pulsante_invia_messaggio.setVisibility(View.GONE);
            msg_selezione_fascia_oraria.setVisibility(View.GONE);
            selezione_fascia_oraria.setVisibility(View.GONE);
            ok_selezione_orario.setVisibility(View.GONE);
            msg_conferma_orario.setVisibility(View.GONE);
            conferma_invio_messaggio.setVisibility(View.GONE);

            numero_tel_agenzia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:0705105115"));
                    startActivity(callIntent);
                }
            });
        }
    });

    scelta_voglio_Essere_ricontattato.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            msg_selezione_fascia_oraria.setVisibility(View.VISIBLE);
            selezione_fascia_oraria.setVisibility(View.VISIBLE);
            ok_selezione_orario.setVisibility(View.VISIBLE);


            scrivi_messaggio.setVisibility(View.GONE);
            testo_messaggio.setVisibility(View.GONE);
            pulsante_invia_messaggio.setVisibility(View.GONE);
            numero_tel_agenzia.setVisibility(View.GONE);
            conferma_invio_messaggio.setVisibility(View.GONE);

            ok_selezione_orario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ok_selezione_orario.setVisibility(View.GONE);


                    Snackbar.make(v, "La tua richiesta è stata presa in carico\nVerrai ricontattato al " +
                            "più presto", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            });
        }
    });


    }

}
