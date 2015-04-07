package com.android.cudgel.admin;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class ViewUSerFragment extends Fragment {
    Dialog dialog;
    public static CustomAdapter adapter;
   ListView list;
    public ViewUSerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convert = inflater.inflate(R.layout.fragment_view, container, false);
        list = (ListView)convert.findViewById(R.id.list);

        processDowloadandSQLinsert();


        return convert;
    }



    private void processDowloadandSQLinsert(  ){

        dialog= ProgressDialog.show(getActivity(), "Please Wait", "downloading latest data from server...", true);
        dialog.setCancelable(false);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("NCC_USER");
        // query.whereEqualTo("DATABASE_VERSION", "ALL3");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObject, ParseException e) {
                if (e == null) {
                    // check for database
                    // check for database

                    adapter = new CustomAdapter(getActivity(), parseObject);
                    list.setAdapter(adapter);
                    adapter.notifyDataSetChanged();


                    dialog.dismiss();


                } else {
                    Toast.makeText(getActivity(), "Error to fetch details !!!", Toast.LENGTH_SHORT).show();

                }

            }
        });

   }


    class CustomAdapter extends BaseAdapter {
        List<ParseObject> result;
        Context context;

        public CustomAdapter(Context ctx, List<ParseObject> prgmNameList) {
            // TODO Auto-generated constructor stub
            result = prgmNameList;
            this.context = ctx;
        }

        @Override
        public int getCount() {
            return result.size();
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
                convertView = getActivity().getLayoutInflater().inflate(R.layout.item_list_user_search, null);
            }


            TextView txtArea = (TextView) convertView.findViewById(R.id.txtArea);
            TextView txtMob = (TextView) convertView.findViewById(R.id.txtMob);
            TextView txtname = (TextView) convertView.findViewById(R.id.txtname);


            txtname.setText(result.get(position).getString("name").trim());
            txtMob.setText(result.get(position).getString("mob1").trim());

            txtArea.setText(result.get(position).getString("area").trim() + " , " + result.get(position).getString("city").trim() + " , " + result.get(position).getString("state").trim());

            return convertView;
        }

    }


//end of main class
}
