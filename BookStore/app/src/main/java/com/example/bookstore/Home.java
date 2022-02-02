package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    private RecyclerView recentlyAddedR;
    private RecyclerView popularR;
    private CardView cardViewBookList,cardViewBookCard;
    private FloatingActionButton addingBooks;
    private String featuredBooks = "https://android21930780.000webhostapp.com/getfbooks.php";
    private String allBooks = "https://android21930780.000webhostapp.com/getallbooks.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        recentlyAddedR = findViewById(R.id.recentlyAddedR);
        popularR = findViewById(R.id.popularR);
        cardViewBookList = findViewById(R.id.cardViewBookList);
        cardViewBookCard = findViewById(R.id.cardViewBookCard);
        addingBooks = findViewById(R.id.addingBooks);

        ArrayList<Book> books = new ArrayList<>();

        BookCardRecViewAdapter adapter = new BookCardRecViewAdapter(this);
        adapter.setBooks(books);

        recentlyAddedR.setAdapter(adapter);
        recentlyAddedR.setLayoutManager(new LinearLayoutManager(Home.this, LinearLayoutManager.HORIZONTAL, false));

        RequestQueue queue = Volley.newRequestQueue(Home.this);
        JsonArrayRequest request = new JsonArrayRequest(featuredBooks, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i<  response.length(); i++){
                    try {
                        JSONObject row = response.getJSONObject(i);
                        int id = row.getInt("id");
                        String name = row.getString("name");
                        String author = row.getString("author");
                        String imageUrl = row.getString("imageUrl");
                        String description = row.getString("description");
                        books.add(new Book(name,author,imageUrl, description));
                    } catch (Exception e) {
                        Toast.makeText(Home.this,"Error", Toast.LENGTH_SHORT).show();
                    }
                    adapter.notifyDataSetChanged();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Home.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);



        ArrayList<Book> booksList = new ArrayList<>();



        BookListRecViewAdapter listAdapter = new BookListRecViewAdapter(this);
        listAdapter.setBooks(booksList);

        popularR.setAdapter(listAdapter);
        popularR.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        RequestQueue queueAllBooks = Volley.newRequestQueue(Home.this);
        JsonArrayRequest requestAllBooks = new JsonArrayRequest(allBooks, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i<  response.length(); i++){
                    try {
                        JSONObject row = response.getJSONObject(i);
                        int id = row.getInt("id");
                        String name = row.getString("name");
                        String author = row.getString("author");
                        String imageUrl = row.getString("imageUrl");
                        String description = row.getString("description");
                        booksList.add(new Book(name,author,imageUrl,description));
                    } catch (Exception e) {
                        Toast.makeText(Home.this,"Error", Toast.LENGTH_SHORT).show();
                    }
                    listAdapter.notifyDataSetChanged();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Home.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });
        queueAllBooks.add(requestAllBooks);

        addingBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, BookAdd.class);
                Home.this.startActivity(intent);
            }
        });
    }

}