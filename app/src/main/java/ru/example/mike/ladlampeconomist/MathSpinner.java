package ru.example.mike.ladlampeconomist;

import android.app.Activity;
import android.widget.Spinner;

/**
 * Created by Mike on 14.03.2016.
 */
public class MathSpinner extends Activity {

   private Spinner spinner1 = (Spinner) findViewById(R.id.spinnerOne);
   private Integer selected1 = spinner1. getSelectedItemPosition();

   private Spinner spinner2 = (Spinner) findViewById(R.id.spinnerTwo);
   private Integer selected2 = spinner2. getSelectedItemPosition();

   private Spinner spinner3 = (Spinner) findViewById(R.id.spinnerHours);
   private Integer selected3 = spinner3. getSelectedItemPosition();

    public int moneyTime(){
        int result = (selected1+(selected2/100)) * selected3;
        return result;
    }

}
