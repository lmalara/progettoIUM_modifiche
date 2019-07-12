package com.example.enrico.progettoium;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.ArrayList;

public class Login extends AppCompatActivity {

    EditText username, password;
    TextView registrazione, autenticazioneFallita;
    Button pulsanteAccedi;

    //lista contenente gli utenti fittizi
    private ArrayList<Utente> utenti = new ArrayList<Utente>(3);

    //Utenti fittizi
    private Utente ut1 =
            new Utente("demo", "demo", "1",
                    "1", "05/09/1976", 1234567891, 1234567891);
    private Utente ut2 = new Utente("Gianni", "Lai", "gianni.lai@gmail.com",
            "gianni", "12/03/1966", 402361001, 345264255);
    private Utente ut3 = new Utente("Clara", "Melis", "clara.melis@gmail.com",
            "clara", "1975-12-05", 402512541, 346958744);

    private String actualUsername, actualPassword;

    //utente che si dovrà loggare, per ora NULL
    Utente actualUser = null;

    public static final String UTENTE_EXTRA="com.example.enrico.progettoium.Utente";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //si aggiungono gli utenti nella lista "utenti"
        utenti.add(ut1);
        utenti.add(ut2);
        utenti.add(ut3);


        //prelievo delle informazioni dall'xml
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        pulsanteAccedi = (Button)findViewById(R.id.accedi);
        registrazione = (TextView)findViewById(R.id.registrazione);


        registrazione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg = new Intent(Login.this,
                        Registrazione.class);
                startActivity(reg);

            }
        });


        //l'utente preme il pulsante "Accedi"
        pulsanteAccedi.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                //se l'utente compila entrambi i campi "Username" e "Password"
                if( checkInput() )
                {
                    //prelievo dei valori inseriti negli EditText
                    actualUsername = username.getText().toString();
                    actualPassword = password.getText().toString();

                    //controllo se l'utente compare nella lista "utenti"
                    for (Utente u : utenti)
                    {
                        /*
                          se l'username e password dell'utente corrispondono ad un
                          utente X presente nella lista, actualUser sarà l'utente X,
                          altrimenti actualUser non viene modificato e varrà NULL
                        */
                        if (actualUsername.equals(u.getEmail()) &&
                                actualPassword.equals(u.getPassword()))
                        {
                            actualUser = u;
                            //passaggio alla prossima activity consegnando l'oggetto "actualUser"
                            Intent menu = new Intent(Login.this,
                                    Menu.class);
                            menu.putExtra(UTENTE_EXTRA, actualUser);
                            startActivity(menu);
                        }
                    }

                    if(actualUser==null) {
                        autenticazioneFallita = (TextView) findViewById(R.id.autenticazioneFallita);
                        autenticazioneFallita.setVisibility(View.VISIBLE);
                        username.setError("Errore Username");
                        password.setError("Errore Password");
                    }
                }
            }
        });
    }


    /*Questa funzione verifica che entrambi i campi "Username" e Password
      non siano vuoti (true). Restituisce false se almeno uno dei due campi
      è vuoto.
      Si occupa, eventualmente, di mostrare i messaggi d'errore all'utente.
    */
    private boolean checkInput(){

        int errors=0;

        if (username.getText() == null || username.getText().length()==0) {
            errors++;
            username.setError("Inserisci Username");
        }
        else
            username.setError(null);

        if (password.getText() == null || password.getText().length()==0) {
            errors++;
            password.setError("Inserisci Password");
        }
        else
            password.setError(null);

        return errors==0; // ritorna true se non ci sono errori

    }
}
