package ru.example.mike.ladlampeconomist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

public class RatesActivity extends AppCompatActivity {

    final private static List<Integer> spinnerOne = new ArrayList<>();
    final private static List<Integer> spinnerTwo = new ArrayList<>(); //TODO: перевести "копейку"
    final private static List<Integer> spinnerHours = new ArrayList<>();


    static {
        for (int i = 1; i <= 50; i++) {
            spinnerOne.add(i);
        }
        for (int i = 1; i <= 100; i++) {
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
        Spinner spinner = (Spinner) findViewById(R.id.spinnerOne);
        SpinnerAdapter spinnerAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, spinnerOne);
        ((ArrayAdapter<Integer>)spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    public void adapterSpinnerTwo(){
        Spinner spinner = (Spinner) findViewById(R.id.spinnerTwo);
        SpinnerAdapter spinnerAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, spinnerTwo);
        ((ArrayAdapter<Integer>)spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }
    public void adapterSpinnerHours(){
        Spinner spinner = (Spinner) findViewById(R.id.spinnerHours);
        SpinnerAdapter spinnerAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, spinnerHours);
        ((ArrayAdapter<Integer>)spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }





    public void onClickAgo(View view) {
        Intent intent = new Intent(RatesActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void onClickForward(View view) {
    }

}
