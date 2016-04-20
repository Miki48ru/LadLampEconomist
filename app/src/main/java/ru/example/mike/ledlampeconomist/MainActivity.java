package ru.example.mike.ledlampeconomist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;


public class MainActivity extends Activity {



    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.main_activity).setOnTouchListener(activitySwiped);

    }



    public void onClickStart(View view) {
        Intent intent = new Intent(MainActivity.this, RatesActivity.class);
        startActivity(intent);
    }


    View.OnTouchListener activitySwiped = new OnSwipeTouchListener(this) {
        public boolean onSwipeRight() {
            return true;
        }

        public boolean onSwipeLeft() {
            Intent intent = new Intent(MainActivity.this, RatesActivity.class);
            startActivity(intent);
            return true;
        }

        public boolean onSwipeBottom() {
            return true;
        }

    };
}


