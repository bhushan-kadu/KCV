package com.kcv.kcv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Home extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView firstname,lastname,email,birthdate,mobileno,age,address,state,district,taluka,pincode;
        firstname=(TextView)findViewById(R.id.textview1);
        firstname.setText(getIntent().getStringExtra("firstname"));

        lastname=(TextView)findViewById(R.id.textview2);
        lastname.setText(getIntent().getStringExtra("lastname"));

        mobileno=(TextView)findViewById(R.id.textview3);
        mobileno.setText(getIntent().getStringExtra("mobileno"));

        email=(TextView)findViewById(R.id.textview4);
        email.setText(getIntent().getStringExtra("email"));

        age=(TextView)findViewById(R.id.textview5);
        age.setText(getIntent().getStringExtra("age"));

        birthdate=(TextView)findViewById(R.id.textview6);
        birthdate.setText(getIntent().getStringExtra("birthdate"));

        address=(TextView)findViewById(R.id.textview7);
        address.setText(getIntent().getStringExtra("address"));

        taluka=(TextView)findViewById(R.id.textview8);
        taluka.setText(getIntent().getStringExtra("taluka"));

        district=(TextView)findViewById(R.id.textview9);
        district.setText(getIntent().getStringExtra("district"));

        pincode=(TextView)findViewById(R.id.textview10);
        pincode.setText(getIntent().getStringExtra("pincode"));

        state=(TextView)findViewById(R.id.textview11);
        state.setText(getIntent().getStringExtra("state"));







    }
}

