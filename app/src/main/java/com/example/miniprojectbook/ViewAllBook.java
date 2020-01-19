package com.example.miniprojectbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ViewAllBook extends AppCompatActivity {

    DatabaseReference refee;
    RecyclerView recyclerView;
    BookAdapter adapter;
    ArrayList<Book> list;

    List<Book> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_book);

        recyclerView=(RecyclerView)findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<Book>();

        refee= FirebaseDatabase.getInstance().getReference().child("Book1");
        refee.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for (DataSnapshot studentDatasnapshot : dataSnapshot.getChildren())
                {
                    Book book = studentDatasnapshot.getValue(Book.class);
                    list.add(book);
                }
                adapter = new BookAdapter(ViewAllBook.this,list);
                recyclerView.setAdapter(adapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                Toast.makeText(getApplicationContext(),"something wnt wrong",Toast.LENGTH_LONG).show();
            }
        });

       /* Query query=refee.orderByChild("title");
        query.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for (DataSnapshot studentDatasnapshot : dataSnapshot.getChildren())
                {
                    Book book = studentDatasnapshot.getValue(Book.class);

                    itemList.add(new Book(book.code,book.title,book.description,book.author,book.type,book.publisher,book.price,book.img));

                    adapter = new BookAdapter(this, itemList);
                    recyclerView.setAdapter(adapter);


                    /* Picasso.with(getApplicationContext()).load(ob.img).into(img);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }

        });*/


    }

}
