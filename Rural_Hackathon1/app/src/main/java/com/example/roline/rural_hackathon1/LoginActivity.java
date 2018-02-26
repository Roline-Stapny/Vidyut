package com.example.roline.rural_hackathon1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button login;
    private Button Electricity_map;
    private String usernameHolder,passwordHolder;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;


    //10.0.2.2
    String httpUrl="http://192.168.1.101:8080/my%20php%20programRR/include/login.php";
    boolean CheckEditText;


    @Override
    protected void onStop() {
        super.onRestart();
        username.setText("");
        password.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username= (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        login= (Button) findViewById(R.id.button2);
        Electricity_map= (Button) findViewById(R.id.button4);



        requestQueue= Volley.newRequestQueue(LoginActivity.this);
        progressDialog=new ProgressDialog(LoginActivity.this);



        ImageView myImage = (ImageView) findViewById(R.id.imageView);
        myImage.setImageAlpha(50);


        Electricity_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(LoginActivity.this, com.example.roline.rural_hackathon1.Electricity_Map.class);

                startActivity(i);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckEditTextIsEmptyOrNot();
                if(CheckEditText)
                {
                    usernameHolder = username.getText().toString().trim();
                    passwordHolder = password.getText().toString().trim();
                    if(usernameHolder.equals("admin")   &&  passwordHolder.equals("admin"))
                    {
                        Intent i=new Intent(LoginActivity.this,AdminHome.class);

                        startActivity(i);
                    }
                    else
                    {
                        UserLogin();
                    }



                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Either Username or password is Empty",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    //check if username or password is empty

    public void CheckEditTextIsEmptyOrNot() {

        // Getting values from EditText.
        usernameHolder = username.getText().toString().trim();
        passwordHolder = password.getText().toString().trim();

        // Checking whether EditText value is empty or not.
        if (TextUtils.isEmpty(usernameHolder) || TextUtils.isEmpty(passwordHolder)) {

            // If any of EditText is empty then set variable value as False.
            CheckEditText = false;

        } else {

            // If any of EditText is filled then set variable value as True.
            CheckEditText = true;
        }
    }



    // Creating user login function.
    public void UserLogin() {

        // Showing progress dialog at user registration time.
        progressDialog.setMessage("Please Wait");
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
                        if(ServerResponse.trim().equalsIgnoreCase("Data Matched")) {

                            // If response matched then show the toast.
                            Toast.makeText(LoginActivity.this, "Logged In Successfully", Toast.LENGTH_LONG).show();



                            // Opening the user profile activity using intent.
                           Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            intent.putExtra("metre_no",usernameHolder);
                            intent.putExtra("password",passwordHolder);

                            // Sending User Email to another activity using intent.
                           // intent.putExtra("UserEmailTAG", EmailHolder);

                           startActivity(intent);
                        }
                        else {

                            // Showing Echo Response Message Coming From Server.
                            Toast.makeText(LoginActivity.this, ServerResponse, Toast.LENGTH_LONG).show();

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();

                        // Showing error message if something goes wrong.
                        Toast.makeText(LoginActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                // The firs argument should be same sa your MySQL database table columns.
                params.put("metre_no", usernameHolder);
                params.put("password", passwordHolder);

                return params;
            }

        };
        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);

    }

}
