package com.kcv.kcv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.HashMap;

public class Headsurvey extends AppCompatActivity {

    LinearLayout linearLayout ;
    ParameterExtraction parameterExtraction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headsurvey);

        linearLayout = findViewById(R.id.head_survey_0LL);
        parameterExtraction = new ParameterExtraction();
    }

    public void head_survey_0_next(View view) {

        HashMap<String, String> params = new HashMap<>();
        int count = 0;

//       for(int i = 0; i< linearLayout.getChildCount(); i++){
//           //if view is EditText then add its value to pramas
//           View v = linearLayout.getChildAt(i);
//           if(v instanceof EditText){
//               EditText et = (EditText) v;
//              params.put(String.valueOf(count), et.getText().toString());
//              count++;
//           }else if(v instanceof LinearLayout){
//               // else if it is LinearLayout then extract the radio group value from LinearLayout
//               // and add to params
//               for(int j = 0; j< ((LinearLayout) v).getChildCount(); j++){
//                   View vv = ((LinearLayout) v).getChildAt(j);
//                   if(vv instanceof RadioGroup){
//                       RadioGroup rg = (RadioGroup) vv;
//                       int selectedGenderId = rg.getCheckedRadioButtonId();
//                       RadioButton myRadio =  findViewById(selectedGenderId);
//                       String radVal = myRadio.getText().toString();
//                       if(radVal.equals("Yes")) radVal = "y";
//                       else radVal = "n";
//                       params.put(String.valueOf(count), radVal);
//                       count++;
//                   }else if(vv instanceof EditText){
//                       EditText et = (EditText) vv;
//                       params.put(String.valueOf(count), et.getText().toString());
//                       count++;
//                   }
//               }
//
//           }
//       }
        params = parameterExtraction.getParams(linearLayout, count, params);

        count = parameterExtraction.getCounter();
        Log.i("params", params.toString());
       Bundle extras = new Bundle();
       extras.putSerializable("form-params",params);
       extras.putInt("count", count);
        Intent intent=new Intent(Headsurvey.this,HeadSurvey1.class);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public HashMap<String, String> getParams(int count, LinearLayout layout, HashMap<String, String> params){

        for(int i = 0; i< linearLayout.getChildCount(); i++) {
            //if view is EditText then add its value to pramas
            View v = linearLayout.getChildAt(i);
            if (v instanceof EditText) {
                EditText et = (EditText) v;
                params.put(String.valueOf(count), et.getText().toString());
                count++;
            }else if(v instanceof RadioGroup){
                RadioGroup rg = (RadioGroup) v;
                int selectedGenderId = rg.getCheckedRadioButtonId();
                RadioButton myRadio =  findViewById(selectedGenderId);
                String radVal = myRadio.getText().toString();
                if(radVal.equals("Yes")) radVal = "y";
                else radVal = "n";
                params.put(String.valueOf(count), radVal);
                count++;
            }else if(v instanceof LinearLayout){
                params = getParams(count, (LinearLayout)v, params);
                count++;
            }
        }
        return params;
    }
}
