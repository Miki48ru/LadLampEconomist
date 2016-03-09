package ru.example.mike.ladlampeconomist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mike on 08.03.2016.
 */
public class SpinnerFiller {



    private static List<Integer> spinnerOne = new ArrayList<>();
    private static List<Integer> spinnerTwo = new ArrayList<>(); //TODO: перевести "копейку"
    private static List<Integer> spinnerHours = new ArrayList<>();


    public static List<Integer> getSpinnerHours() {
        return filler(spinnerHours, 24, false);
    }

    public static List<Integer> getSpinnerOne() {
        return filler(spinnerOne, 50, true);
    }

    public static List<Integer> getSpinnerTwo() {
        return filler(spinnerTwo, 100, true);
    }


    private static List<Integer> filler(List<Integer> filling, int count, boolean includeLast){
        count = includeLast ? count : count - 1;

        for (int i = 1; i <= count; i++) {
            filling.add(i);
        }
        return filling;
    }
}
