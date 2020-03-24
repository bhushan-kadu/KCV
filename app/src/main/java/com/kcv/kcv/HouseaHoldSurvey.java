package com.kcv.kcv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.HashMap;

public class HouseaHoldSurvey extends AppCompatActivity {

    ParameterExtraction parameterExtraction;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housea_hold_survey);

        linearLayout = findViewById(R.id.householdSurvey0);
        parameterExtraction = new ParameterExtraction();

    }
    public void reg(View view) {
        HashMap<String, String> params = new HashMap<>();
        int count = 0;

        params = parameterExtraction.getParams(linearLayout, count, params);
        count = parameterExtraction.getCounter();

        Log.i("params", params.toString());
        Log.i("params", String.valueOf(params.size()));
        Bundle extras = new Bundle();
        extras.putSerializable("form-params",params);
        extras.putInt("count", count);
        Intent intent=new Intent(view.getContext(),HouseaHoldSurvey1.class);
        intent.putExtras(extras);
        startActivity(intent);
//        view.getContext().startActivity(intent);
    }
}
