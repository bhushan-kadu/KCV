package com.kcv.kcv;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getIntent().getBooleanExtra("EXIT", false)) {
//            System.exit(0);
//            finish();//inbuilt method to perform back pressed operation
            this.finish();


        } else {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {


            /*  Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                        .getBoolean("isFirstRun", true);

               if (isFirstRun) {
                    //show start activity

                   startActivity(new Intent(MainActivity.this, swipe1.class));
                    finish();
                }else {
                    startActivity(new Intent(MainActivity.this, Mainpage1.class));
                   finish();

                }


                getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                        .putBoolean("isFirstRun", false).commit();*/

            Session session = new Session(getApplicationContext());
            if(!session.getShowInfo()){
                startActivity(new Intent(MainActivity.this, Login.class));

            }else{
                startActivity(new Intent(MainActivity.this, swipe1.class));

            }


                }

            }, 3000);
        }

    }



}
