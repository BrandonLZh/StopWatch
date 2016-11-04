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
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by BrandonZhu on 2016-09-21.
 */
public class recordspage extends Activity {

    private String hi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.record_page);
        Button back = ((Button) findViewById(R.id.returnButton));
        ArrayList listoftime = (ArrayList) getIntent().getSerializableExtra("sto");
        back.setOnClickListener(new View.OnClickListener() { //return to previous activity
            @Override
            public void onClick(View v) {
                Log.i("Clicks", "You clicked the records page");

                onBackPressed();
            }
        });

        /**

        //get multiple values that are being passes
        String time = getIntent().getStringExtra("trans");
        int num = getIntent().getExtras().getInt("ivalues");
        Boolean check = getIntent().getExtras().getBoolean("checkRes");
        ((TextView) findViewById(R.id.testing)).setText(time);

        if (time == null) {
            ((TextView) findViewById(R.id.testing)).setText("No data recorded");
        } else {
            /**if (check == false && time == null){
                ((TextView) findViewById(R.id.testing)).setText("00:00:00");
            }
            else {
                if (num == 0) {
                    ((TextView) findViewById(R.id.testing)).setText(time);
                }
                else if (num > 0 && num < 6){
                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.addtimers);
                    TextView tx = new TextView(recordspage.this);
                    tx.setId(1);
                    linearLayout.addView(tx);
                    ((TextView) findViewById(1)).setText(time);

                }
            }
         **/


        hi = listoftime.toString().replace(",", "").replace("[", "").replace("]", "").trim();
/**
 for (int i = 0 ; i < listoftime.size();i++){
 hi = "\n" + listoftime;
 }

 **/
        ((TextView) findViewById(R.id.testing)).setText(hi);

    }



public void clearClick(View view){
    ArrayList listoftime = (ArrayList) getIntent().getSerializableExtra("sto");
    listoftime.removeAll(listoftime);
    hi = "";
    ((TextView) findViewById(R.id.testing)).setText("No data recorded");
}
}