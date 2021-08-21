package com.example.bluejay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bluejay.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignupScreen extends AppCompatActivity {


    Button btn_createAcc,btn_sign_google,btn_sign_facebook,btn_sign_twitter;
    EditText ed_name,ed_username,d_Email,ed_password,ed_Confirm_password;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);


        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("Users");

        //type casting
        btn_createAcc=findViewById(R.id.btn_createAcc);
        btn_sign_google=findViewById(R.id.btn_sign_google);
        btn_sign_facebook=findViewById(R.id.btn_sign_facebook);
        btn_sign_twitter=findViewById(R.id.btn_sign_twitter);
        ed_name=findViewById(R.id.ed_name);
        ed_username=findViewById(R.id.ed_username);
        d_Email=findViewById(R.id.d_Email);
        ed_password=findViewById(R.id.ed_password);
        ed_Confirm_password=findViewById(R.id.ed_Confirm_password);



        btn_createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String UserName=ed_username.getText().toString();
                DatabaseReference reference=FirebaseDatabase.getInstance().getReference("Users");
                Query query=reference.orderByChild("userName").equalTo(UserName);

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists())
                        {
                            String checkUser=snapshot.child(UserName).child("userName").getValue(String.class);
                            if (checkUser.equals(UserName))
                            {
                                ed_username.setError("Username Already Exist!");
                                ed_username.requestFocus();
                                ed_username.setText("");
                                return;

                            }
                        }

                        else
                        {
                            String userName=ed_username.getText().toString().trim();
                            String Name=ed_name.getText().toString().trim();
                            String userEmail=d_Email.getText().toString().trim();
                            String userPassword=ed_password.getText().toString().trim();

                            String confirmPassword = ed_Confirm_password.getText().toString();

                            if (TextUtils.isEmpty(UserName))
                            {
                                ed_username.setError("invalid userName!");
                                ed_username.requestFocus();
                                return;
                            }
                            if (TextUtils.isEmpty(Name))
                            {
                                ed_name.setError("invalid Name!");
                                ed_name.requestFocus();
                                return;
                            }

                            if (TextUtils.isEmpty(userEmail))
                            {
                                if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches())
                                {
                                    d_Email.setError("Enter valid email address");
                                    d_Email.requestFocus();
                                    d_Email.setText("");
                                }
                                else
                                {
                                    d_Email.setError("invalid Email address!");
                                    d_Email.requestFocus();
                                    return;
                                }

                            }
                            if (TextUtils.isEmpty(userPassword))
                            {
                                ed_password.setError("invalid password!");
                                ed_password.requestFocus();
                                return;
                            }
                            if (TextUtils.isEmpty(confirmPassword))
                            {
                                ed_Confirm_password.setError("invalid password");
                                ed_Confirm_password.requestFocus();
                                return;
                            }


                            if (userPassword.equals(ed_Confirm_password.getText().toString().trim()))
                            {
                                Users myuser = new Users(userName,Name,userEmail,userPassword);
                                String key=myRef.child(UserName).getKey();
                                myRef.child(key).setValue(myuser);
                                Toast.makeText(SignupScreen.this, "Account Created", Toast.LENGTH_LONG).show();
                                Intent loginIntent = new Intent(SignupScreen.this,LoginScreen.class);
                                startActivity(loginIntent);
                                finish();

                            }

                            else
                            {
                                ed_password.setError("Password Doesn't Match");
                                ed_password.requestFocus();
                                ed_password.setText("");
                                ed_password.setText("");
                            }





                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

    }
}