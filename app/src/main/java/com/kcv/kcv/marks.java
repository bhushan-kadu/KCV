package com.kcv.kcv;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class marks extends AppCompatActivity {
    TextView tView,tView1,tView2,tView3;
    int total=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);
        tView=(TextView)findViewById(R.id.textView1);
        tView1=(TextView)findViewById(R.id.textView5);
        tView2=(TextView)findViewById(R.id.textView3);
        tView3=(TextView)findViewById(R.id.textView7);
        SharedPreferences pref = getApplication().getSharedPreferences("Options", MODE_PRIVATE);
        String and=pref.getString("marks", "");
        tView.setText(and+"/"+"50");
        SharedPreferences pref1 = getApplication().getSharedPreferences("Options1", MODE_PRIVATE);
        String and1=pref1.getString("marks1", "");
        tView2.setText(and1+"/"+"50");
        SharedPreferences pref2 = getApplication().getSharedPreferences("Options2", MODE_PRIVATE);
        String and2=pref2.getString("marks2", "");
        tView1.setText(and2+"/"+"50");
         total=total+ Integer.parseInt(and)+Integer.parseInt(and1)+Integer.parseInt(and2);
        tView3.setText(total+"/"+"150");
    }
}
