package com.example.enrico.progettoium;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

public class DataPickerFragment extends DialogFragment {

    private Calendar date;
    private DataPickerFragmentListener listener; //variabile listener

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        super.onCreateDialog(savedInstanceState);

        if(date == null){
            date = Calendar.getInstance();
            date.set(Calendar.YEAR, 1995);
            date.set(Calendar.MONTH, Calendar.JANUARY);
            date.set(Calendar.DAY_OF_MONTH, 15);
        }

        //contenuto della finestra
        final DatePicker dataPicker = new DatePicker(getActivity());

        //cio che contiene finestra aperta di dialogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //dico cosa il builder deve contenere
        builder.setView(dataPicker);


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //prendo il contenuto del calendario (data)
                date.set(Calendar.YEAR, dataPicker.getYear());
                date.set(Calendar.MONTH, dataPicker.getMonth());
                date.set(Calendar.DAY_OF_MONTH, dataPicker.getDayOfMonth());


                if (listener != null) {
                    listener.onDatePickerFragmentOkButton(DataPickerFragment.this, date);
                }
            }
        });


        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null){
                    listener.onDatePickerFragmentCancelButton(DataPickerFragment.this);
                }
            }
        });

        return  builder.create();
    }

    public Calendar getData()
    {
        return date;
    }

    public void setOnDatePickerFragmentChanged(DataPickerFragmentListener l){
        this.listener = l;
    }

    public interface DataPickerFragmentListener{
        //oggetti dentro il widget calendario
        public void onDatePickerFragmentOkButton(DialogFragment dialog, Calendar date);
        public void onDatePickerFragmentCancelButton(DialogFragment dialog);

    }
}
