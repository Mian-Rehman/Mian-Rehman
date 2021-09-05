package com.example.bluejay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.OAuthProvider;

public class TwitterAuthActivty extends LoginScreen {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        firebaseAuth=FirebaseAuth.getInstance();


        OAuthProvider.Builder provider = OAuthProvider.newBuilder("twitter.com");

        // Target specific email with login hint.
        provider.addCustomParameter("lang", "fr");


        Task<AuthResult> pendingResultTask = firebaseAuth.getPendingAuthResult();
        if (pendingResultTask != null)
        {
            // There's something already here! Finish the sign-in for your user.
            pendingResultTask
                    .addOnSuccessListener(
                            new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {


                                    startActivity(new Intent(TwitterAuthActivty.this,MainDashboard.class));
                                    Toast.makeText(TwitterAuthActivty.this, "Login Successful", Toast.LENGTH_SHORT).show();

                                }
                            })
                    .addOnFailureListener(
                            new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Handle failure.

                                    Toast.makeText(TwitterAuthActivty.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });
        } else
            {
            // There's no pending result so you need to start the sign-in flow.
            // See below.

                firebaseAuth
                        .startActivityForSignInWithProvider(/* activity= */ this, provider.build())
                        .addOnSuccessListener(
                                new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {

                                        startActivity(new Intent(TwitterAuthActivty.this,MainDashboard.class));
                                        Toast.makeText(TwitterAuthActivty.this, "Login Successful", Toast.LENGTH_SHORT).show();

                                    }
                                })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Handle failure.

                                        Toast.makeText(TwitterAuthActivty.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                });
        }

    }
}