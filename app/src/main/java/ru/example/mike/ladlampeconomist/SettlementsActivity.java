package ru.example.mike.ladlampeconomist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SettlementsActivity extends AppCompatActivity {

    private int percentPriceUp;
    private int powerLamp;
    private int timeYearsRate1;
    private int timeYearsRate2;
    private double resultPriceRate1;
    private double resultPriceRate2;
    private int watt = 1000;
    private boolean isChecked;
    private int changeLamp;
    private int priceLamp;
    private int priceLed;


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
        setContentView(R.layout.activity_settlements);

        percentPriceUp = getIntent().getIntExtra("percent", 1);
        powerLamp = getIntent().getIntExtra("power", 1);
        timeYearsRate1 = getIntent().getIntExtra("dataYears", 1);
        timeYearsRate2 = getIntent().getIntExtra("dataYearsTwo", 1);
        resultPriceRate1 = getIntent().getDoubleExtra("resultPriceRate1", 1);
        resultPriceRate2 = getIntent().getDoubleExtra("resultPriceRate2", 1);
        isChecked = getIntent().getBooleanExtra("checked", true);
        changeLamp = getIntent().getIntExtra("changeLamp", 1);
        priceLamp = getIntent().getIntExtra("priceLamp", 1);
        priceLed = getIntent().getIntExtra("priceLed", 1);

        textView1 = (TextView)findViewById(R.id.cell_1_row_2);
        textView2 = (TextView)findViewById(R.id.cell_2_row_2);
        textView3 = (TextView)findViewById(R.id.cell_3_row_2);
        textView4 = (TextView)findViewById(R.id.cell_2_row_3);
        textView5 = (TextView)findViewById(R.id.cell_3_row_3);
        textView6 = (TextView)findViewById(R.id.cell_4_row_3);
        textView7 = (TextView)findViewById(R.id.cell_2_row_4);
        textView8 = (TextView)findViewById(R.id.cell_3_row_4);
        textView9 = (TextView)findViewById(R.id.cell_4_row_4);
        textView10 = (TextView)findViewById(R.id.cell_2_row_5);
        textView11 = (TextView)findViewById(R.id.cell_3_row_5);
        textView12 = (TextView)findViewById(R.id.cell_4_row_5);
        textView13 = (TextView)findViewById(R.id.cell_2_row_6);
        textView13 = (TextView)findViewById(R.id.cell_3_row_6);
        textView13 = (TextView)findViewById(R.id.cell_4_row_6);


        if (isChecked == true) {
            textView1.setText(String.valueOf(Math.round((powerLamp * timeYearsRate1 * percentPriceUp / watt * resultPriceRate1) + (powerLamp * timeYearsRate2 * percentPriceUp / watt * resultPriceRate2) + (changeLamp * priceLamp)) + " руб.")); //Math.round - округление значения
        } else if (isChecked == false) {
            textView1.setText(String.valueOf(Math.round((powerLamp * timeYearsRate1 * percentPriceUp / watt * resultPriceRate1) + (changeLamp * priceLamp)) + " руб."));
        }

    }

    public void onClickLampUpdate(View view) {
    }

    public void onClickNewInfo(View view) {
    }
}
