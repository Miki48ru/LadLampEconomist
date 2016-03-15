package ru.example.mike.ladlampeconomist;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mike on 09.03.2016.
 */
public class TestSpinners extends Activity {


    Spinner spinner1 = (Spinner) findViewById(R.id.spinnerOne);
    Integer selected1 = spinner1.getSelectedItemPosition();

    Spinner spinner2 = (Spinner) findViewById(R.id.spinnerTwo);
    Integer selected2 = spinner2.getSelectedItemPosition();

    Spinner spinner3 = (Spinner) findViewById(R.id.spinnerHours);
    Integer selected3 = spinner3.getSelectedItemPosition();

    public int moneTime() {
        int result = (selected1 + (selected2 / 100)) * selected3;
        return result;
    }
}



