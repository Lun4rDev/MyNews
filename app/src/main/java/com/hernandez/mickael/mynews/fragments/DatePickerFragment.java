package com.hernandez.mickael.mynews.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment
                            implements DatePickerDialog.OnDateSetListener {
    public static Calendar date;
    public static SimpleDateFormat format = new SimpleDateFormat("YYYYMMdd");
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        date = Calendar.getInstance();
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, date.get(Calendar.YEAR), date.get(Calendar.MONTH) + 1, date.get(Calendar.DAY_OF_MONTH));
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        date = new GregorianCalendar(year, month, day);
    }

    public String getDate(){
        if(date == null){
            date = Calendar.getInstance();
        }
        return format.format(date.getTime());
    }
}