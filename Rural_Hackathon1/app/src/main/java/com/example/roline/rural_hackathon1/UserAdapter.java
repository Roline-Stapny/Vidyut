package com.example.roline.rural_hackathon1;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Roline on 21-02-2018.
 */

public class UserAdapter extends ArrayAdapter<User> {




    public UserAdapter(Context context, int resource, List<User> objects) {

        super(context, resource, objects);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {

            LayoutInflater li = LayoutInflater.from(getContext());
            v = li.inflate(R.layout.user, null);


        }

        User u = (User) getItem(position);

        if (u != null) {
            TextView region = (TextView) v.findViewById(R.id.textView3);
            TextView total = (TextView) v.findViewById(R.id.textView4);
            final Button notify=(Button) v.findViewById(R.id.button3);
            notify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // 1. Instantiate an AlertDialog.Builder with its constructor
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    // 2. Chain together various setter methods to set the dialog characteristics
                    builder.setMessage("do you want to notify the complained users ");

                    builder.setTitle("Confirm Submition");



                    builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(getContext(),"sending notifications",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            notify.setText("notified");
                            notify.setEnabled(false);

                        }
                    });
                    builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(getContext(),"cancelled",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });
                    // 3. Get the AlertDialog from create(
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
            });


            region.setText(u.getRegion());

            total.setText(Integer.toString(u.getTotal()));

        }


        return v;
    }
}


