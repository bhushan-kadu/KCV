package com.kcv.kcv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.HashMap;

public class HeadSurvey2 extends AppCompatActivity {
    private Bundle b ;
    HashMap<String, String> params;
    int count;
    LinearLayout linearLayout;
    ParameterExtraction parameterExtraction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_survey2);
        b = this.getIntent().getExtras();
        if(b!= null){
            params = (HashMap<String, String>) b.getSerializable("form-params");
            count = b.getInt("count");
        }
        linearLayout = findViewById(R.id.head_survey_2LL);
        parameterExtraction = new ParameterExtraction();
    }

    public void head_survey_2_next(View view) {
        params = parameterExtraction.getParams(linearLayout, count, params);
        count = parameterExtraction.getCounter();



        Log.i("params", params.toString());
        Bundle extras = new Bundle();
        extras.putSerializable("form-params",params);
        extras.putInt("count", count);
        Intent intent=new Intent(HeadSurvey2.this,Headsurvey3.class);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
