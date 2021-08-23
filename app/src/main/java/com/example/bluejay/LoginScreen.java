package com.example.bluejay;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bluejay.Model.Users;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginScreen extends AppCompatActivity {

    TextView tv_login_forgotPass,tv_login_signbyphone;
    CheckBox check_login_remember;
    EditText ed_login_user,ed_login_pass;
    Button btn_Login,btn_Signup,btn_Google,btn_Facrbook,btn_Twitter;

    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;



    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user!=null)
        {
            //set code you want to show Name and Email of sign in google
        }
    }

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


        mAuth = FirebaseAuth.getInstance();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Users");



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


                String LoginUserName=ed_login_user.getText().toString().trim();
                String LoginPassword=ed_login_pass.getText().toString();

                Query check_user_Details =reference.orderByChild("userName").equalTo(LoginUserName);

                check_user_Details.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()) {
                            String checkUser = snapshot.child(LoginUserName).child("userName").getValue(String.class);
                            String checkpass = snapshot.child(LoginUserName).child("userPassword").getValue(String.class);


                            if (checkUser.equals(LoginUserName))
                            {


                                if (checkpass.equals(LoginPassword))
                                {
                                    Users user =snapshot.child(LoginUserName).getValue(Users.class);

                                    SharedPreferences sp=getSharedPreferences("USERDATA",MODE_PRIVATE);
                                    SharedPreferences.Editor ed=sp.edit();
                                    ed.putString("currentUserName",user.getUserName());
                                    ed.putString("currentName",user.getName());
                                    ed.putString("currentPhoneNo",user.getUserPhoneNo());
                                    ed.putString("currentEmail",user.getUserEmail());
                                    ed.putString("currentPassword",user.getUserPassword());
                                    ed.apply();
                                    Intent intent=new Intent(LoginScreen.this,MainDashboard.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else
                                {
                                    ed_login_pass.setError("incorrect password");
                                    ed_login_pass.requestFocus();
                                    ed_login_pass.setText("");

                                }


                            }
                            else
                                {
                                ed_login_user.setError("incorrect username");
                                ed_login_user.requestFocus();
                                ed_login_user.setText("");
                                return;
                            }

                        }



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }
        });


        tv_login_signbyphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(LoginScreen.this,FragPhoneLogin.class);
                startActivity(intent);

            }
        });


        btn_Facrbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginScreen.this, "Facebook Button Demo app", Toast.LENGTH_SHORT).show();
            }
        });


        createRequest();

        btn_Google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultLauncher.launch(new Intent(mGoogleSignInClient.getSignInIntent()));
            }
        });



        btn_Twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginScreen.this, "Twitter button demo app", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void createRequest() {

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            , new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == Activity.RESULT_OK)
                    {
                        Intent intent =result.getData();

                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intent);
                        try {
                            // Google Sign In was successful, authenticate with Firebase
                            GoogleSignInAccount account = task.getResult(ApiException.class);

                            firebaseAuthWithGoogle(account.getIdToken());
                        } catch (ApiException e) {
                            // Google Sign In failed, update UI appropriately

                        }

                    }

                }
            });


    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            startActivity(new Intent(LoginScreen.this,MainDashboard.class));
                            finish();
                            Toast.makeText(LoginScreen.this, "Login Successfully", Toast.LENGTH_SHORT).show();


                        }
                        else
                        {

                            Toast.makeText(LoginScreen.this, "Sign in Failed.", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }



}