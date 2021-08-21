package com.example.bluejay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {

    TextView tv_login_forgotPass,tv_login_signbyphone;
    CheckBox check_login_remember;
    EditText ed_login_user,ed_login_pass;
    Button btn_Login,btn_Signup,btn_Google,btn_Facrbook,btn_Twitter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);


        //textview type casting
        tv_login_forgotPass=findViewById(R.id.tv_login_forgotPass);
        tv_login_signbyphone=findViewById(R.id.tv_login_signbyphone);

        //checkbox type casting
        check_login_remember=findViewById(R.id.check_login_remember);

        //EditText type casting
        ed_login_user=findViewById(R.id.ed_login_user);
        ed_login_pass=findViewById(R.id.ed_login_pass);

        //button type casting
        btn_Login=findViewById(R.id.btn_Login);
        btn_Signup=findViewById(R.id.btn_Signup);
        btn_Google=findViewById(R.id.btn_Google);
        btn_Facrbook=findViewById(R.id.btn_Facrbook);
        btn_Twitter=findViewById(R.id.btn_Twitter);



        btn_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(LoginScreen.this,SignupScreen.class);
                startActivity(intent);
                finish();
            }
        });


        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginScreen.this, "Login Button Demo App", Toast.LENGTH_SHORT).show();
            }
        });


        btn_Facrbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginScreen.this, "Facebook Button Demo app", Toast.LENGTH_SHORT).show();
            }
        });


        btn_Google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginScreen.this, "Google button Demo app", Toast.LENGTH_SHORT).show();
            }
        });



        btn_Twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginScreen.this, "Twitter button demo app", Toast.LENGTH_SHORT).show();
            }
        });

    }
}