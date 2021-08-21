package com.example.bluejay;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bluejay.Model.Users;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
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

public class SignupScreen extends AppCompatActivity {


    Button btn_createAcc,btn_sign_google,btn_sign_facebook,btn_sign_twitter;
    EditText ed_name,ed_username,d_Email,ed_password,ed_Confirm_password,ed_PhoneNo;

    FirebaseDatabase database;
    DatabaseReference myRef;
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
        ed_PhoneNo=findViewById(R.id.ed_PhoneNo);


        mAuth = FirebaseAuth.getInstance();

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
                            String userPhoneNo=ed_PhoneNo.getText().toString().trim();


                            String confirmPassword = ed_Confirm_password.getText().toString();

                            if (TextUtils.isEmpty(UserName))
                            {
                                ed_username.setError("invalid userName!");
                                ed_username.requestFocus();
                                return;
                            }
                            if (TextUtils.isEmpty(userPhoneNo))
                            {
                                ed_PhoneNo.setError("invalid PhoneN0!");
                                ed_PhoneNo.requestFocus();
                                return;
                            }
                            if (TextUtils.isEmpty(Name))
                            {
                                ed_name.setError("invalid Name!");
                                ed_name.requestFocus();
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
                                Users myuser = new Users(userName,Name,userEmail,userPassword,userPhoneNo);
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


        createRequest();


        btn_sign_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resultLauncher.launch(new Intent(mGoogleSignInClient.getSignInIntent()));

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

                            startActivity(new Intent(SignupScreen.this,MainDashboard.class));
                            finish();
                            Toast.makeText(SignupScreen.this, "Login Successfully", Toast.LENGTH_SHORT).show();


                        }
                        else
                            {

                                Toast.makeText(SignupScreen.this, "Sign in Failed.", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


}