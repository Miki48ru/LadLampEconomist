package ru.example.mike.ladlampeconomist;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mike on 09.03.2016.
 */
public class TestSpinners extends Activity {


    private static final List<Integer> list = new ArrayList<>();

    static {
        for (int i = 1; i <= 100; i++) {
            list.add(i);
        }

    }
}



