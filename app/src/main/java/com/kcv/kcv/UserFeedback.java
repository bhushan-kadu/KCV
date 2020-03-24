package com.kcv.kcv;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class UserFeedback extends AppCompatActivity {

    EditText feedback ;
    String URL ="";
    HashMap<String, String> param = new HashMap<String, String>();

    Button sendFeedback ;
    JSONParser jsonParser = new JSONParser();
    Session session ;
    RelativeLayout thankYouLayput;
    LinearLayout feedbackLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feedback);
        feedback = findViewById(R.id.EditText_FeedbackBody);
        thankYouLayput = findViewById(R.id.thankLayout);
        feedbackLayout = findViewById(R.id.feedbackLayout);


        sendFeedback  = findViewById(R.id.ButtonSendFeedback);
        URL = getString(R.string.url)+"/test_android/upload-feedback.php";
        session = new Session(getApplicationContext());


    }

    public void sendFeedback(View view) {
        if(!feedback.getText().toString().trim().equals("")){

            param.put("userId",session.getUserId());
            param.put("feedback",feedback.getText().toString().trim());

            uploadForm(new JSONObject(param));

            thankYouLayput.setVisibility(View.VISIBLE);
            feedbackLayout.setVisibility(View.INVISIBLE);


        }
    }
    public void uploadForm(final JSONObject json){


        class UploadForm extends AsyncTask<JSONObject, Void, String> {

            ProgressDialog loading;

            @Override

            protected void onPreExecute() {

                super.onPreExecute();
                loading = ProgressDialog.show(UserFeedback.this, "Uploading...", null,true,true);

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
                Toast.makeText(getApplicationContext(), "Succesfully sumbitted", Toast.LENGTH_LONG).show();
            }


        }

        UploadForm uploadForm = new UploadForm();
        uploadForm.execute(json);

    }
}
