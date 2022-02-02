package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BookDetails extends AppCompatActivity {

    private TextView bookNameDetails, bookAuthorDetails,bookDescriptionDetails;
    private ImageView imageBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        bookNameDetails = findViewById(R.id.bookNameDetails);
        bookAuthorDetails = findViewById(R.id.bookAuthorDetails);
        imageBook = findViewById(R.id.imageBook);
        bookDescriptionDetails = findViewById(R.id.bookDescriptionDetails);

        String name = getIntent().getStringExtra("name");
        String author = getIntent().getStringExtra("author");
        String imgUl = getIntent().getStringExtra("imgUrl");
        String description = getIntent().getStringExtra("description");

        bookNameDetails.setText( name);
        bookAuthorDetails.setText(author);
        bookDescriptionDetails.setText( description);

        Glide.with(this)
                .asBitmap()
                .load(imgUl)
                .into(imageBook);

    }
}