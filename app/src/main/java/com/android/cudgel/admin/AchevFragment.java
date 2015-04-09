package com.android.cudgel.admin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;

public class AchevFragment extends Fragment {

    EditText eteventname,etdesc,eteventdate;
    TextView btnSave;
    public AchevFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convert = inflater.inflate(R.layout.fragment_achev, container, false);


        eteventname = (EditText)convert. findViewById(R.id.eteventname);
        eteventdate = (EditText)convert. findViewById(R.id.eteventdate);
        etdesc = (EditText)convert. findViewById(R.id.etdesc);


        btnSave = (TextView)convert. findViewById(R.id.btnSave);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processValidate();
            }
        });




        return convert;
    }

    private void processValidate(){
        if(eteventname.getText().toString().trim().length()==0){
            eteventname.setError("Please Enter Event name !!!");
        }
        else if(eteventdate.getText().toString().trim().length()==0 ){
            eteventdate.setError("Please Enter Achieved by !!!");
        }

        else if(etdesc.getText().toString().trim().length()==0){
            etdesc.setError("Please Enter description !!!");
        }

        else{
            processSave();
        }
    }


    private void processSave(){
        try{
            ParseObject gameScore = new ParseObject("ACHEV");

            gameScore.put("Name", eteventname.getText().toString().trim());
            gameScore.put("Achev", eteventdate.getText().toString().trim());
            gameScore.put("Desc", etdesc.getText().toString().trim());

            gameScore.saveInBackground();

            Toast.makeText(getActivity(),"Record saved sucessfully",Toast.LENGTH_LONG).show();

        }catch(Exception e){
            Log.e("exc", e.toString());
            Toast.makeText(getActivity(),"Error Occur",Toast.LENGTH_LONG).show();
        }

    }
}
