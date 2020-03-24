package com.kcv.kcv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.HashMap;

public class HouseaHoldSurvey5 extends AppCompatActivity {
    private Bundle b ;
    HashMap<String, String> params;
    int count;
    LinearLayout linearLayout;
    ParameterExtraction parameterExtraction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housea_hold_survey5);
        b = this.getIntent().getExtras();
        if(b!= null){
            params = (HashMap<String, String>) b.getSerializable("form-params");
            count = b.getInt("count");
        }
        linearLayout = findViewById(R.id.householdSurvey5);
        parameterExtraction = new ParameterExtraction();
    }
    public void reg(View view) {
        params = parameterExtraction.getParams(linearLayout, count, params);
        count = parameterExtraction.getCounter();



        Log.i("params", params.toString());
        Log.i("params", String.valueOf(params.size()));

        Bundle extras = new Bundle();
        extras.putSerializable("form-params",params);
        extras.putInt("count", count);
        Intent intent=new Intent(view.getContext(),HouseaHoldSurvey6.class);
        intent.putExtras(extras);
        view.getContext().startActivity(intent);
    }
}
