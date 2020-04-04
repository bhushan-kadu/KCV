package com.kcv.kcv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;

public class HouseaHoldSurvey6 extends AppCompatActivity {
    private Bundle b ;
    HashMap<String, String> params;
    int count;
    LinearLayout linearLayout;
    ParameterExtraction parameterExtraction;
    int pageCount;
    ArrayList<Integer> counterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housea_hold_survey6);
        b = this.getIntent().getExtras();
        if(b!= null){
            params = (HashMap<String, String>) b.getSerializable("form-params");
            counterList = b.getIntegerArrayList("counter_list");
            pageCount = b.getInt("curPage");
            count = b.getInt("count");
        }
        linearLayout = findViewById(R.id.householdSurvey6);
        parameterExtraction = new ParameterExtraction();
    }
    public void reg(View view) {
        counterList.add(count);
        pageCount = new Session(getApplicationContext()).getcurPage();
        params = parameterExtraction.getParams(linearLayout, counterList.get(pageCount), params);
        count = parameterExtraction.getCounter();



        Log.i("params", params.toString());
        Log.i("params", String.valueOf(params.size()));

        Bundle extras = new Bundle();
        extras.putSerializable("form-params",params);
        extras.putInt("count", count);
        extras.putInt("curPage", pageCount);
        new Session(getApplicationContext()).setcurPage(pageCount+1);
        extras.putIntegerArrayList("counter_list", counterList);
        Intent intent=new Intent(view.getContext(),HouseaHoldSurvey7.class);
        intent.putExtras(extras);
        view.getContext().startActivity(intent);
    }
    public void onBackPressed() {
        pageCount = new Session(getApplicationContext()).getcurPage();
        new Session(getApplicationContext()).setcurPage(pageCount-1);
        super.onBackPressed();
    }
}
