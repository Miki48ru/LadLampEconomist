package ru.example.mike.ladlampeconomist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class RatesActivity extends AppCompatActivity {

    private static List<Integer> spinnerOne = new ArrayList<>();
    private static List<Integer> spinnerTwo = new ArrayList<>(); //TODO: перевести "копейку"
    private static List<Integer> spinnerHours = new ArrayList<>();
    static {
        for (int i = 1; i <= 100; i++) {
            spinnerOne.add(i);
        }
        for (int i = 1; i <= 50; i++) {
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

    }





    public void onClickAgo(View view) {
        Intent intent = new Intent(RatesActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void onClickForward(View view) {
    }

}
