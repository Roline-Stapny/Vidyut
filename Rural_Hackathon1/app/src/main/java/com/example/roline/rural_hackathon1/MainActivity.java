package com.example.roline.rural_hackathon1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText name, aadhar, metre, mobile, address, password;

    Spinner region,district,state;

     String Vname;
     String Vaadhar;
     String Vmetre;
    String Vregion;
    String Vstate;
     String Vmobile ;
     String Vpassword;
     String Vaddress ;
     String Vdistrict ;
    String httpUrl = "http://192.168.1.101:8080/my%20php%20programRR/include/Insert_data.php";
   ProgressDialog progressDialog;
    RequestQueue requestQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Boolean notFirst = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("notFirst", false);

        if (notFirst) {

            //finish();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
           // Toast.makeText(MainActivity.this, " Not First Run", Toast.LENGTH_LONG)
                  //  .show();
        }






        name = (EditText) findViewById(R.id.Name);
        aadhar = (EditText) findViewById(R.id.aadhar);
        metre = (EditText) findViewById(R.id.metre);
        region = (Spinner) findViewById(R.id.spinnerregion);
        state = (Spinner) findViewById(R.id.state);
        mobile = (EditText) findViewById(R.id.mobile);
        password = (EditText) findViewById(R.id.password);
        address = (EditText) findViewById(R.id.address);
        district = (Spinner) findViewById(R.id.district);


        String[] items = new String[]{"shirva", "belman", "nitte"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        region.setAdapter(adapter);

        region.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Vregion="shirva";
                        break;
                    case 1:
                       Vregion="belman";
                        break;
                    case 2:
                       Vregion="nitte";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        //for district
        String[] districts = new String[]{"Udupi", "Uttar kannada", "Dakshina Kannada"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, districts);
//set the spinners adapter to the previously created one.
        district.setAdapter(adapter1);

        district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Vdistrict="Udupi";
                        break;
                    case 1:
                        Vdistrict="Uttar Kannada";
                        break;
                    case 2:
                        Vdistrict="Dakshina Kannada";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //for state

        String[] states = new String[]{"Karnataka", "Tamil Nadu", "Andra Pradesh"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, states);
//set the spinners adapter to the previously created one.
        state.setAdapter(adapter2);

        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Vstate="Karnataka";
                        break;
                    case 1:
                        Vstate="Tamil Nadu";
                        break;
                    case 2:
                        Vstate="Andra Pradesh";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        requestQueue = Volley.newRequestQueue(MainActivity.this);
        progressDialog = new ProgressDialog(MainActivity.this);

    }



    public void submit (View view) {
        Vname = name.getText().toString();
        Vaadhar = aadhar.getText().toString();
        Vmetre = metre.getText().toString();


         Vmobile = mobile.getText().toString();
         Vpassword = password.getText().toString();
         Vaddress = address.getText().toString();


        //new Register(this,name).execute(Vname,Vaadhar,Vmetre,Vregion,Vstate,Vmobile,Vaddress,Vdistrict,Vpassword);

        // Showing progress dialog at user registration time.
        progressDialog.setMessage("Please Wait, We are Inserting Your Data on Server");
        progressDialog.show();





        StringRequest stringRequest = new StringRequest(Request.Method.POST, httpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {

                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();

                        if(ServerResponse.trim().equalsIgnoreCase("successful"))
                        {


                            Log.v("suceess","succesful");
                            // Showing response message coming from server.
                            Toast.makeText(MainActivity.this, ServerResponse, Toast.LENGTH_LONG).show();

                            //indicate registration is done dont show the registration page again
                            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                                    .putBoolean("notFirst", true).apply();

                            finish();

                            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                            startActivity(intent);

                        }
                        else

                        {
                            Log.v("suceess"," not succesful");
                            Toast.makeText(MainActivity.this, ServerResponse, Toast.LENGTH_LONG).show();
                        }




                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();

                        // Showing error message if something goes wrong.
                        Toast.makeText(MainActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                params.put("name", Vname);
                params.put("password", Vpassword);
                params.put("district", Vdistrict);
                params.put("state", Vstate);
                params.put("address", Vaddress);
                params.put("region", Vregion);
                params.put("mobile", Vmobile);
                params.put("aadhar", Vaadhar);
                params.put("metre", Vmetre);

                return params;
            }
        };


        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}

