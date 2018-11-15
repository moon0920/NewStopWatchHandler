package com.example.edu.newstopwatchhandler;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textViewTime;
    Button btnStart, btnPause, btnReset;
    Handler handler = new Handler();
    Long startTime, millisecondTime, updateTime, timeBuff=0l;
    int seconds, minuts, milliSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTime = findViewById(R.id.textViewTime);
        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);
        btnPause = findViewById(R.id.btnPause);
        btnPause.setOnClickListener(this);
        btnReset = findViewById(R.id.btnReset);
        btnReset.setOnClickListener(this);
    }
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            millisecondTime = SystemClock.uptimeMillis()-startTime;
            updateTime = timeBuff + millisecondTime;
            seconds = (int)(updateTime/1000);
            minuts = seconds/60;
            seconds = seconds %60;
            milliSeconds = (int)(updateTime %1000);
            textViewTime.setText(minuts +":"+ String.format("%02d", seconds) +":"+ String.format("%03d",milliSeconds));
            handler.postDelayed(this,0);

//            SimpleDateFormat formatter = new SimpleDateFormat("mm : ss : SSS");
//            textViewTimer.setText(formatter.format(updateTime)); 다른출력 예제
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnStart:
                startTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable,0);
                break;
            case R.id.btnPause:
                timeBuff += millisecondTime;
                handler.removeCallbacks(runnable);
                break;
            case R.id.btnReset:
                break;
        }

    }

}
