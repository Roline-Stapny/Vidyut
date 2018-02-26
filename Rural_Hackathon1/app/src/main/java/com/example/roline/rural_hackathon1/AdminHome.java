package com.example.roline.rural_hackathon1;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdminHome extends AppCompatActivity {


    List<User> userList;
    String httpUrl = "http://192.168.1.101:8080/my%20php%20programRR/include/analysis.php";
    ProgressDialog progressDialog;
    RequestQueue requestQueue;
    Button notify;
   Spinner region;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
         region= (Spinner) findViewById(R.id.spinner);

          notify= (Button) findViewById(R.id.notify1);
            //retriieve data from my sql
            final ListView li= (ListView) findViewById(R.id.listview);

            userList=new ArrayList<>();

           //tosend message regarding reason of power cut
           notify.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Toast.makeText(AdminHome.this,"Sending the reason ",Toast.LENGTH_SHORT).show();
               }
           });


        //for region
        String[] regions = new String[]{"Shirva", "Belman", "Nitte"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, regions);
//set the spinners adapter to the previously created one.
        region.setAdapter(adapter1);

        region.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


            StringRequest stringRequest= new StringRequest(Request.Method.GET, httpUrl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONArray jsonArray = new JSONArray(response);

                                for(int i=0; i<jsonArray.length(); i++)
                                {

                                    JSONObject user=jsonArray.getJSONObject(i);

                                    userList.add(new User(
                                            user.getString("region"),
                                            user.getInt("total")));
                                }


                                UserAdapter adapter=new UserAdapter(AdminHome.this,R.layout.user,userList);


                                li.setAdapter(adapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    },


                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(AdminHome.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }

            );



            requestQueue = Volley.newRequestQueue(AdminHome.this);
            requestQueue.add(stringRequest);
    }
}

