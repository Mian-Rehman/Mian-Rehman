package com.example.bluejay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Setting extends AppCompatActivity {

    ImageView img_setting_back,img_lock_icon,img_account_icon,img_security_icon,img_help_icon;
    TextView tv_password_setting,tv_account_setting,tv_security_setting,tv_help_setting;

    LinearLayout invite_layout,help_layout,security_Layout,account_layout,password_layout;
    RelativeLayout rel_setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        invite_layout=findViewById(R.id.invite_layout);
        help_layout=findViewById(R.id.help_layout);
        security_Layout=findViewById(R.id.security_Layout);
        account_layout=findViewById(R.id.account_layout);
        password_layout=findViewById(R.id.password_layout);
        rel_setting=findViewById(R.id.rel_setting);
        img_setting_back=findViewById(R.id.img_setting_back);



        img_setting_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Setting.this,MainDashboard.class);
                startActivity(intent);
                finish();
            }
        });

        password_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rel_setting.setVisibility(View.INVISIBLE);
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.setting_framlayout,new Frag_Setting_password()).commit();

            }
        });

        account_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rel_setting.setVisibility(View.INVISIBLE);
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.setting_framlayout,new Frag_sett_Account()).commit();
            }
        });

        security_Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Setting.this, "Security", Toast.LENGTH_SHORT).show();
            }
        });

        help_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Setting.this, "Help?", Toast.LENGTH_SHORT).show();
            }
        });




        invite_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Setting.this, "Invite friends", Toast.LENGTH_SHORT).show();
            }
        });

    }
}