package com.pedoran.datasiswa_231.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.pedoran.datasiswa_231.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Thread(new Runnable() {
            @Override
            public void run() {
                loadingApp();
                toHome();
                finish();
            }
        }).start();
    }

    private void loadingApp(){
        for (int progress=0; progress<100; progress+=10) {
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("ERROR",e.getMessage());
            }
        }
    }

    private void toHome(){
        Intent homie = new Intent(SplashScreen.this,Dashboard.class);
        startActivity(homie);
    }
}
