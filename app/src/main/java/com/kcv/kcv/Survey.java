package com.kcv.kcv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Survey extends AppCompatActivity {
    String[] item = {"HEALTH", "LAND", "EDUCATION", "WATER", "ENERGY", "FOREST", "TRANSPORTATION","Goverence"};
    ListView L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        L = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, item);
        L.setAdapter(adapter);
        L.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                if (i == 0) {
                    Intent intent = new Intent(Survey.this, HEALTH.class);

                    startActivityForResult(intent, 0);

                } else if (i == 1) {
                    Intent intent = new Intent(Survey.this, LAND.class);

                    startActivityForResult(intent, 1);

                } else if (i == 2) {
                    Intent intent = new Intent(Survey.this, EDUCATION.class);

                    startActivityForResult(intent, 2);

                } else if (i == 3) {
                    Intent intent = new Intent(Survey.this, WATER.class);

                    startActivityForResult(intent, 3);

                } else if (i == 4) {
                    Intent intent = new Intent(Survey.this, ENERGY.class);

                    startActivityForResult(intent, 4);

                } else if (i == 5) {
                    Intent intent = new Intent(Survey.this, Forest.class);

                    startActivityForResult(intent, 5);

                } else if (i == 6) {
                    Intent intent = new Intent(Survey.this, Transportation.class);

                    startActivityForResult(intent, 6);
                } else if (i == 7) {
                    Intent intent = new Intent(Survey.this, Goverence.class);

                    startActivityForResult(intent, 7);
                }
            }
        });


    }
}
