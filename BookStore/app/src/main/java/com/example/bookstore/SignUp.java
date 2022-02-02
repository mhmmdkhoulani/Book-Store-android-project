package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    private TextView loginText;

    private Button registerButton;
    private EditText studentNameR, studentIdR, studentpasswordR;

    private String stdId, stdPassword, stdName;
    private String ApiUrl = "https://android21930780.000webhostapp.com/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        registerButton = findViewById(R.id.registerButton);
        studentNameR = findViewById(R.id.studentNameR);
        studentIdR = findViewById(R.id.studentIdR);
        studentpasswordR = findViewById(R.id.studentpasswordR);
        stdId = stdPassword = stdName= "";


        loginText = findViewById(R.id.loginText);

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stdId = studentIdR.getText().toString().trim();
                String stdPassword= studentpasswordR.getText().toString().trim();
                String stdName= studentNameR.getText().toString();


                if (!stdId.equals("") && !stdPassword.equals("") && !stdName.equals("")) {

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiUrl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if (response.equals("success")) {
                                Toast.makeText(SignUp.this, "Seccessfully registers", Toast.LENGTH_SHORT).show();
                                Intent home = new Intent(SignUp.this, Login.class);
                                startActivity(home);
                                finish();
                            } else if (response.equals("failure")) {
                                Toast.makeText(SignUp.this, "Invalid Register", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(SignUp.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> data = new HashMap<>();
                            data.put("name", stdName);
                            data.put("username", stdId);
                            data.put("password", stdPassword);
                            return data;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);

                }
                else {
                    Toast.makeText(SignUp.this, "Fields can not be empty!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}