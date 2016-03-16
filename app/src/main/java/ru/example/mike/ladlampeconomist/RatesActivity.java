package ru.example.mike.ladlampeconomist;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
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

    private GestureDetectorCompat mGestureDetectorCompat;

    private static List<Integer> spinnerOne = new ArrayList<>();
    private static List<Integer> spinnerTwo = new ArrayList<>(); //TODO: перевести "копейку"
    private static List<Integer> spinnerHours = new ArrayList<>();
    TextView textView;
    Spinner spinnerRub; //вот эти спинеры
    Spinner spinnerKopeck; //вот эти спинеры
    Spinner spinnerHour;//вот эти спинеры
    private int resultTimeYears; // руезультат с точкой 4.767
    private int selected3; //целое число
    final String LOG_TAG = "myLogs";


    static {
        for (int i = 1; i <= 50; i++) {  //так инициализировать списки не стоит
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

        textView = (TextView) findViewById(R.id.textView5);

        spinnerRub = (Spinner) findViewById(R.id.spinnerOne); //вот правильная инициализация спинеров которые в верху
        spinnerKopeck = (Spinner) findViewById(R.id.spinnerTwo); //вот правильная инициализация спинеров которые в верху
        spinnerHour = (Spinner) findViewById(R.id.spinnerHours); //вот правильная инициализация спинеров которые в верху

        adapterSpinnerOne();
        adapterSpinnerTwo();
        adapterSpinnerHours();

        setClicklistenerTo3Spinner();
        mGestureDetectorCompat = new GestureDetectorCompat(this, new MyGestureListener());

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mGestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e2.getX() > e1.getX()) {
                Toast.makeText(getBaseContext(),
                        "Свайп вправо",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(
                        RatesActivity.this, MainActivity.class);
                startActivity(intent);
            }
            return true;
        }
    }


    public void adapterSpinnerOne() {

        SpinnerAdapter spinnerAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, spinnerOne);
        ((ArrayAdapter<Integer>) spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRub.setAdapter(spinnerAdapter);
    }

    public void adapterSpinnerTwo() {

        SpinnerAdapter spinnerAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, spinnerTwo);
        ((ArrayAdapter<Integer>) spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKopeck.setAdapter(spinnerAdapter);
    }

    public void adapterSpinnerHours() {
        SpinnerAdapter spinnerAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, spinnerHours);
        ((ArrayAdapter<Integer>) spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHour.setAdapter(spinnerAdapter);

    }


    public void onClickAgo(View view) {
        Intent intent = new Intent(RatesActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickForward(View view) {
    }

    public void setClicklistenerTo3Spinner() {
        spinnerHour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                selected3 = (int) spinnerHour.getSelectedItem();
                try {

                    resultTimeYears =  selected3 * 360;
                    Log.d(LOG_TAG, "result: " + resultTimeYears);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "что то не так", Toast.LENGTH_SHORT).show();
                }

                textView.setText(String.valueOf(resultTimeYears));
                Log.d(LOG_TAG, "itemClick: position = " + position + ", id = "
                        + id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Нужно выбрать пункт", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
