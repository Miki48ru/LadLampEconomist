package ru.example.mike.ladlampeconomist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RatesActivity extends AppCompatActivity {

    final private static List<Integer> spinnerOne = new ArrayList<>();
    final private static List<Integer> spinnerTwo = new ArrayList<>(); //TODO: перевести "копейку"
    final private static List<Integer> spinnerHours = new ArrayList<>();
    final TextView textView = (TextView)findViewById(R.id.textView5);
    Spinner spinnerRub = (Spinner) findViewById(R.id.spinnerOne);
    Spinner spinnerKopeck = (Spinner) findViewById(R.id.spinnerTwo);
    Spinner spinnerHour = (Spinner) findViewById(R.id.spinnerHours);
    private int result;
    private Integer selected1 = (Integer) spinnerRub. getSelectedItem();
    private Integer selected2 = (Integer) spinnerKopeck. getSelectedItem();
    private Integer selected3 = (Integer) spinnerHour. getSelectedItem();
    final String LOG_TAG = "myLogs";



    static {
        for (int i = 1; i <= 50; i++) {
            spinnerOne.add(i);
        }
        for (int i = 1; i <= 99; i++) {
            spinnerTwo.add(i);
        }
        for (int i = 1; i <= 24; i++) {
            spinnerHours.add(i);
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rates);

        adapterSpinnerOne();
        adapterSpinnerTwo();
        adapterSpinnerHours();


    }

    public void adapterSpinnerOne(){

        SpinnerAdapter spinnerAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, spinnerOne);
        ((ArrayAdapter<Integer>)spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRub.setAdapter(spinnerAdapter);
    }

    public void adapterSpinnerTwo(){

        SpinnerAdapter spinnerAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, spinnerTwo);
        ((ArrayAdapter<Integer>)spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKopeck.setAdapter(spinnerAdapter);
    }
    public void adapterSpinnerHours() {

        SpinnerAdapter spinnerAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, spinnerHours);
        ((ArrayAdapter<Integer>) spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHour.setAdapter(spinnerAdapter);
        spinnerHour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                result = (selected1+(selected2/100)) * selected3;
                textView.setText(result);
                Log.d(LOG_TAG, "itemClick: position = " + position + ", id = "
                        + id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Нужно выбрать пункт", Toast.LENGTH_SHORT).show();
            }
        });
    }







    public void onClickAgo(View view) {
        Intent intent = new Intent(RatesActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void onClickForward(View view) {
    }

}
