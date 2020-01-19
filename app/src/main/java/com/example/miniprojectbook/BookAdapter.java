package com.example.miniprojectbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojectbook.Book;
import com.example.miniprojectbook.R;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder>
{
    private ValueEventListener mCtx;
    private ArrayList<Book> books;
    Context context;

    BookAdapter(Context context, ArrayList<Book> itemList)
    {
        this.context = context;
        books = itemList;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater=LayoutInflater.from((Context) context);
        View view=layoutInflater.inflate(R.layout.book_cardview,null);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position)
    {
        holder.t1.setText(books.get(position).getCode());
        holder.t2.setText(books.get(position).getTitle());
        holder.t3.setText(books.get(position).getDescription());
        holder.t4.setText(books.get(position).getAuthor());
        holder.t5.setText(books.get(position).getType());
        holder.t6.setText(books.get(position).getPublisher());
        holder.t7.setText(books.get(position).getPrice());
        Picasso.with(context).load(books.get(position).getImg()).into(holder.imageView);
    }

    @Override
    public int getItemCount()
    {
        return books.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder
    {
        TextView t1,t2,t3,t4,t5,t6,t7,t8;
        ImageView imageView;

        public BookViewHolder(@NonNull View bookView) {
            super(bookView);
            t1=(TextView) bookView.findViewById(R.id.bcode);
            t2=(TextView)bookView.findViewById(R.id.btitle);
            t3=(TextView)bookView.findViewById(R.id.bdesc);
            t4=(TextView)bookView.findViewById(R.id.bauth);
            t5=(TextView)bookView.findViewById(R.id.btype);
            t6=(TextView)bookView.findViewById(R.id.bpubli);
            t7=(TextView)bookView.findViewById(R.id.bprice);
            imageView=(ImageView) bookView.findViewById(R.id.imagesow);
        }
    }
}
