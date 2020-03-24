package com.kcv.kcv;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class Mainpage1 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ViewFlipper viewFlipper;
    int[] imagos = {R.drawable.pic2, R.drawable.pic5, R.drawable.pic3, R.drawable.pic5, R.drawable.pic3};
    NavigationView navigationView;


    MenuItem headSurvey, householdSurvey;
    private  Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage1);

        navigationView =  findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();

        headSurvey = nav_Menu.findItem(R.id.Survey1);
        householdSurvey = nav_Menu.findItem(R.id.Survey2);



        session = new Session(getApplicationContext());
        if(session.getUserType().equals("head")){
            householdSurvey.setVisible(false);
        }else{
            headSurvey.setVisible(false);
        }
        viewFlipper = (ViewFlipper) findViewById(R.id.v_flipper);
        for (int i = 0; i < imagos.length; i++) {
            flip_image(imagos[i]);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(Mainpage1.this, Floating_action.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            new AlertDialog.Builder(Mainpage1.this)
                    .setTitle("Exit")
                    .setMessage("Are you sure you want to exit KCV App ?")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                            Intent intent = new Intent(Mainpage1.this, MainActivity.class);
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainpage1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(Mainpage1.this, setting.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.User_profile) {
            setTitle("User Profile");
            Intent intent = new Intent(Mainpage1.this, User.class);

            startActivity(intent);


        } else if (id == R.id.Dashboard) {
            setTitle("Dashboard");
            Intent intent = new Intent(Mainpage1.this, Dashboard.class);
            startActivity(intent);

        } else if (id == R.id.Change_Password) {
            setTitle("Change Password");
            Intent intent = new Intent(Mainpage1.this, Changepaswword.class);
            startActivity(intent);


        } else if (id == R.id.Change_Language) {
            setTitle("Change Language");
            Intent intent = new Intent(Mainpage1.this, ChangeLanguage.class);
            startActivity(intent);


        } else if (id == R.id.About_KCV) {
            setTitle("About KCV");
            Intent intent = new Intent(Mainpage1.this, AboutKCV.class);
            startActivity(intent);


        } else if (id == R.id.Saadgram_Documentry) {
            setTitle("KCV Documentry");
            Intent intent = new Intent(Mainpage1.this, KCVdocumentry.class);
            startActivity(intent);


        } else if (id == R.id.How_To_Use) {
            setTitle("How To Use");
            Intent intent = new Intent(Mainpage1.this, How_to_use.class);
            startActivity(intent);


        } else if (id == R.id.Survey) {
            setTitle("Survey");
            Intent intent = new Intent(Mainpage1.this, Survey.class);
            startActivity(intent);
        }else if (id == R.id.Survey1) {
            setTitle(" Head Survey");
            Intent intent = new Intent(Mainpage1.this, Headsurvey.class);
            startActivity(intent);
        }else if (id == R.id.Survey2) {
            setTitle("House Hold Survey");
            Intent intent = new Intent(Mainpage1.this, HouseaHoldSurvey.class);
            startActivity(intent); }
        else if (id == R.id.Blog) {
            setTitle("Blog");
            Intent intent = new Intent(Mainpage1.this, Blog.class);
            startActivity(intent);


        } else if (id == R.id.User_Feedback) {
            setTitle("User Feedback");
            Intent intent = new Intent(Mainpage1.this, UserFeedback.class);
            startActivity(intent);


        } else if (id == R.id.Developed_By) {
            setTitle("Developed By");
            Intent intent = new Intent(Mainpage1.this, developedby.class);
            startActivity(intent);


        } else if (id == R.id.Logout) {
            session.setUserId("");
            Intent intent = new Intent(Mainpage1.this, Login.class);
            Toast.makeText(getApplicationContext(), "Sucessfully Logged out", Toast.LENGTH_LONG).show();
            startActivity(intent);


        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void flip_image(int i) {
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(i);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
        viewFlipper.setInAnimation(this,android.R.anim.slide_out_right);

    }


    public void health(View view) {
        Intent intent = new Intent(Mainpage1.this, healthinfo.class);
        startActivity(intent);

    }

    public void land(View view) {
        Intent intent = new Intent(Mainpage1.this, landinfo.class);
        startActivity(intent);

    }

    public void edu(View view) {
        Intent intent = new Intent(Mainpage1.this, eduinfo.class);
        startActivity(intent);

    }

    public void water(View view) {
        Intent intent = new Intent(Mainpage1.this, waterinfo.class);
        startActivity(intent);

    }

    public void energy(View view) {
        Intent intent = new Intent(Mainpage1.this, energyinfo.class);
        startActivity(intent);

    }

    public void forest(View view) {
        Intent intent = new Intent(Mainpage1.this, forestinfo.class);
        startActivity(intent);

    }

    public void transport(View view) {
        Intent intent = new Intent(Mainpage1.this, transportinfo.class);
        startActivity(intent);

    }

    public void gov(View view) {
        Intent intent = new Intent(Mainpage1.this, governenceinfo.class);
        startActivity(intent);

    }
}
