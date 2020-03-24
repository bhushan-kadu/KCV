package com.kcv.kcv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class WATER extends AppCompatActivity {
    String [] item={"DRINKING WATER","AGRICULTURE WATER","REGULAR USE WATER"};
    ListView L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        L = (ListView)findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, item);
        L.setAdapter(adapter);
        L.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                if (i == 0) {
                    Intent intent = new Intent(WATER.this, drinking_water.class);

                    startActivityForResult(intent, 0);

                } else if (i == 1) {
                    Intent intent = new Intent(WATER.this, Agriculture_water.class);

                    startActivityForResult(intent, 1);

                } else if (i == 2) {
                    Intent intent = new Intent(WATER.this,regularuse_water.class);

                    startActivityForResult(intent, 2);
                }
            }
        });


    }

    public void list(View view) {
        Intent intent= new Intent(WATER.this,Survey.class);
        startActivity(intent);
    }
}




