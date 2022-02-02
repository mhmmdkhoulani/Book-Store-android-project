package com.example.bookstore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookCardRecViewAdapter extends RecyclerView.Adapter<BookCardRecViewAdapter.ViewHolder> {


    private ArrayList<Book> books = new ArrayList<>();

    private Context context;

    public BookCardRecViewAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_row_card, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nameBookCard.setText(books.get(position).getName());
        holder.authorBookCard.setText(books.get(position).getAuthor());

        Glide.with(context)
                .asBitmap()
                .load(books.get(position).getImgUrl())
                .into(holder.imageBookCard);

        holder.cardViewBookCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BookDetails.class);
                intent.putExtra("name", books.get(position).getName());
                intent.putExtra("author", books.get(position).getAuthor());
                intent.putExtra("imgUrl", books.get(position).getImgUrl());
                intent.putExtra("description", books.get(position).getDescription());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
       return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageBookCard;
        private TextView nameBookCard, authorBookCard;
        private CardView cardViewBookCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageBookCard = itemView.findViewById(R.id.imageBookCard);
            nameBookCard = itemView.findViewById(R.id.nameBookCard);
            authorBookCard = itemView.findViewById(R.id.authorBookCard);
            cardViewBookCard = itemView.findViewById(R.id.cardViewBookCard);
        }
    }
}
