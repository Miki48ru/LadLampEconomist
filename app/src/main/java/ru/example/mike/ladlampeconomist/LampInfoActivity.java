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

public class LampInfoActivity extends AppCompatActivity {

    private static List<Integer> listPower = new ArrayList<>(); // массив мощности лампы
    private static List<Integer> listPrice = new ArrayList<>();

   RatesActivity result = new RatesActivity();
    
    result.getResultTimeYears();


    Spinner spinnerPowerLamp;
    Spinner spinnerLampPrice;

    static {
        for (int i = 0; i <= 250; i = i+10) {
            listPower.add(i);
        }
        for (int i = 5; i <= 500; i = i + 5){
            listPrice.add(i);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp_info);
        findViewById(R.id.lamp_info_activity).setOnTouchListener(activitySwiped);

        spinnerPowerLamp = (Spinner) findViewById(R.id.spinner_power);
        adapterSpinnerPower();

        spinnerLampPrice = (Spinner) findViewById(R.id.spinner_lamp_price);
        adapterSpinnerPrice();
    }

    // метод для свайпа активностей
    View.OnTouchListener activitySwiped = new OnSwipeTouchListener(this) {
        public boolean onSwipeRight() {
            Intent intent = new Intent(LampInfoActivity.this, RatesActivity.class);
            startActivity(intent);
            return true;
        }

        public boolean onSwipeLeft() {

            return true;
        }

        public boolean onSwipeBottom() {
            return true;
        }

    };

    public void adapterSpinnerPower() {

        SpinnerAdapter spinnerAdapter = new ArrayAdapter<Integer>(this, // создаем адаптер между раскрывающимся списком и массивом
                android.R.layout.simple_spinner_item, listPower);
        ((ArrayAdapter<Integer>) spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPowerLamp.setAdapter(spinnerAdapter);
    }

    public void adapterSpinnerPrice() {

        SpinnerAdapter spinnerAdapter = new ArrayAdapter<Integer>(this, // создаем адаптер между раскрывающимся списком и массивом
                android.R.layout.simple_spinner_item, listPrice);
        ((ArrayAdapter<Integer>) spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLampPrice.setAdapter(spinnerAdapter);
    }


}
