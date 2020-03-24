package com.kcv.kcv;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

class AttemptLogin extends AsyncTask<String, String, JSONObject> {
    Context context = null;
    AttemptLogin(Context ctx){
        this.context = ctx;
        URL = this.context.getString(R.string.url) + "/test_android/index.php";
    }

    EditText editEmail, editPassword, editName;

    String URL;

    JSONParser jsonParser=new JSONParser();
    int loginState = 0;

    int i=0;
    ProgressDialog loading;

        @Override

        protected void onPreExecute() {


            super.onPreExecute();
            loading = ProgressDialog.show(context, "Uploading...", null,true,true);

        }

        @Override

        protected JSONObject doInBackground(String... args) {

            String name= args[0];
            String password = args[1];
            String email = args[2];

            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", name));
            params.add(new BasicNameValuePair("password", password));
            if(email.length()>0){
                String first_name = args[3];
                String last_name = args[4];
                String profile_pic = args[5];
                String mobile = args[6];
                String gender = args[7];
                String birthdate = args[8];
                String state = args[9];
                String district = args[10];
                String taluka = args[11];
                String village = args[12];
                String pincode = args[13];
                params.add(new BasicNameValuePair("email",email));
                params.add(new BasicNameValuePair("first_name",first_name));
                params.add(new BasicNameValuePair("last_name",last_name));
                params.add(new BasicNameValuePair("profile_pic",profile_pic));
                params.add(new BasicNameValuePair("mobile",mobile));
                params.add(new BasicNameValuePair("gender",gender));
                params.add(new BasicNameValuePair("birthdate",birthdate));
                params.add(new BasicNameValuePair("state",state));
                params.add(new BasicNameValuePair("district",district));
                params.add(new BasicNameValuePair("taluka",taluka));
                params.add(new BasicNameValuePair("village",village));
                params.add(new BasicNameValuePair("pincode",pincode));

            }

            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);


            return json;

        }

        protected void onPostExecute(JSONObject result) {

            loading.dismiss();
            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

            try {
                if (result != null) {
                    Toast.makeText(context,result.getString("message"),Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }