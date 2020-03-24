package com.kcv.kcv;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class acticityagriculturewater_q5 extends AppCompatActivity {
    SeekBar sBar;
    TextView tView;
    SharedPreferences sharedpreference;
    Button save1,finish1,list1,back1;
    int pval = 0;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acticityagriculturewater_q5);
        SharedPreferences sharedpreference = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());
        sBar = (SeekBar) findViewById(R.id.seekBar1);
        final SharedPreferences.Editor editor;
        tView = (TextView) findViewById(R.id.textview1);
        tView.setText(sBar.getProgress() + "/" + sBar.getMax());
        save1 = (Button) findViewById(R.id.save);

        list1 = (Button) findViewById(R.id.list);
        finish1 = (Button) findViewById(R.id.Finish);
        back1 = (Button) findViewById(R.id.back);
        SharedPreferences pref1 = getApplicationContext().getSharedPreferences("Options1", MODE_PRIVATE);
        editor = pref1.edit();
        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pval = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //write custom code to on start progress
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tView.setText(pval + "/" + seekBar.getMax());
            }
        });


        save1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref1 = getApplication().getSharedPreferences("Options1", MODE_PRIVATE);
                String and=pref1.getString("marks1", "");
                pval= pval+Integer.parseInt(and);

                i=1;
                editor.putString("marks1", String.valueOf(pval));
                editor.commit();
                Toast.makeText(getApplicationContext(), "Response saved!", Toast.LENGTH_SHORT).show();
                save1.setEnabled(false);
            }
        });
        finish1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 0) {
                    Toast.makeText(getApplicationContext(), "Please Save the ans", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), WATER.class);
                    startActivity(intent);


                }
            }
        });




        list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WATER.class);
                startActivity(intent);

            }
        });
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
