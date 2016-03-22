package ru.example.mike.ladlampeconomist;


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
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class RatesActivity extends AppCompatActivity {

    private GestureDetectorCompat mGestureDetectorCompat;

    private static List<Integer> spinnerOne = new ArrayList<>();
    private static List<Integer> spinnerTwo = new ArrayList<>();
    private static List<Integer> spinnerHours = new ArrayList<>();

    private static List<Integer> spinner_rates_2_one = new ArrayList<>();
    private static List<Integer> spinner_rates_2_two = new ArrayList<>();
    private static List<Integer> spinner_rates_2_hours = new ArrayList<>();

    TextView textView;
    Spinner spinnerRub; //вот эти спинеры
    Spinner spinnerKopeck; //вот эти спинеры
    Spinner spinnerHour;//вот эти спинеры

    Spinner spinnerRatesTwoRub; //вот эти спинеры
    Spinner spinnerRatesTwoKopeck; //вот эти спинеры
    Spinner spinnerRatesTwoHour;//вот эти спинеры
    private int resultTimeYears; // руезультат с точкой 4.767
    private int selected3; //целое число
    final String LOG_TAG = "myLogs";


    static {
        for (int i = 1; i <= 50; i++) {
            spinnerOne.add(i);
        }
        for (int i = 1; i <= 50; i++) {
            spinner_rates_2_one.add(i);
        }
        for (int i = 1; i <= 99; i++) {
            spinnerTwo.add(i);
        }
        for (int i = 1; i <= 99; i++) {
            spinner_rates_2_two.add(i);
        }

        for (int i = 1; i <= 24; i++) {
            spinnerHours.add(i);
        }
        for (int i = 1; i <= 24; i++) {
            spinner_rates_2_hours.add(i);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rates);
           findViewById(R.id.tableLayoutRates).setOnTouchListener(activitySwiped);;



        textView = (TextView) findViewById(R.id.textView5);

        spinnerRub = (Spinner) findViewById(R.id.spinnerOne);
        spinnerKopeck = (Spinner) findViewById(R.id.spinnerTwo);
        spinnerHour = (Spinner) findViewById(R.id.spinnerHours);

        spinnerRatesTwoRub = (Spinner) findViewById(R.id.spinner_rates_2_one);
        spinnerRatesTwoKopeck = (Spinner) findViewById(R.id.spinner_rates_2_two);
        spinnerRatesTwoHour = (Spinner) findViewById(R.id.spinner_rates_2_hours);

        adapterSpinnerOne();
        adapterSpinnerTwo();
        adapterSpinnerHours();
        adapterSpinnerRates2One();
        adapterSpinnerRates2Two();
        adapterSpinnerRates2Hours();

        setClicklistenerTo3Spinner();


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

    public void adapterSpinnerRates2One() {

        SpinnerAdapter spinnerAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, spinner_rates_2_one);
        ((ArrayAdapter<Integer>) spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRatesTwoRub.setAdapter(spinnerAdapter);
    }

    public void adapterSpinnerRates2Two() {

        SpinnerAdapter spinnerAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, spinner_rates_2_two);
        ((ArrayAdapter<Integer>) spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRatesTwoKopeck.setAdapter(spinnerAdapter);
    }

    public void adapterSpinnerRates2Hours() {
        SpinnerAdapter spinnerAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, spinner_rates_2_hours);
        ((ArrayAdapter<Integer>) spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRatesTwoHour.setAdapter(spinnerAdapter);

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

    public void onCheckBoxClicked(View view){
        boolean checked = ((CheckBox)view).isChecked();
        spinnerRatesTwoRub.setEnabled(checked);
        spinnerRatesTwoKopeck.setEnabled(checked);
        spinnerRatesTwoHour.setEnabled(checked);

        }
    View.OnTouchListener activitySwiped = new OnSwipeTouchListener(this) {
        public boolean onSwipeRight() {
            Intent intent = new Intent(RatesActivity.this, MainActivity.class);
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
    }


