package com.example.brandonzhu.stopwatch;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Window;
import android.widget.TextView;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.view.WindowManager;
import android.view.Display;
import android.graphics.Point;
import android.graphics.Color;
import android.content.Intent;
import android.util.Log;
import android.support.v4.app.Fragment;
import java.util.ArrayList;

public class Stopwatch extends Activity {
    private TextView tempTextView;
    private Button tempbtn,saveb;
    private Handler mHandler = new Handler();
    private long startTime;
    private long elapsedTime;
    private final int REFRESH_RATE = 100;
    private String hours,minutes,seconds,milliseconds,savetime;
    private long sec,min,hrs,mill;
    private boolean stopped = false;
    private Bitmap width, height;
    private int count = 0;
    private ArrayList store = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_stopwatch);

        ((Button)findViewById(R.id.stopButton)).setVisibility(View.GONE); //hide stop button initially
        Button placement = ((Button) findViewById(R.id.standButton));
        placement.setOnClickListener(new View.OnClickListener() {  //start record page activity
            @Override
            public void onClick(View v) {
                if (count == 0 && count < 5 ){
                    store.add("No data recorded");
                }
                else if (count > 0 && count < 5){
                    store.remove(0);
                }

                Log.i("Clicks", "You clicked the records page");
                Intent i = new Intent(Stopwatch.this, recordspage.class);
                Bundle bun = new Bundle();
                bun.putInt("ivalues",count);
                bun.putString("trans",savetime);
                bun.putBoolean("checkRes",stopped);
                i.putExtra("sto",store);
                i.putExtras(bun);
                startActivity(i);
            }
        });


    }



    public void startClick (View view){
        showStopButton();
        if (stopped){
            startTime = System.currentTimeMillis() - elapsedTime;
        }
        else{
            startTime = System.currentTimeMillis();
        }
        mHandler.removeCallbacks(startTimer);
        mHandler.postDelayed(startTimer,0);
    }


    public void stopClick (View view){
        hideStopButton();
        mHandler.removeCallbacks(startTimer);
        stopped = true;

    }

    public void resetClick (View view){
        stopped = false;
        ((TextView)findViewById(R.id.timer)).setText("00:00:00");
    }


    public void saveClick(View view){
        if (minutes == null){
            count +=1;
            savetime = "00:00:00";
            if (count < 6){
                store.add(savetime);
            }
        }
        else {
            savetime = minutes + ":" + seconds + ":" + milliseconds;
            count ++;
            if (count < 6){
                store.add(savetime);
            }
        }

    }


    private void showStopButton() {
        ((Button) findViewById(R.id.startButton)).setVisibility(View.GONE);
        ((Button) findViewById(R.id.resetButton)).setVisibility(View.GONE);
        ((Button) findViewById(R.id.stopButton)).setVisibility(View.VISIBLE);
        ((Button) findViewById(R.id.saveButton)).setVisibility(View.GONE);

    }
    private void hideStopButton(){
        ((Button)findViewById(R.id.startButton)).setVisibility(View.VISIBLE);
        ((Button)findViewById(R.id.resetButton)).setVisibility(View.VISIBLE);
        ((Button)findViewById(R.id.stopButton)).setVisibility(View.GONE);
        ((Button) findViewById(R.id.saveButton)).setVisibility(View.VISIBLE);

    }

    private void updateTimer (float time){

        sec = (long)(time/1000);
        min = (long)((time/1000)/60);
        hrs = (long)(((time/1000)/60)/60);

        sec = sec % 60;

        seconds = String.valueOf(sec);

        if (sec == 0){
            seconds = "00";
        }

        if (sec < 10 && sec > 0){
            seconds = "0" + seconds;
        }

        min = min % 60;

        minutes = String.valueOf(min);

        if (min == 0){
            minutes = "00";
        }

        if (min < 10 && min > 0){
            minutes = "0" + minutes;
        }

        hours = String.valueOf(hrs);

        if (hrs == 0){
            hours = "00";
        }

        if (hrs < 10 && hrs > 0){
            hours = "0" + hours;
        }

        milliseconds = String.valueOf((long)time);

        mill = (long)time;
        if (mill == 0){
            milliseconds = "00";
        }

        if ( mill < 10 && mill > 0){
            milliseconds = "0" + milliseconds;
        }

        milliseconds = milliseconds.substring(Math.max(milliseconds.length()-2,0 ));

        ((TextView)findViewById(R.id.timer)).setText(minutes + ":" + seconds + ":" + milliseconds);

    }



    private Runnable startTimer = new Runnable() {
        public void run(){
            elapsedTime = System.currentTimeMillis() - startTime;
            updateTimer(elapsedTime);
            mHandler.postDelayed(this,REFRESH_RATE);

        }
    };



}












