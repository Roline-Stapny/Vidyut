package com.example.roline.rural_hackathon1;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class    HomeActivity extends AppCompatActivity {


    Button No;
    TextView metre_no;
    Spinner region;
    String httpUrl = "http://192.168.1.101:8080/my%20php%20programRR/include/complaint.php";
    ProgressDialog progressDialog;
    RequestQueue requestQueue;

    String Vregion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


       No= (Button) findViewById(R.id.No);
        metre_no= (TextView) findViewById(R.id.metre_no);
         region= (Spinner) findViewById(R.id.region);

        final String username=getIntent().getStringExtra("metre_no");
        final String password=getIntent().getStringExtra("password");


        metre_no.setText(username);
        requestQueue = Volley.newRequestQueue(HomeActivity.this);
        progressDialog = new ProgressDialog(HomeActivity.this);



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
                                                 switch (position) {
                                                     case 0:
                                                         Vregion = "Shirva";
                                                         break;
                                                     case 1:
                                                         Vregion = "Belman";
                                                         break;
                                                     case 2:
                                                         Vregion = "Nitte";
                                                         break;

                                                 }
                                             }

                                             @Override
                                             public void onNothingSelected(AdapterView<?> parent) {

                                             }
                                         });


            //on Clicking no button store the data into the database
        No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.setMessage("Please Wait, Submittig your response ");
                progressDialog.show();

                // Creating string request with post method.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, httpUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {

                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();

                                // Matching server responce message to our text.

                                //trim because trailing space is present at the begining
                                if(ServerResponse.trim().equalsIgnoreCase("Done")) {

                                    // If response matched then show the toast.
                                    Toast.makeText(HomeActivity.this, "Succesfully submitted", Toast.LENGTH_LONG).show();




                                }
                                else {

                                    // Showing Echo Response Message Coming From Server.
                                    Toast.makeText(HomeActivity.this, ServerResponse, Toast.LENGTH_LONG).show();

                                }


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();

                                // Showing error message if something goes wrong.
                                Toast.makeText(HomeActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {

                        // Creating Map String Params.
                        Map<String, String> params = new HashMap<String, String>();

                        // Adding All values to Params.
                        // The firs argument should be same sa your MySQL database table columns.
                        params.put("metre_no", username);
                        params.put("region", Vregion);

                        return params;
                    }

                };
                // Creating RequestQueue.
                RequestQueue requestQueue = Volley.newRequestQueue(HomeActivity.this);

                // Adding the StringRequest object into requestQueue.
                requestQueue.add(stringRequest);

            }



        });
    }
}
