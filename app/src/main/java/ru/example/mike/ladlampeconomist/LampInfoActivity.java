package ru.example.mike.ladlampeconomist;

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
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class LampInfoActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    private static List<Integer> listPower = new ArrayList<>(); // массив мощности лампы
    private static List<Integer> listPrice = new ArrayList<>();
    private static List<Integer> listChangeLamp = new ArrayList<>();



    private int baseResult = RatesActivity.getInstance().getResultTimeYears(); // переменная со значением времени из активности с расчетом по тарифам

    private int baseResultTwoRate =  RatesActivity.getInstance().getResultTimeYearsTwoRate();

    private float summPriceRate1 =  RatesActivity.getInstance().getSummPrice(); // цена по тарифу 1

    private float summPriceRate2 = RatesActivity.getInstance().getSummPriceTwoRate(); // цена по тарифу 2

    private boolean enableCheckTwoRate = RatesActivity.getInstance().isChecked(); // стоит галочка тарифа 2 или не стоит

    private int selectedPower;
    private double resultPrice;
    private double watt = 1000.0;
    private  double resultPriceTwoRate;




    Spinner spinnerPowerLamp;
    Spinner spinnerLampPrice;
    Spinner spinnerChangeLamp;

    TextView textView;


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

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp_info);
        findViewById(R.id.lamp_info_activity).setOnTouchListener(activitySwiped);



        textView = (TextView)findViewById(R.id.text_table_1);


        spinnerPowerLamp = (Spinner) findViewById(R.id.spinner_power);
        adapterSpinnerPower();


        spinnerLampPrice = (Spinner) findViewById(R.id.spinner_lamp_price);
        adapterSpinnerPrice();

        spinnerChangeLamp = (Spinner) findViewById(R.id.spinner_lamp_change);
        adapterSpinnerChange();

        setClicklistenerPriceRatesSpinner();

    }


    public void setClicklistenerPriceRatesSpinner() {
        spinnerPowerLamp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                selectedPower = (int) spinnerPowerLamp.getSelectedItem();
                try {

                    resultPrice = (double) selectedPower * baseResult / watt * summPriceRate1; // мощность * общее время работы ламп по тарифу 1 / на 1000 ватт * на стоимость тарифа выбранного пользователем (дробное число)
                    resultPriceTwoRate = (double) selectedPower * baseResultTwoRate / watt * summPriceRate2;
                    // если чекбокс включен, то тариф 2 расчитывается
                    if (enableCheckTwoRate == true){

                        textView.setText(String.valueOf(Math.round(resultPrice + resultPriceTwoRate) + " руб.")); //Math.round - округление значения
                    }
                    else if (enableCheckTwoRate == false) {
                        textView.setText(String.valueOf(Math.round(resultPrice) + " руб."));
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "что то не так", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Нужно выбрать пункт", Toast.LENGTH_SHORT).show();
            }
        });
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

    public void adapterSpinnerChange() {

        SpinnerAdapter spinnerAdapter = new ArrayAdapter<Integer>(this, // создаем адаптер между раскрывающимся списком и массивом
                android.R.layout.simple_spinner_item, listChangeLamp);
        ((ArrayAdapter<Integer>) spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChangeLamp.setAdapter(spinnerAdapter);
    }


    // вывод информации по нажатию на иконку информации
    public void onClickInfoChange(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LampInfoActivity.this);
        builder.setTitle(R.string.icon_info_title) // заголовок диалогового окна
                .setMessage(R.string.icon_info_change_lamp)//текст сообщения
                .setIcon(R.drawable.icon_info)// изображение иконки в заголовке
                .setCancelable(false)
                .setNegativeButton(R.string.icon_info_close_button, // кнопка закрытия сообщения
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

    }


}
