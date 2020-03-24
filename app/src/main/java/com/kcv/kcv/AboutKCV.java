package com.kcv.kcv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class AboutKCV extends AppCompatActivity {
    ViewFlipper viewFlipper;
    int[] imagos = {R.drawable.pic2, R.drawable.pic3, R.drawable.pic5, R.drawable.pic2, R.drawable.pic3};
    ViewFlipper viewFlipper1;
    int[] imagos1 = {R.drawable.pic2, R.drawable.pic3, R.drawable.pic5, R.drawable.pic2, R.drawable.pic3};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_kcv);
        viewFlipper = (ViewFlipper) findViewById ( R.id.v_flipper );
        for (int i = 0; i < imagos.length; i++) {
            flip_image ( imagos[i] );
        }
        viewFlipper1 = (ViewFlipper) findViewById ( R.id.v_flipper1);
        for (int j = 0; j < imagos1.length; j++) {
            flip_image1 ( imagos1[j] );
        }
    }

    private void flip_image(int i) {
        ImageView imageView = new ImageView ( this );
        imageView.setBackgroundResource ( i );
        viewFlipper.addView ( imageView );
        viewFlipper.setFlipInterval ( 4000 );
        viewFlipper.setAutoStart ( true );
        viewFlipper.setInAnimation ( this, android.R.anim.slide_in_left );
        viewFlipper.setInAnimation ( this, android.R.anim.slide_out_right );

    }


    private void flip_image1(int j) {
        ImageView imageView = new ImageView ( this );
        imageView.setBackgroundResource ( j );
        viewFlipper1.addView ( imageView );
        viewFlipper1.setFlipInterval ( 4000 );
        viewFlipper1.setAutoStart ( true );
        viewFlipper1.setInAnimation ( this, android.R.anim.slide_in_left );
        viewFlipper1.setInAnimation ( this, android.R.anim.slide_out_right );

    }
}