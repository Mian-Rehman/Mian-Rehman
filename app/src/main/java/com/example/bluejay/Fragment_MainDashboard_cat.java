package com.example.bluejay;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_MainDashboard_cat#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_MainDashboard_cat extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_MainDashboard_cat() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_MainDashboard_cat.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_MainDashboard_cat newInstance(String param1, String param2) {
        Fragment_MainDashboard_cat fragment = new Fragment_MainDashboard_cat();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------


    //Declare Area

    GridLayout mainGridLayout;
    CardView card_PreKids,k_12_Grade;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment__main_dashboard_cat, container, false);
        //coding Area


        mainGridLayout= v.findViewById(R.id.mainGridLayout);

        //Cardview Id
        card_PreKids= v.findViewById(R.id.card_PreKids);
        k_12_Grade= v.findViewById(R.id.k_12_Grade);


        card_PreKids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Kids", Toast.LENGTH_SHORT).show();
            }
        });


        k_12_Grade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "K-12 Grade", Toast.LENGTH_SHORT).show();
            }
        });



        return v;
    }
}