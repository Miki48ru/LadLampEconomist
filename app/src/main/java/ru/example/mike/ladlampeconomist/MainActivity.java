package ru.example.mike.ladlampeconomist;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



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


