package com.example.miniprojectbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewAllBook extends AppCompatActivity {

    DatabaseReference refee;
    Book book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_book);
        book=new Book();
        refee= FirebaseDatabase.getInstance().getReference().child("Book1");


    }
}
