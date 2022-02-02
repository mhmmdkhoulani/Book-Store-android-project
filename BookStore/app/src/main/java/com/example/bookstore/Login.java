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

public class Login extends AppCompatActivity {

    private Button loginButton;
    private EditText studentId, studentPassword;
    private TextView singUp;
    private String stdId, stdPassword;
    private String ApiUrl = "https://android21930780.000webhostapp.com/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.loginButton);
        studentId = findViewById(R.id.studentId);
        studentPassword = findViewById(R.id.studentpassword);
        singUp = findViewById(R.id.singUp);
        stdId = stdPassword = "";

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stdId = studentId.getText().toString();
                String stdPassword= studentPassword.getText().toString().trim();

                if(!stdId.equals("") && !stdPassword.equals("")){
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiUrl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if (response.equals("success")) {
                                Intent home = new Intent(Login.this, Home.class);
                                startActivity(home);
                                finish();
                            } else  {
                                Toast.makeText(Login.this, "Invalid Login Id/Password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Login.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> data = new HashMap<>();
                            data.put("username", stdId);
                            data.put("password", stdPassword);
                            return data;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                    Intent home = new Intent(Login.this, Home.class);
                    startActivity(home);
                    finish();

                }
                else{
                    Toast.makeText(Login.this, "Fields can not be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup = new Intent(Login.this, SignUp.class);
                startActivity(signup);
            }
        });
    }
}