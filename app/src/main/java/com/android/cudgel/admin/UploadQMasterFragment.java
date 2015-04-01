package com.android.cudgel.admin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import base.MyDrawerActivity;


public class UploadQMasterFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText etTid,etPassword,etTotQ,etTotTime;
    private Button btnSave;


    // TODO: Rename and change types and number of parameters
    public static UploadQMasterFragment newInstance(String param1, String param2) {
        UploadQMasterFragment fragment = new UploadQMasterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public UploadQMasterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertview =inflater.inflate(R.layout.fragment_upload_q_master, container, false);

        etTid = (EditText)convertview.findViewById(R.id.etTid);
        etPassword= (EditText)convertview.findViewById(R.id.etPassword);
        etTotQ= (EditText)convertview.findViewById(R.id.etTotQ);
        etTotTime= (EditText)convertview.findViewById(R.id.etTotTime);
        btnSave= (Button)convertview.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processValidate();
            }
        });


        return  convertview;
    }

private void  processValidate(){
    if(etTid.getText().toString().trim().length()==0){
        etTid.setError("Please enter Test Id");
    }
    else if(etPassword.getText().toString().trim().length()==0){
        etPassword.setError("Please enter Test Password");
    }
    else if(etTotQ.getText().toString().trim().length()==0){
        etTotQ.setError("Please enter Total Questions");
    }
    else if(etTotTime.getText().toString().trim().length()==0){
        etTotTime.setError("Please enter Total time for test");
    }
    else{
        processCheckTestID(etTid.getText().toString().trim());
    }
}


private void processCheckTestID(String testid){
    ParseQuery<ParseObject> query = ParseQuery.getQuery("Question_master");
     query.whereEqualTo("Test_id", testid.toUpperCase());

    query.findInBackground(new FindCallback<ParseObject>() {
        @Override
        public void done(List<ParseObject> parseObjects, ParseException e) {
            if(e==null){
                // check for database

                if(parseObjects.size()!=0){
                    Toast.makeText(getActivity(), "Testid already exists !!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    processSaveDatatoServer();

                }


            }
            else{
                Toast.makeText(getActivity(), "Error to fetch details !!!", Toast.LENGTH_SHORT).show();

            }

        }
    });
}

 private void processSaveDatatoServer(){
     try{
         ParseObject gameScore = new ParseObject("Question_master");

         gameScore.put("Test_id", etTid.getText().toString().trim().toUpperCase());
         gameScore.put("Password", etPassword.getText().toString().trim());
         gameScore.put("Tot_Question", etTotQ.getText().toString().trim());
         gameScore.put("Tot_Time", etTotTime.getText().toString().trim());


         gameScore.saveInBackground();

         Toast.makeText(getActivity(),"Record saved sucessfully",Toast.LENGTH_LONG).show();
         Intent i = new Intent(getActivity(), MyDrawerActivity.class);
         startActivity(i);
         getActivity().finish();
     }catch(Exception e){

         Toast.makeText(getActivity(),"Error Occur",Toast.LENGTH_LONG).show();
     }
}

//end of main class
}
