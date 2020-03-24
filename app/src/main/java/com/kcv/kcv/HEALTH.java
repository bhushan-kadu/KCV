package com.kcv.kcv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class HEALTH extends AppCompatActivity {
    String [] item={"HEALTH1","HEALTH2","HEALTH3"};
    ListView L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
    }
}
