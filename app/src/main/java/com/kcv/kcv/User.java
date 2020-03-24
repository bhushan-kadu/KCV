package com.kcv.kcv;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class User extends AppCompatActivity {

    Session session ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        session = new Session(getApplicationContext());


        TextView name = findViewById(R.id.name);
        TextView email = findViewById(R.id.email);
        TextView mobile = findViewById(R.id.mobile);

        name.setText(session.getName());
        email.setText(session.getEmail());
        mobile.setText(session.getMobile());



        photoUpload();


    }
    public void photoUpload(){
         class SendHttpRequestTask extends AsyncTask<String, Void, Bitmap> {
            @Override
            protected Bitmap doInBackground(String... params) {
                try {
                    URL url = new URL(getString(R.string.url)+"/PhotoUpload/uploads/"+session.getUserId()+".png");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    Bitmap myBitmap = BitmapFactory.decodeStream(input);
                    return myBitmap;
                }catch (Exception e){
                    Log.d("tag",e.getMessage());
                }
                return null;
            }

            @Override
            protected void onPostExecute(Bitmap result) {
                ImageView imageView = (ImageView) findViewById(R.id.userdp);
                imageView.setImageBitmap(result);
            }
        }

        SendHttpRequestTask sendHttpRequestTask = new SendHttpRequestTask();
         sendHttpRequestTask.execute();
    }

}
