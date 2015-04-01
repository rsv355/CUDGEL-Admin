package com.android.cudgel.admin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import base.MyDrawerActivity;


public class UploadQDetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private EditText etQid,etQuestion,etoptA,etoptB,etoptC,etoptD,etCorrectopt;
    private Button btnSave;
    Spinner spTest;
    ArrayList<String> Testid;


    // TODO: Rename and change types and number of parameters
    public static UploadQDetailsFragment newInstance(String param1, String param2) {
        UploadQDetailsFragment fragment = new UploadQDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public UploadQDetailsFragment() {
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
    public void onResume() {
        super.onResume();
        Testid = new ArrayList<String>();
        Testid.add(0,"Select");
        processfetchTestid();
    }

    private void processfetchTestid(){

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Question_master");
      //  query.whereEqualTo("Test_id", testid.toUpperCase());

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if(e==null){
                    // check for database
                    for(int i=0;i<parseObjects.size();i++) {
                        Testid.add(i+1,parseObjects.get(i).get("Test_id").toString().trim());
                    }


                    ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,Testid);
                    spTest.setAdapter(arrayAdapter);
                }
                else{
                    Toast.makeText(getActivity(), "Error to fetch details !!!", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertview =inflater.inflate(R.layout.fragment_upload_q_detail, container, false);
        etQid = (EditText)convertview.findViewById(R.id.etQid);
        etQuestion= (EditText)convertview.findViewById(R.id.etQuestion);
        etoptA= (EditText)convertview.findViewById(R.id.etoptA);
        etoptB= (EditText)convertview.findViewById(R.id.etoptB);
        etoptC= (EditText)convertview.findViewById(R.id.etoptC);
        etoptD= (EditText)convertview.findViewById(R.id.etoptD);
        etCorrectopt= (EditText)convertview.findViewById(R.id.etCorrectopt);
        spTest = (Spinner)convertview.findViewById(R.id.spTest);
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
        if(spTest.getSelectedItemPosition()==0){
           Toast.makeText(getActivity(), "Please Select Test ID !!!", Toast.LENGTH_SHORT).show();
        }
        else if(etQid.getText().toString().trim().length()==0){
            etQid.setError("Please enter Question Id");
        }
        else if(etQuestion.getText().toString().trim().length()==0){
            etQuestion.setError("Please enter Question");
        }
        else if(etoptA.getText().toString().trim().length()==0){
            etoptA.setError("Please enter option A");
        }
        else if(etoptB.getText().toString().trim().length()==0){
            etoptB.setError("Please enter option B");
        }
        else if(etoptC.getText().toString().trim().length()==0){
            etoptC.setError("Please enter option C");
        }
        else if(etoptD.getText().toString().trim().length()==0){
            etoptD.setError("Please enter option D");
        }
        else if(etCorrectopt.getText().toString().trim().length()==0){
            etCorrectopt.setError("Please enter correct option");
        }

        else{
            processCheckQuestionID(etQid.getText().toString().trim());
        }
    }

    private void processCheckQuestionID(String qid){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Question_details");
        query.whereEqualTo("Q_id", qid.toUpperCase());

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if(e==null){
                    // check for database

                    if(parseObjects.size()!=0){
                        Toast.makeText(getActivity(), "Question ID already exists !!!", Toast.LENGTH_SHORT).show();
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
            ParseObject gameScore = new ParseObject("Question_details");

            gameScore.put("Test_id", spTest.getSelectedItem().toString());
            gameScore.put("Q_id", etQid.getText().toString().trim().toUpperCase());
            gameScore.put("optA", etoptA.getText().toString().trim().toUpperCase());
            gameScore.put("optB", etoptB.getText().toString().trim().toUpperCase());
            gameScore.put("optC", etoptC.getText().toString().trim().toUpperCase());
            gameScore.put("optD", etoptD.getText().toString().trim().toUpperCase());
            gameScore.put("Correct_opt", etCorrectopt.getText().toString().trim().toUpperCase());
            gameScore.put("Question", etQuestion.getText().toString().trim());


            gameScore.saveInBackground();

            Toast.makeText(getActivity(),"Question saved sucessfully",Toast.LENGTH_LONG).show();
            Intent i = new Intent(getActivity(), MyDrawerActivity.class);
            startActivity(i);
            getActivity().finish();
        }catch(Exception e){

            Toast.makeText(getActivity(),"Error Occur",Toast.LENGTH_LONG).show();
        }
    }



//end of main class
}
