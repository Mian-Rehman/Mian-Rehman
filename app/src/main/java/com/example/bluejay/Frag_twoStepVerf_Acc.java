package com.example.bluejay;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class Frag_twoStepVerf_Acc extends Fragment
{


    Button btn_twoStep;
    ImageView img_twoStep_back;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_frag_two_step_verf__acc, container, false);

        img_twoStep_back=v.findViewById(R.id.img_twoStep_back);


        img_twoStep_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.Frame_TwoStep,new Frag_sett_Account()).commit();
                getActivity().finish();
            }
        });




        return v;
    }
}