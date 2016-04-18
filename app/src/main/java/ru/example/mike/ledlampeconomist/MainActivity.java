package ru.example.mike.ledlampeconomist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ru.example.mike.ledlampeconomist.R.layout.activity_main);
        findViewById(ru.example.mike.ledlampeconomist.R.id.main_activity).setOnTouchListener(activitySwiped);

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


