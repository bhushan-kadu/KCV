package com.kcv.kcv;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class swipe2 extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe2);
        Window window=getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.setStatusBarColor(getColor(R.color.statusbar));
        }else
        {
            window.setStatusBarColor(getResources().getColor(R.color.statusbar));
        }
    }
    public void back2(View view) {
        Intent intent= new Intent(swipe2.this,Login.class);
        startActivity(intent);
    }



    public void next1(View view) {
        Intent intent= new Intent(swipe2.this,swipe3.class);
        startActivity(intent);
    }
}


