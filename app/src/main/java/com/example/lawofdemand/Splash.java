package com.example.lawofdemand;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by jaicksninan on 2/6/15.
 */
public class Splash extends Activity {


    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent i = new Intent(Splash.this, SplashPage.class);
                startActivity(i);


                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}
