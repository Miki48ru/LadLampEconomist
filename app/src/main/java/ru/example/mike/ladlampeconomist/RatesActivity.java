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


    private final int[] spinners = new int[]{R.id.spinnerOne,  R.id.spinnerTwo, R.id.spinnerHours};
    private final List<List> spinnersData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rates);
        fillData();
        fillSpinners();
    }

    private void fillData(){
        spinnersData.add(SpinnerFiller.getSpinnerOne());
        spinnersData.add(SpinnerFiller.getSpinnerTwo());
        spinnersData.add(SpinnerFiller.getSpinnerHours());
    }


    private void fillSpinners(){
        for (int i = 0; i < spinners.length; i++) {
            Spinner spinner = (Spinner)findViewById(spinners[i]);
            ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this,  spinnersData.get(i), android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        }
    }

    public void onClickAgo(View view) {
        Intent intent = new Intent(RatesActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void onClickForward(View view) {
    }

}
