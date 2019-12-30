package com.example.miniprojectbook.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.miniprojectbook.Book;
import com.example.miniprojectbook.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DashboardFragment extends Fragment {
    ImageView img;
    EditText ecode,etitle,edesc,eauth,epubli,etype,eprice;
    String code,title,desc,auth,publi,type,price,titlee;
    Button bu;

    DatabaseReference databaseReference;

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                ecode=(EditText)root.findViewById(R.id.SearchResultBookCode);
                edesc=(EditText)root.findViewById(R.id.SearchResultBookDescription);
                eauth=(EditText)root.findViewById(R.id.SearchResultBookAuthor);
                epubli=(EditText)root.findViewById(R.id.SearchResultBookPublisher);
                etype=(EditText)root.findViewById(R.id.SearchResultBookType);
                eprice=(EditText)root.findViewById(R.id.SearchResultBookPrice);
                etitle=(EditText)root.findViewById(R.id.SearchBookTitle);
                img=(ImageView)root.findViewById(R.id.showImgSearch);
                bu=(Button)root.findViewById(R.id.SearchBookSearchButton);

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
                                    Picasso.with(getContext()).load(ob.img).into(img);

                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }
                });

            }
        });
        return root;
    }
}