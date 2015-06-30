package com.myfirst.lingaraj.theholybible;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by lingaraj on 6/25/2015.
 */
public class splash_main extends Activity {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                Intent i = new Intent(splash_main.this, Bible_home.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }




}
