package com.kcv.kcv;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class swipe1 extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe1);
        Window window=getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.setStatusBarColor(getColor(R.color.statusbar));
        }else
        {
            window.setStatusBarColor(getResources().getColor(R.color.statusbar));
        }
    }

    public void click1(View view) {
        Intent intent=new Intent(swipe1.this,swipe2.class);
        startActivity(intent);
    }

    public void skip(View view) {
        Intent intent=new Intent(swipe1.this,Login.class);
        startActivity(intent);
    }
}
