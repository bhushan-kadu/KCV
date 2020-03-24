package com.kcv.kcv;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.HashMap;

public class ParameterExtraction {
    int counter;
    public HashMap<String, String> getParams(LinearLayout linearLayout, int count, HashMap<String, String> params){
        for(int i = 0; i< linearLayout.getChildCount(); i++){
            //if view is EditText then add its value to pramas
            View v = linearLayout.getChildAt(i);
            if(v instanceof EditText){
                EditText et = (EditText) v;
                params.put(String.valueOf(count), et.getText().toString());
                count++;
            }else if(v instanceof RadioGroup){
                RadioGroup rg = (RadioGroup) v;
                int selectedGenderId = rg.getCheckedRadioButtonId();
                RadioButton myRadio =  linearLayout.findViewById(selectedGenderId);
                String radVal = myRadio.getText().toString();
                if(radVal.equals("Yes")) radVal = "y";
                else if(radVal.equals("No")) radVal= "n";
                params.put(String.valueOf(count), radVal);
                count++;
            }else if(v instanceof LinearLayout){
                // else if it is LinearLayout then extract the radio group value from LinearLayout
                // and add to params
                for(int j = 0; j< ((LinearLayout) v).getChildCount(); j++){
                    View vv = ((LinearLayout) v).getChildAt(j);
                    if(vv instanceof RadioGroup){
                        RadioGroup rg = (RadioGroup) vv;
                        int selectedGenderId = rg.getCheckedRadioButtonId();
                        RadioButton myRadio =  linearLayout.findViewById(selectedGenderId);
                        String radVal = myRadio.getText().toString();
                        if(radVal.equals("Yes")) radVal = "y";
                        else if(radVal.equals("No")) radVal= "n";
                        params.put(String.valueOf(count), radVal);
                        count++;
                    }else if(vv instanceof EditText){
                        EditText et = (EditText) vv;
                        params.put(String.valueOf(count), et.getText().toString());
                        count++;
                    }
                }

            }
        }
        this.counter = count;
        return params;
    }
    int getCounter(){
        return  this.counter;
    }
}
