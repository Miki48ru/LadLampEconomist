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
    private GestureDetectorCompat mGestureDetectorCompat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGestureDetectorCompat = new GestureDetectorCompat(this, new MyGestureListener());
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mGestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e2.getX() < e1.getX()) {
                Intent intent = new Intent(
                        MainActivity.this, RatesActivity.class);
                startActivity(intent);
            }
            return true;
        }
    }






    public void onClickStart(View view) {
        Intent intent = new Intent(MainActivity.this, RatesActivity.class);
        startActivity(intent);
    }
}

