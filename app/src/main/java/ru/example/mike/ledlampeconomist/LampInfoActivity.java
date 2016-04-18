package ru.example.mike.ledlampeconomist;

import android.app.AlertDialog;
import android.content.DialogInterface;
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


import java.util.ArrayList;
import java.util.List;

public class LampInfoActivity extends AppCompatActivity {

   final String LOG_TAG = "myLogs";

    private static List<Integer> listPower = new ArrayList<>(); // массив мощности лампы
    private static List<Integer> listPrice = new ArrayList<>();
    private static List<Integer> listChangeLamp = new ArrayList<>();

    private static List<Integer> listPowerLed = new ArrayList<>(); // массив мощности светодеодной лампы (spinner)
    private static List<Integer> listPriceLed = new ArrayList<>(); // спиннер цены лед лампы


    private int baseResult; //  переменная со значением времени из активности с расчетом по тарифам

    private int baseResultTwoRate;

    private float summPriceRate1; // цена по тарифу 1

    private float summPriceRate2;  // цена по тарифу 2

    private boolean enableCheckTwoRate;  // стоит галочка тарифа 2 или не стоит

    private int percentBase;


    private int selectedPower;
    private float resultPrice;
    private double watt = 1000.0;
    private float resultPriceTwoRate;
    private int changeLamp;
    private int priceLamp;
    private int priceLed;


    private int selectedPowerLed;
    private float resultPriceLed;
    private float resultPriceTwoRateLed;




    private Spinner spinnerPowerLamp;
    private Spinner spinnerLampPrice;
    private Spinner spinnerChangeLamp;

    private Spinner spinnerPowerLed;
    private Spinner spinnerPriceLed;

    private TextView textView;
    private TextView ledTextView;


    static {
        for (int i = 0; i <= 250; i = i+10) {
            listPower.add(i);
        }
        for (int i = 5; i <= 500; i = i + 5){
            listPrice.add(i);
        }
        for (int i = 0; i <= 10; i++){
            listChangeLamp.add(i);
        }
        for (int i = 1; i <= 50; i++) {
            listPowerLed.add(i);
        }
        for (int i = 100; i <= 700; i = i + 10 ) {
            listPriceLed.add(i);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ru.example.mike.ledlampeconomist.R.layout.activity_lamp_info);
        findViewById(ru.example.mike.ledlampeconomist.R.id.lamp_info_activity).setOnTouchListener(activitySwiped);



        textView = (TextView)findViewById(ru.example.mike.ledlampeconomist.R.id.text_table_1);

        ledTextView = (TextView)findViewById(ru.example.mike.ledlampeconomist.R.id.text_table_2);

        spinnerPowerLamp = (Spinner) findViewById(ru.example.mike.ledlampeconomist.R.id.spinner_power);
        adapterSpinnerPower();


        spinnerLampPrice = (Spinner) findViewById(ru.example.mike.ledlampeconomist.R.id.spinner_lamp_price);
        adapterSpinnerPrice();

        spinnerChangeLamp = (Spinner) findViewById(ru.example.mike.ledlampeconomist.R.id.spinner_lamp_change);
        adapterSpinnerChange();

        spinnerPowerLed = (Spinner)findViewById(ru.example.mike.ledlampeconomist.R.id.spinner_power_led);

        spinnerPriceLed = (Spinner)findViewById(ru.example.mike.ledlampeconomist.R.id.spinner_lamp_price_led);

        adapterSpinnerPowerAndPriceLed();

        setClicklistenerPriceRatesSpinner();
        setClicklistenerLedSpinner();
        setClicklistenerChangeSpinner();
        setClicklistenerPriceLampSpinner();
        setClicklistenerPriceLedSpinner();

        baseResult = getIntent().getIntExtra("dataYears", 1);
        Log.d(LOG_TAG, "putExtra dataYears: " + baseResult);

        baseResultTwoRate = getIntent().getIntExtra("dataYearsTwo", 1);
        Log.d(LOG_TAG, "putExtra dataYearsTwo: " + baseResultTwoRate);

        summPriceRate1 = getIntent().getFloatExtra("resultPrice1", 1);
        Log.d(LOG_TAG, "summPrice1 getExtra: " + summPriceRate1);

        summPriceRate2 = getIntent().getFloatExtra("resultPrice2", 1);
        Log.d(LOG_TAG, "summPrice2 getExtra: " + summPriceRate2);

        enableCheckTwoRate = getIntent().getBooleanExtra("checked", true);

        percentBase = getIntent().getIntExtra("percent", 1);
        Log.d(LOG_TAG, "percent onCreate: " + percentBase);
    }


    public void setClicklistenerPriceRatesSpinner() {
        spinnerPowerLamp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                selectedPower = (int) spinnerPowerLamp.getSelectedItem();
                Log.d(LOG_TAG, "power: " + selectedPower);
                try {

                    resultPrice = (float) (selectedPower * baseResult / watt * summPriceRate1);
                    Log.d(LOG_TAG, "result price: " + resultPrice);// мощность * общее время работы ламп по тарифу 1 / на 1000 ватт * на стоимость тарифа выбранного пользователем (дробное число)
                    resultPriceTwoRate = (float) (selectedPower * baseResultTwoRate / watt * summPriceRate2);
                    Log.d(LOG_TAG, "result price2: " + resultPriceTwoRate);
                    // если чекбокс включен, то тариф 2 расчитывается
                    if (enableCheckTwoRate == true) {

                        textView.setText(String.valueOf(Math.round(resultPrice + resultPriceTwoRate) + " руб.")); //Math.round - округление значения
                    } else if (enableCheckTwoRate == false) {
                        textView.setText(String.valueOf(Math.round(resultPrice) + " руб."));
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setClicklistenerLedSpinner() {
        spinnerPowerLed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                selectedPowerLed = (int) spinnerPowerLed.getSelectedItem();
                try {

                    resultPriceLed = (float) (selectedPowerLed * baseResult / watt * summPriceRate1);
                    Log.d(LOG_TAG, "LampInfoActivity: " + "result price Led: " + resultPriceLed + " selectedPowerLed " + selectedPowerLed +
                            " baseResult " + baseResult + " summPriceRate1 " + summPriceRate1);// мощность * общее время работы ламп по тарифу 1 / на 1000 ватт * на стоимость тарифа выбранного пользователем (дробное число)
                    resultPriceTwoRateLed = (float) (selectedPowerLed * baseResultTwoRate / watt * summPriceRate2);
                    Log.d(LOG_TAG, "result price Led 2: " + resultPriceTwoRateLed);
                    // если чекбокс включен, то тариф 2 расчитывается
                    if (enableCheckTwoRate == true) {

                        ledTextView.setText(String.valueOf(Math.round(resultPriceLed + resultPriceTwoRateLed) + " руб.")); //Math.round - округление значения
                    } else if (enableCheckTwoRate == false) {
                        ledTextView.setText(String.valueOf(Math.round(resultPriceLed) + " руб."));
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setClicklistenerChangeSpinner() {
        spinnerChangeLamp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                changeLamp = (int) spinnerChangeLamp.getSelectedItem();
                Log.d(LOG_TAG, "change: " + changeLamp);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    // цена обычной лампочки
    public void setClicklistenerPriceLampSpinner() {
        spinnerLampPrice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                priceLamp = (int) spinnerLampPrice.getSelectedItem();
                Log.d(LOG_TAG, "price Lamp: " + priceLamp);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setClicklistenerPriceLedSpinner() {
        spinnerPriceLed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                priceLed = (int) spinnerPriceLed.getSelectedItem();
                Log.d(LOG_TAG, "price Led: " + priceLed);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    // метод для свайпа активити
    View.OnTouchListener activitySwiped = new OnSwipeTouchListener(this) {
        public boolean onSwipeRight() {

            Intent intent = new Intent(LampInfoActivity.this, RatesActivity.class);
            startActivity(intent);
            return true;
        }

        public boolean onSwipeLeft() {

            Intent intent = new Intent(LampInfoActivity.this, SettlementsActivity.class);
            intent.putExtra("power", selectedPower);
            intent.putExtra("powerLed", selectedPowerLed);
            intent.putExtra("percent", percentBase);
            intent.putExtra("dataYears", baseResult);
            intent.putExtra("dataYearsTwo", baseResultTwoRate);
            intent.putExtra("resultPriceRate1", summPriceRate1);
            intent.putExtra("resultPriceRate2", summPriceRate2);
            intent.putExtra("checked", enableCheckTwoRate);
            intent.putExtra("changeLamp", changeLamp);
            intent.putExtra("priceLamp", priceLamp);
            intent.putExtra("priceLed", priceLed);
            startActivity(intent);

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

    public void adapterSpinnerChange() {

        SpinnerAdapter spinnerAdapter = new ArrayAdapter<Integer>(this, // создаем адаптер между раскрывающимся списком и массивом
                android.R.layout.simple_spinner_item, listChangeLamp);
        ((ArrayAdapter<Integer>) spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChangeLamp.setAdapter(spinnerAdapter);
    }

    public void adapterSpinnerPowerAndPriceLed() {

        SpinnerAdapter spinnerAdapter = new ArrayAdapter<Integer>(this, // создаем адаптер между раскрывающимся списком и массивом
                android.R.layout.simple_spinner_item, listPowerLed);
        ((ArrayAdapter<Integer>) spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPowerLed.setAdapter(spinnerAdapter);

        SpinnerAdapter mSpinnerAdapter = new ArrayAdapter<Integer>(this, // создаем адаптер между раскрывающимся списком и массивом
                android.R.layout.simple_spinner_item, listPriceLed);
        ((ArrayAdapter<Integer>) mSpinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPriceLed.setAdapter(mSpinnerAdapter);
    }




    // вывод информации по нажатию на иконку информации
    public void onClickInfoChange(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LampInfoActivity.this);
        builder.setTitle(ru.example.mike.ledlampeconomist.R.string.icon_info_title) // заголовок диалогового окна
                .setMessage(ru.example.mike.ledlampeconomist.R.string.icon_info_change_lamp)//текст сообщения
                .setIcon(ru.example.mike.ledlampeconomist.R.drawable.icon_info)// изображение иконки в заголовке
                .setCancelable(false)
                .setNegativeButton(ru.example.mike.ledlampeconomist.R.string.icon_info_close_button, // кнопка закрытия сообщения
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void onClickAgo(View view) {
        Intent intent = new Intent(LampInfoActivity.this, RatesActivity.class);
        startActivity(intent);
    }


    public void onClickForward(View view) {
        Intent intent = new Intent(LampInfoActivity.this, SettlementsActivity.class);
        intent.putExtra("power", selectedPower);
        intent.putExtra("powerLed", selectedPowerLed);
        intent.putExtra("percentBase", percentBase);
        intent.putExtra("dataYears", baseResult);
        intent.putExtra("dataYearsTwo", baseResultTwoRate);
        intent.putExtra("resultPriceRate1", summPriceRate1);
        intent.putExtra("resultPriceRate2", summPriceRate2);
        intent.putExtra("checked", enableCheckTwoRate);
        intent.putExtra("changeLamp", changeLamp);
        intent.putExtra("priceLamp", priceLamp);
        intent.putExtra("priceLed", priceLed);
        startActivity(intent);
    }


}
