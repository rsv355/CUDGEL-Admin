package com.android.cudgel.admin;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class TestUSerFragment extends Fragment {
    Dialog dialog;
    public static CustomAdapter adapter;
    ListView list;
    EditText etUserid,etPassword;
    TextView btnStart;
    public static ArrayList<String> users = new ArrayList<String>();
    public TestUSerFragment() {
        // Required empty public constructor
    }


    private void fetch(){
        dialog = ProgressDialog.show(getActivity(), "Please Wait", "Fetching data from server..", true);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("TEST_USER");
        //query.whereEqualTo("IsActive", true);
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {

                      dialog.dismiss();

                if (e == null) {
                    Log.e("size of list", String.valueOf(parseObjects.size()));

                    for(int i=0;i<parseObjects.size();i++) {
                       /* user newobj = new user();
                        newobj.testid=parseObjects.get(i).get("testid").toString();
                        qm.add(newobj);*/
                        users.add(parseObjects.get(i).get("testid").toString());
                    }



                    adapter = new CustomAdapter(getActivity(), users,parseObjects);
                    list.setAdapter(adapter);
                    adapter.notifyDataSetChanged();


                } else {
                    Log.e("size of exception", e.getMessage());
                    Toast.makeText(getActivity(), "Error While downloading the data !!!", Toast.LENGTH_LONG).show();
                }





            }


        });





    }







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convert = inflater.inflate(R.layout.fragment_achev, container, false);
        list = (ListView)convert.findViewById(R.id.list);
        fetch();





        return convert;
    }


    class CustomAdapter extends BaseAdapter {
        ArrayList<String> list;
        List<ParseObject> result;
        Context context;

        public CustomAdapter(Context ctx, ArrayList<String> prgmNameList, List<ParseObject> parseobj) {
            // TODO Auto-generated constructor stub
            result = parseobj;
            list = new ArrayList<String>(new LinkedHashSet<String>(prgmNameList));
            this.context = ctx;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.item_list_test_user, null);
            }


            TextView txttestid = (TextView) convertView.findViewById(R.id.txttestid);
            TextView txtusers = (TextView) convertView.findViewById(R.id.txtusers);

            int counter=0;

            for(int i=0;i<result.size();i++) {
                if (list.get(position).equalsIgnoreCase(result.get(i).getString("testid"))) {
                    counter+=1;
                }
            }

            txttestid.setText(list.get(position));
            txtusers.setText("Number of Test Users :- "+String.valueOf(counter));

            return convertView;
        }

    }
















    class user{
        public String testid;
    }
}
