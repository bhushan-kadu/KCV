package com.kcv.kcv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.HashMap;

public class Headsurveysubmit extends AppCompatActivity {
    private Bundle b ;
    HashMap<String, String> params;
    int count;
    LinearLayout linearLayout;
    ParameterExtraction parameterExtraction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headsurveysubmit);
        b = this.getIntent().getExtras();
        if(b!= null){
            params = (HashMap<String, String>) b.getSerializable("form-params");
            count = b.getInt("count");
        }
        linearLayout = findViewById(R.id.head_survey_3LL);
        parameterExtraction = new ParameterExtraction();
    }

    public void head_survey_submit(View view) {

        Intent intent=new Intent(Headsurveysubmit.this,Mainpage1.class);

        startActivity(intent);
    }
}
