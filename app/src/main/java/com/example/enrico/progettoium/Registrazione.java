package com.example.enrico.progettoium;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Registrazione extends AppCompatActivity {

    ImageView fotocamera, graffetta;
    TextView allegato;
    private int CAMERA_PIC_REQUEST = 7;
    Button registrazione;

    private EditText nome, cognome, cf, email, user, pw1, pw2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);

        fotocamera = findViewById(R.id.fotocamera);
        graffetta = findViewById(R.id.graffetta);
        allegato = findViewById(R.id.allegato);
        registrazione = findViewById(R.id.registrazione);

        nome= findViewById(R.id.nomeRegistrazione);
        cognome= findViewById(R.id.cognomeRegistrazione);
        cf= findViewById(R.id.codiceFiscaleRegistrazione);
        email= findViewById(R.id.emailRegistrazione);
        user= findViewById(R.id.usernameRegistrazione);
        pw1= findViewById(R.id.pwdReg1);
        pw2= findViewById(R.id.pwdReg2);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_stat_name);
        toolbar.setTitle("Registrazione");// your drawable
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
            }
        });

        fotocamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(intent, CAMERA_PIC_REQUEST );

                graffetta.setVisibility(View.VISIBLE);
                allegato.setText("Allegato: Video001.mp4");
            }
        });

        registrazione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInput()){
                    Snackbar.make(v, "La richiesta di registrazione è stata inoltrata.\nControlla la tua casella email", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    registrazione.setVisibility(View.GONE);
                }
            }
        });

    }



    private Boolean checkInput(){
        int errors=0;


        if (user.getText() == null || user.getText().length()==0) {
            errors++;
            user.setError("Inserisci Username");
        }
        else
            user.setError(null);

        if (nome.getText() == null || nome.getText().length()==0) {
            errors++;
            nome.setError("Inserisci Nome");
        }
        else
            nome.setError(null);

        if (cognome.getText() == null || cognome.getText().length()==0) {
            errors++;
            cognome.setError("Inserisci Cognome");
        }
        else
            cognome.setError(null);

        if (cf.getText() == null || cf.getText().length()!=16) {
            errors++;
            cf.setError("CF errato!");
        }
        else
            cf.setError(null);

        if (email.getText() == null || email.getText().length()==0) {
            errors++;
            email.setError("Inserisci Email");
        }
        else
            email.setError(null);

        if (pw1.getText() == null || pw1.getText().length()==0) {
            errors++;
            pw1.setError("Inserisci Password");
        }
        else
            pw1.setError(null);

        if (pw2.getText() == null || pw2.getText().length()==0) {
            errors++;
            pw2.setError("Conferma Password");
        }
        else
            pw2.setError(null);

        if(!pw1.getText().toString().equals(pw2.getText().toString())){
            errors++;
            pw2.setError("La password non coincide!");
        }
        else
            pw2.setError(null);


        if(allegato.getText().toString().equals("Nessun video allegato!")){
            errors++;
            allegato.setError("Inserisci un allegato!");
        }
        else
        {
            allegato.setError(null);
        }

        return (errors==0);
    }
}
