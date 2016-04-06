package ru.example.mike.ladlampeconomist;


import android.app.AlertDialog;
import android.content.DialogInterface;
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
    public RatesActivity() {
    }

    private static List<Integer> spinnerOne = new ArrayList<>(); // список для выбора стоимости в рублях
    private static List<Integer> spinnerTwo = new ArrayList<>(); // список для выбора стоимости в копейках
    private static List<Integer> spinnerHours = new ArrayList<>();// список для выбора работы лампочки в часах

    private static List<Integer> spinner_rates_2_one = new ArrayList<>(); // список для выбора стоимости в рублях для тарифа 2
    private static List<Integer> spinner_rates_2_two = new ArrayList<>(); // список для выбора стоимости в копейках для тарифа 2
    private static List<Integer> spinner_rates_2_hours = new ArrayList<>(); // список для выбора работы лампочки в часах
    private static List<Integer> spinner_percent = new ArrayList<>(); // список для выбора процента удорожания

    TextView textView;
    Spinner spinnerRub;
    Spinner spinnerKopeck;
    Spinner spinnerHour;

    Spinner spinnerRatesTwoRub;
    Spinner spinnerRatesTwoKopeck;
    Spinner spinnerRatesTwoHour;
    Spinner spinnerPercent;
    private int resultTimeYears;
    private int resultTimeYearsTwoRate;
    private int selected3; //целое число
    private int selected3TwoRate;
    private int selectedRubRateOne;
    private int resultPriceRub;
    private int selectedKopRateOne;
    private int resultPriceKopeck;
    private double summPrice = resultPriceRub + ((double)resultPriceKopeck/100);

    final String LOG_TAG = "myLogs";

// статически заполняем списки выборов так как никогда не меняются значения
    static {
        for (int i = 0; i <= 50; i++) {
            spinnerOne.add(i);
        }
        for (int i = 0; i <= 50; i++) {
            spinner_rates_2_one.add(i);
        }
        for (int i = 0; i <= 99; i++) {
            spinnerTwo.add(i);
        }
        for (int i = 0; i <= 99; i++) {
            spinner_rates_2_two.add(i);
        }

        for (int i = 1; i <= 24; i++) {
            spinnerHours.add(i);
        }
        for (int i = 1; i <= 24; i++) {
            spinner_rates_2_hours.add(i);
        }
        for (int i = 0; i <=50; i++){
            spinner_percent.add(i);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rates);
           findViewById(R.id.tableLayoutRates).setOnTouchListener(activitySwiped);



        textView = (TextView) findViewById(R.id.textView5);

        spinnerRub = (Spinner) findViewById(R.id.spinnerOne);
        spinnerKopeck = (Spinner) findViewById(R.id.spinnerTwo);
        spinnerHour = (Spinner) findViewById(R.id.spinnerHours);

        spinnerRatesTwoRub = (Spinner) findViewById(R.id.spinner_rates_2_one);
        spinnerRatesTwoKopeck = (Spinner) findViewById(R.id.spinner_rates_2_two);
        spinnerRatesTwoHour = (Spinner) findViewById(R.id.spinner_rates_2_hours);
        spinnerPercent = (Spinner) findViewById(R.id.spinner_percent);

        adapterSpinnerOne();
        adapterSpinnerTwo();
        adapterSpinnerHours();
        adapterSpinnerRates2One();
        adapterSpinnerRates2Two();
        adapterSpinnerRates2Hours();
        adapterSpinnerPercent();

        setClicklistenerTo3Spinner();
        setClicklistenerTwoRatesSpinner();
        setClicklistenerRubSpinner();
        setClicklistenerKopSpinner();


    }

    public int getResultTimeYearsTwoRate() {
        return resultTimeYearsTwoRate;
    }

    public int getResultTimeYears() {
        return resultTimeYears;
    }

    public double getSummPrice() {
        return summPrice;
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
    public void adapterSpinnerPercent() {
        SpinnerAdapter spinnerAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, spinner_percent);
        ((ArrayAdapter<Integer>) spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPercent.setAdapter(spinnerAdapter);

    }


    public void onClickAgo(View view) {
        Intent intent = new Intent(RatesActivity.this, MainActivity.class);
        startActivity(intent);
    }
// переход на активити с вводом данных ламп
    public void onClickForward(View view) {
        Intent intent = new Intent(RatesActivity.this, LampInfoActivity.class);
        startActivity(intent);
    }

    public void setClicklistenerTo3Spinner() {
        spinnerHour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                selected3 = (int) spinnerHour.getSelectedItem();
                try {

                    resultTimeYears = selected3 * 360;
                    Log.d(LOG_TAG, "resultTimeYear: " + resultTimeYears);
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

    public void setClicklistenerTwoRatesSpinner() {
        spinnerRatesTwoHour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                selected3TwoRate = (int) spinnerRatesTwoHour.getSelectedItem();
                try {

                    resultTimeYearsTwoRate = selected3TwoRate * 360;
                    Log.d(LOG_TAG, "resultTime2Rate: " + resultTimeYearsTwoRate);

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

    public void setClicklistenerRubSpinner() {
        spinnerRub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                selectedRubRateOne = (int) spinnerRub.getSelectedItem();
                try {

                    resultPriceRub = selectedRubRateOne;
                    Log.d(LOG_TAG, "resultPriceRub: " + resultPriceRub);
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

    public void setClicklistenerKopSpinner() {
        spinnerKopeck.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                selectedKopRateOne = (int) spinnerKopeck.getSelectedItem();
                try {

                    resultPriceKopeck = selectedKopRateOne;
                    Log.d(LOG_TAG, "resultPriceKop: " + resultPriceKopeck);

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

    View.OnTouchListener activitySwiped = new OnSwipeTouchListener(this) {
        public boolean onSwipeRight() {
            Intent intent = new Intent(RatesActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        public boolean onSwipeLeft() {
            Intent intent = new Intent(RatesActivity.this, LampInfoActivity.class);
            startActivity(intent);

            return true;
        }

        public boolean onSwipeBottom() {
            return true;
        }

    };

// создаем Диалоговое окно AlertDialog для иконки ИНФО
    public void onClickInfo(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(RatesActivity.this);
        builder.setTitle(R.string.icon_info_title)
                .setMessage(R.string.icon_info)
                .setIcon(R.drawable.icon_info)
                .setCancelable(false)
                .setNegativeButton(R.string.icon_info_close_button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
// создаем Диалоговое окно AlertDialog для иконки ИНФО в Тарифе 2
    public void onClickInfoRate2(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(RatesActivity.this);
        builder.setTitle(R.string.icon_info_title)
                .setMessage(R.string.icon_info_rate_2)
                .setIcon(R.drawable.icon_info)
                .setCancelable(false)
                .setNegativeButton(R.string.icon_info_close_button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}


