package com.example.miniprojectbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class SearchBook extends AppCompatActivity {
    ImageView img;
    EditText ecode,etitle,edesc,eauth,epubli,etype,eprice;
    String code,title,desc,auth,publi,type,price,titlee;
    Button bu;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);

        ecode=(EditText)findViewById(R.id.SearchResultBookCode);
        edesc=(EditText)findViewById(R.id.SearchResultBookDescription);
        eauth=(EditText)findViewById(R.id.SearchResultBookAuthor);
        epubli=(EditText)findViewById(R.id.SearchResultBookPublisher);
        etype=(EditText)findViewById(R.id.SearchResultBookType);
        eprice=(EditText)findViewById(R.id.SearchResultBookPrice);
        etitle=(EditText)findViewById(R.id.SearchBookTitle);
        img=(ImageView)findViewById(R.id.showImgSearch);
        bu=(Button)findViewById(R.id.SearchBookSearchButton);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Book1");

        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                titlee=etitle.getText().toString();
                Query query=databaseReference.orderByChild("title").equalTo(titlee);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot studentDatasnapshot : dataSnapshot.getChildren()) {
                            Book ob = studentDatasnapshot.getValue(Book.class);

                            ecode.setText(ob.code);
                            edesc.setText(ob.description);
                            eprice.setText(ob.price);
                            eauth.setText(ob.author);
                            etype.setText(ob.type);
                            epubli.setText(ob.publisher);
                            Picasso.with(getApplicationContext()).load(ob.img).into(img);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }
}
