package com.android.cudgel.admin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CLUBFragment extends Fragment {

    EditText etUserid,etPassword;
    TextView btnStart;
    public CLUBFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convert = inflater.inflate(R.layout.fragment_club, container, false);

        etUserid = (EditText)convert.findViewById(R.id.etUserid);
        etPassword = (EditText)convert.findViewById(R.id.etPassword);
        btnStart = (TextView)convert.findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etUserid.getText().toString().equalsIgnoreCase("ncc") && etPassword.getText().toString().equalsIgnoreCase("ncc")){

                    FragmentManager manager11 = getActivity().getSupportFragmentManager();
                    FragmentTransaction ft11 = manager11.beginTransaction();
                    ft11.replace(R.id.main_container, new CLUBFragment2());
                    ft11.addToBackStack("");
                    ft11.commit();


                }else{
                    Toast.makeText(getActivity(), "Wrong User id or Password", Toast.LENGTH_LONG).show();
                }



            }
        });




        return convert;
    }


}
