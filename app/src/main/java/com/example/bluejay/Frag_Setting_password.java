package com.example.bluejay;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class Frag_Setting_password extends Fragment {



        ImageView img_password_back;
        Button btn_newPass_save;
        EditText ed_current_pass,ed_new_pass,ed_confirm_pass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_frag__setting_password, container, false);

        img_password_back=v.findViewById(R.id.img_password_back);
        btn_newPass_save=v.findViewById(R.id.btn_newPass_save);

        //EditText
        ed_current_pass=v.findViewById(R.id.ed_current_pass);
        ed_new_pass=v.findViewById(R.id.ed_new_pass);
        ed_confirm_pass=v.findViewById(R.id.ed_confirm_pass);





        img_password_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(),Setting.class);
                startActivity(intent);
                getActivity().finish();

            }
        });


        btn_newPass_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Demo App Save Button", Toast.LENGTH_SHORT).show();
            }
        });











        return v;
    }
}