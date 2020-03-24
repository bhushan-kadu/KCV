package com.kcv.kcv;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class HouseaHoldSurvey11 extends AppCompatActivity {
    private Bundle b ;
    HashMap<String, String> params;
    int count;
    LinearLayout linearLayout;
    ParameterExtraction parameterExtraction;
    JSONParser jsonParser=new JSONParser();
    Session session;
    String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housea_hold_survey11);
        b = this.getIntent().getExtras();
        if(b!= null){
            params = (HashMap<String, String>) b.getSerializable("form-params");
            count = b.getInt("count");
        }
        linearLayout = findViewById(R.id.householdSurvey11);
        parameterExtraction = new ParameterExtraction();
        session = new Session(getApplicationContext());
        URL = getString(R.string.url)+"/test_android/upload-form-data.php";

    }
    public void reg(View view) {

        params = parameterExtraction.getParams(linearLayout, count, params);
        count = parameterExtraction.getCounter();



        Log.i("params", params.toString());
        Log.i("params", String.valueOf(params.size()));
        Bundle extras = new Bundle();
        extras.putSerializable("form-params",params);
        extras.putInt("count", count);
        Intent intent=new Intent(view.getContext(),Headsurveysubmit.class);
        intent.putExtras(extras);
        Toast.makeText(this, getResources().getString(R.string.SubmittedSuccesfully), Toast.LENGTH_SHORT).show();

        params.put("userId",String.valueOf(session.getUserId()));
        params.put("surveyName","householdsurveyques");
        uploadForm(new JSONObject(params));


        view.getContext().startActivity(intent);
    }

    public void uploadForm(final JSONObject json){


        class UploadForm extends AsyncTask<JSONObject, Void, String> {

            ProgressDialog loading;

            @Override

            protected void onPreExecute() {


                super.onPreExecute();
                loading = ProgressDialog.show(HouseaHoldSurvey11.this, "Uploading...", null,true,true);

            }


            @Override
            protected String doInBackground(JSONObject... jsonObjects) {
                ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("data", String.valueOf(json)));
                jsonParser.makeHttpRequest(URL, "POST", params);
                return  "";
            }

            protected void onPostExecute(String result) {

                loading.dismiss();
            }


        }

        UploadForm uploadForm = new UploadForm();
        uploadForm.execute(json);

    }
}
