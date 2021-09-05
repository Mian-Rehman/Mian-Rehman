package com.example.bluejay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainDashboard extends AppCompatActivity {



    NavigationView navMenu;
    ActionBarDrawerToggle toggle;
    DrawerLayout drayerLayout;
    BottomNavigationView bottom_navigation;

    Fragment temp=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);


        Toolbar toolbar=findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);

        navMenu=findViewById(R.id.navMenu);
        drayerLayout=findViewById(R.id.drawerlayout);
        bottom_navigation=findViewById(R.id.bottom_navigation);


        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new Fragment_MainDashboard_cat()).commit();


        toggle=new ActionBarDrawerToggle(this,drayerLayout,toolbar,R.string.app_name,R.string.app_name);
        drayerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.nav_profile:
                      //  Toast.makeText(MainDashboard.this, "nav Profile", Toast.LENGTH_SHORT).show();


                        break;

                    case R.id.nav_setting:
                        Toast.makeText(MainDashboard.this, "nav Setting", Toast.LENGTH_SHORT).show();

                        Intent profile_intent=new Intent(MainDashboard.this,Setting.class);
                        startActivity(profile_intent);
                        finish();
                        break;

                    case R.id.nav_payMethod:
                        Toast.makeText(MainDashboard.this, "nav Payment Method", Toast.LENGTH_SHORT).show();
                        toolbar.setTitle("Payment Method");
                        drayerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_connect_friends:

                        Toast.makeText(MainDashboard.this, "nav Connect With Friends", Toast.LENGTH_SHORT).show();
                        toolbar.setTitle("Setting");
                        drayerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_share_App:

                        try {
                            Intent intent=new Intent(Intent.ACTION_SEND);
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_SUBJECT,R.string.app_name);
                            intent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName());
                            startActivity(Intent.createChooser(intent,"Share With"));
                        } catch (Exception e) {
                            Toast.makeText(MainDashboard.this, "Unable to share this app", Toast.LENGTH_SHORT).show();
                        }
                        drayerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_Logout:

                        Toast.makeText(MainDashboard.this, "nav Logout", Toast.LENGTH_SHORT).show();
                        drayerLayout.closeDrawer(GravityCompat.START);
                        break;

                }



                return false;
            }
        });


       bottom_navigation.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
           @SuppressLint("NonConstantResourceId")
           @Override
           public void onNavigationItemReselected(@NonNull MenuItem item) {

               switch (item.getItemId())
               {
                   case R.id.bottom_Learning:
                       Toast.makeText(MainDashboard.this, "Bottom Learning", Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.bottom_Profile:
                       Toast.makeText(MainDashboard.this, "Bottom Profile", Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.bottom_notification:
                       Toast.makeText(MainDashboard.this, "Bottom Notification", Toast.LENGTH_SHORT).show();
                       break;
               }


           }
       });




    }
}