package com.example.enrico.progettoium;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class NuovoPreventivo extends AppCompatActivity {

    public static final String[] frazionamento = new String[]{"Annuale","Semestrale"};

    EditText targa, decorrenza;
    Spinner spinnerFrazionamento;
    DataPickerFragment datePickerFragment = new DataPickerFragment();
    CheckBox condizioniPreventivo;
    Button invia_preventivo;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuovo_preventivo);

        spinnerFrazionamento = (Spinner)findViewById(R.id.spinner);
        targa = (EditText)findViewById(R.id.targaPreventivo);
        decorrenza = (EditText)findViewById(R.id.decorrenza);
        condizioniPreventivo = (CheckBox)findViewById(R.id.condizioniPreventivo);
        invia_preventivo = (Button)findViewById(R.id.paga);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, frazionamento);
        spinnerFrazionamento.setAdapter(adapter);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_stat_name);
        toolbar.setTitle("Richiesta nuovo preventivo");// your drawable
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
            }
        });

        decorrenza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        datePickerFragment.setOnDatePickerFragmentChanged(new DataPickerFragment.DataPickerFragmentListener() {
            @Override
            public void onDatePickerFragmentOkButton(DialogFragment dialog, Calendar date) {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                decorrenza.setText(format.format(date.getTime()));
            }

            @Override
            public void onDatePickerFragmentCancelButton(DialogFragment dialog) {

            }
        });

        invia_preventivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInput()){

                    Snackbar.make(v, "La tua richiesta è stata inoltrata correttamente", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    invia_preventivo.setVisibility(View.GONE);
                }
            }
        });


    }

    private boolean checkInput(){

        int errors=0;

        if (targa.getText() == null || targa.getText().length()==0) {
            errors++;
            targa.setError("Inserisci Targa");
        }
        else
            targa.setError(null);

        if (decorrenza.getText() == null || decorrenza.getText().length()==0) {
            errors++;
            decorrenza.setError("Inserisci la data");
        }
        else
            decorrenza.setError(null);

        if (!condizioniPreventivo.isChecked()) {
            errors++;
            condizioniPreventivo.setError("Devi");
        }
        else
            condizioniPreventivo.setError(null);

        return errors==0; // ritorna true se non ci sono errori
    }
}