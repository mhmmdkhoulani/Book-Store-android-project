package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class BookAdd extends AppCompatActivity {
    private EditText bookNameEditText, bookAuthorEditText,bookImageUrlEditText, bookDescriptionEditText;
    private Button addingBookBtn;

    private String name, author, imageUrl, description;

    private String ApiUrl = "https://android21930780.000webhostapp.com/addbook.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_add);

        bookNameEditText = findViewById(R.id.bookNameEditText);
        bookAuthorEditText = findViewById(R.id.bookAuthorEditText);
        bookImageUrlEditText = findViewById(R.id.bookImageUrlEditText);
        bookDescriptionEditText = findViewById(R.id.bookDescriptionEditText);
        addingBookBtn = findViewById(R.id.addingBookBtn);

        addingBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = bookNameEditText.getText().toString();
                author = bookAuthorEditText.getText().toString();
                imageUrl = bookImageUrlEditText.getText().toString();
                description = bookDescriptionEditText.getText().toString();

                if (!name.equals("") && !author.equals("") && !imageUrl.equals("") && !description.equals("")) {

                    StringRequest req = new StringRequest(Request.Method.POST, ApiUrl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("success")) {
                                Toast.makeText(BookAdd.this, "Seccessfully Added", Toast.LENGTH_SHORT).show();
                                Intent home = new Intent(BookAdd.this, Home.class);
                                startActivity(home);
                                finish();
                            } else if (response.equals("failure")) {
                                Toast.makeText(BookAdd.this, "Invalid Add this book", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(BookAdd.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> book = new HashMap<>();
                            book.put("name", name);
                            book.put("author", author);
                            book.put("imageUrl", imageUrl);
                            book.put("description", description);

                            return book;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(req);
                }
                else {
                    Toast.makeText(BookAdd.this, "Fields can not be empty!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}