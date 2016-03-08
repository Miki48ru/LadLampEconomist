package ru.example.mike.ladlampeconomist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mike on 08.03.2016.
 */
public class SpinnerFiller {


    private List<Integer> spinner1 = new ArrayList<>();
    private List<Integer> spinner2 = new ArrayList<>();
    private List<Integer> spinner3 = new ArrayList<>();

    public List<Integer> getSpinner1() {
        for (int i = 0; i < 50; i++) {
            spinner1.add(i);
        }

        return spinner1;
    }

    public List<Integer> getSpinner2() {
        for (int i = 0; i < 100; i++) {
            spinner2.add(i);
        }
        return spinner2;
    }

    public List<Integer> getSpinner3() {
        for (int i = 0; i < 24; i++) {
            spinner3.add(i);
        }
        return spinner3;
    }

}
