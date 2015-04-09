package com.android.cudgel.admin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CLUBFragment2 extends Fragment {


    TextView txtach,txtevent,txtuser,txttestuser;
    public CLUBFragment2() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convert = inflater.inflate(R.layout.fragment_club2, container, false);

        txtach = (TextView)convert.findViewById(R.id.txtach);
        txtevent = (TextView)convert.findViewById(R.id.txtevent);
        txtuser = (TextView)convert.findViewById(R.id.txtuser);
        txttestuser= (TextView)convert.findViewById(R.id.txttestuser);

        txttestuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager11 = getActivity().getSupportFragmentManager();
                FragmentTransaction ft11 = manager11.beginTransaction();
                ft11.replace(R.id.main_container, new TestUSerFragment());
                ft11.addToBackStack("");
                ft11.commit();
            }
        });


        txtach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager11 = getActivity().getSupportFragmentManager();
                FragmentTransaction ft11 = manager11.beginTransaction();
                ft11.replace(R.id.main_container, new AchevFragment());
                ft11.addToBackStack("");
                ft11.commit();
            }
        });
        txtevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager11 = getActivity().getSupportFragmentManager();
                FragmentTransaction ft11 = manager11.beginTransaction();
                ft11.replace(R.id.main_container, new EventFragment());
                ft11.addToBackStack("");
                ft11.commit();
            }
        });

        txtuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager11 = getActivity().getSupportFragmentManager();
                FragmentTransaction ft11 = manager11.beginTransaction();
                ft11.replace(R.id.main_container, new ViewUSerFragment());
                ft11.addToBackStack("");
                ft11.commit();
            }
        });





        return convert;
    }


}
