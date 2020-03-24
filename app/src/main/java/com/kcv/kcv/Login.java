package com.kcv.kcv;

import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class Login extends AppCompatActivity {
    EditText editEmail, editPassword, editName;
    private Session session;

    String URL;

    JSONParser jsonParser;
    int loginState = 0;
    String curUserId = "";

    Bundle userInfoBundle;

    int i=0;


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        URL= getString(R.string.url)+"/test_android/index.php";

        session = new Session(getApplicationContext());
        //check if user logged in session is there
        curUserId = session.getUserId();
        if(!curUserId.equals("")){
            //if yes go to homepage
            startActivity(new Intent(Login.this,Mainpage1.class));
        }

        //else normal log-in

        jsonParser = new JSONParser();
        editName = findViewById(R.id.username_edittext);
        editPassword = findViewById(R.id.password_edittext);


        TextView textView = findViewById(R.id.textView2);

        String text = "Don't have an account? Register";

        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent=new Intent(Login.this,signinpage.class);
                startActivity(intent);
            }

            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.RED);
                ds.setUnderlineText(false);
            }
        };


        ss.setSpan(clickableSpan1, 23, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void login1(View view) throws JSONException {
        if(editName.getText().toString().equals("admin") &&editPassword.getText().toString().equals("admin")){
            //sample login for testing purposes
            startActivity(new Intent(Login.this,Mainpage1.class));
        }else{
            AttemptLogin attemptLogin= new AttemptLogin(Login.this);
            JSONObject json = null;
            try {
                json = attemptLogin.execute(editName.getText().toString(),editPassword.getText().toString(), "").get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(json!= null){

                loginState = Integer.parseInt(json.getString("success"));
                curUserId = json.getString("userId");
                String curName = json.getString("first_name") + " " + json.getString("last_name");
                String curEmail = json.getString("email");
                String curMobile = json.getString("mobile");
                String curUserType = json.getString("user_type");
                if(loginState == 1){
                    userInfoBundle = getUserInfo(json);
                    session.setUserId(String.valueOf(curUserId));
                    session.setEmail(String.valueOf(curEmail));
                    session.setMobile(String.valueOf(curMobile));
                    session.setName(curName);
                    session.setUserType(curUserType);
                    Intent intent=new Intent(Login.this,Mainpage1.class);
                    intent.putExtras(userInfoBundle);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Please enter correct derails", Toast.LENGTH_SHORT).show();
                }
            }
        }


    }
//    public Bundle getUserInfo(int curUserId){
//        class GetUserInfo extends AsyncTask<String, Void, String>{
//
//            @Override
//            protected String doInBackground(String... strings) {
//
//                return null;
//            }
//        }
//        GetUserInfo userInfo = new GetUserInfo();
//        try {
//            String result = userInfo.execute().get();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Bundle userInfoBundle = null;
//        return userInfoBundle;
//    }
public Bundle getUserInfo(JSONObject json){
    Bundle b = new Bundle();
    String fname = null, lname = null;
    try {
        lname = json.getString("first_name");
        fname = json.getString("last_name");
    } catch (JSONException e) {
        e.printStackTrace();
    }
    b.putString("first_name", fname);
    b.putString("last_name", lname);

    return b;

}

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(Login.this)
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit KCV App ?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("EXIT", true);
                        startActivity(intent);
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void downloadFile(View view) {

        File file=new File(getExternalFilesDir(null),"Dummy");
        /*
        Create a DownloadManager.Request with all the information necessary to start the download
         */
        DownloadManager.Request request=new DownloadManager.Request(Uri.parse(getString(R.string.url)+"/test_android/3.csv"))
                .setTitle("Dummy File")// Title of the Download Notification
                .setDescription("Downloading")// Description of the Download Notification
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)// Visibility of the download Notification
                .setDestinationUri(Uri.fromFile(file))// Uri of the destination file
                .setAllowedOverMetered(true)// Set if download is allowed on Mobile network
                .setAllowedOverRoaming(true);// Set if download is allowed on roaming network
        DownloadManager downloadManager= (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        Long downloadID = downloadManager.enqueue(request);// enqueue puts the download request in



    }
}
