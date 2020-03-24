package com.kcv.kcv;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class swipe3 extends AppCompatActivity {
    CheckBox checkBox;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe3);
        Window window=getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.setStatusBarColor(getColor(R.color.statusbar));
        }else
        {
            window.setStatusBarColor(getResources().getColor(R.color.statusbar));
        }
        checkBox = findViewById(R.id.toggleInfoPages);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Session session = new Session(getApplicationContext());
                session.setShowInfo(!isChecked);
            }
        });
    }
    public void back3(View view) {
        Intent intent= new Intent(swipe3.this,Login.class);
        startActivity(intent);
    }



    public void next3(View view) {
        Intent intent= new Intent(swipe3.this,Login.class);
        startActivity(intent);
    }
}
