package ru.example.mike.ledlampeconomist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SettlementsActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    private int percentPriceUp;
    private int powerLamp;
    private int powerLed;
    private int timeYearsRate1;
    private int timeYearsRate2;
    private float resultPriceRateOne;
    private float resultPriceRateTwo;
    private int watt = 1000;
    private boolean isChecked;
    private int changeLamp;
    private int priceLamp;
    private int priceLed;
    private float resultPriceLamp;
    private float resultPriceLampNotRate2;

    private float resultPriceLed;
    private float resultPriceLedNotRate2;


    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    private TextView textView8;
    private TextView textView9;
    private TextView textView10;
    private TextView textView11;
    private TextView textView12;
    private TextView textView13;
    private TextView textView14;
    private TextView textView15;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ru.example.mike.ledlampeconomist.R.layout.activity_settlements);

        percentPriceUp = getIntent().getIntExtra("percentBase", 1) / 100 + 1; // получаем не число, а именно процент
        Log.d(LOG_TAG, "putExtra persentPriceUp: " + percentPriceUp);
        powerLamp = getIntent().getIntExtra("power", 1);
        powerLed = getIntent().getIntExtra("powerLed", 1);
        timeYearsRate1 = getIntent().getIntExtra("dataYears", 1);
        timeYearsRate2 = getIntent().getIntExtra("dataYearsTwo", 1);
        resultPriceRateOne = getIntent().getFloatExtra("resultPriceRate1", 1);
        resultPriceRateTwo = getIntent().getFloatExtra("resultPriceRate2", 1);
        isChecked = getIntent().getBooleanExtra("checked", true);
        Log.d(LOG_TAG, "isChecked: " + isChecked);
        changeLamp = getIntent().getIntExtra("changeLamp", 1);
        priceLamp = getIntent().getIntExtra("priceLamp", 1);
        priceLed = getIntent().getIntExtra("priceLed", 1);

        textView1 = (TextView)findViewById(ru.example.mike.ledlampeconomist.R.id.cell_1_row_2);
        textView2 = (TextView)findViewById(ru.example.mike.ledlampeconomist.R.id.cell_2_row_2);
        textView3 = (TextView)findViewById(ru.example.mike.ledlampeconomist.R.id.cell_3_row_2);
        textView4 = (TextView)findViewById(ru.example.mike.ledlampeconomist.R.id.cell_2_row_3);
        textView5 = (TextView)findViewById(ru.example.mike.ledlampeconomist.R.id.cell_3_row_3);
        textView6 = (TextView)findViewById(ru.example.mike.ledlampeconomist.R.id.cell_4_row_3);
        textView7 = (TextView)findViewById(ru.example.mike.ledlampeconomist.R.id.cell_2_row_4);
        textView8 = (TextView)findViewById(ru.example.mike.ledlampeconomist.R.id.cell_3_row_4);
        textView9 = (TextView)findViewById(ru.example.mike.ledlampeconomist.R.id.cell_4_row_4);
        textView10 = (TextView)findViewById(ru.example.mike.ledlampeconomist.R.id.cell_2_row_5);
        textView11 = (TextView)findViewById(ru.example.mike.ledlampeconomist.R.id.cell_3_row_5);
        textView12 = (TextView)findViewById(ru.example.mike.ledlampeconomist.R.id.cell_4_row_5);
        textView13 = (TextView)findViewById(ru.example.mike.ledlampeconomist.R.id.cell_2_row_6);
        textView13 = (TextView)findViewById(ru.example.mike.ledlampeconomist.R.id.cell_3_row_6);
        textView13 = (TextView)findViewById(ru.example.mike.ledlampeconomist.R.id.cell_4_row_6);

        resultPriceLamp = Math.round(((float) powerLamp / watt * timeYearsRate1 * resultPriceRateOne) +
                ((float) powerLamp / watt * timeYearsRate2 * resultPriceRateTwo) +
                (changeLamp * priceLamp)); //Math.round - округление значения
        Log.d(LOG_TAG, "powerLamp: " + powerLamp + "watt: " + watt + " timeYearsRate1: " + timeYearsRate1 + " resultPriceRate1: " + resultPriceRateOne + " powerLamp: " + powerLamp
                + " timeYearsRate2: " + timeYearsRate2 + " resultPriceRate2: " + resultPriceRateTwo +
                " changeLamp " + changeLamp + " priceLamp " + priceLamp);
        Log.d(LOG_TAG, "Итого: " + resultPriceLamp);


        resultPriceLampNotRate2 = Math.round(((float) powerLamp / watt * timeYearsRate1 * resultPriceRateOne) +
                (changeLamp * priceLamp));

        resultPriceLed = Math.round(((float) powerLed / watt * timeYearsRate1 * resultPriceRateOne) +
                ((float) powerLed / watt * timeYearsRate2 * resultPriceRateTwo) + priceLed);

        resultPriceLedNotRate2 = Math.round(((float) powerLed / watt * timeYearsRate1 * resultPriceRateOne) + priceLed);


        if (isChecked == true) {


            textView1.setText(String.valueOf(resultPriceLamp + " руб."));


            textView2.setText(String.valueOf(resultPriceLed + " руб."));

            textView3.setText(String.valueOf(resultPriceLamp - resultPriceLed + " руб."));

        }

        if (isChecked == false) {
            textView1.setText(String.valueOf(resultPriceLampNotRate2 + " руб."));

            textView2.setText(String.valueOf(resultPriceLedNotRate2 + " руб."));

            textView3.setText(String.valueOf(resultPriceLampNotRate2 - resultPriceLedNotRate2 + " руб."));
        }

    }

    public void onClickLampUpdate(View view) {
    }

    public void onClickNewInfo(View view) {
    }
}
