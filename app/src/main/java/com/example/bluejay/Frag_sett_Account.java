package com.example.bluejay;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class Frag_sett_Account extends Fragment {


    FrameLayout framelayout_Account;
    ImageView img_Account_back;
    LinearLayout Two_Step_layout,change_phone_No_layout,Request_Acc_Info_lay,Delete_Account_lay;
    RelativeLayout rel_Account;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_frag_sett__account, container, false);


        img_Account_back=v.findViewById(R.id.img_Account_back);

        //Linear layout type casting
        Two_Step_layout=v.findViewById(R.id.Two_Step_layout);
        change_phone_No_layout=v.findViewById(R.id.change_phone_No_layout);
        Request_Acc_Info_lay=v.findViewById(R.id.Request_Acc_Info_lay);
        Delete_Account_lay=v.findViewById(R.id.Delete_Account_lay);

        //FrameLayout
        framelayout_Account=v.findViewById(R.id.framelayout_Account);
        rel_Account=v.findViewById(R.id.rel_Account);


        img_Account_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Setting.class);
                startActivity(intent);
                getActivity().finish();
            }
        });


        Two_Step_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rel_Account.setVisibility(View.INVISIBLE);
                FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framelayout_Account,new Frag_twoStepVerf_Acc()).commit();
                Toast.makeText(getActivity(), "Two Step Verification", Toast.LENGTH_SHORT).show();
            }
        });

        change_phone_No_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Change Phone No", Toast.LENGTH_SHORT).show();
            }
        });

        Request_Acc_Info_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Request Account Info", Toast.LENGTH_SHORT).show();
            }
        });

        Delete_Account_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Delete Account", Toast.LENGTH_SHORT).show();
            }
        });




        return v;
    }
}