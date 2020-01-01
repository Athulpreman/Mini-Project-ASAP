package com.example.miniprojectbook.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class NotificationsFragment extends Fragment {


    EditText ecode,etitle,edesc,eauth,epubli,etype,eprice;
    String code,title,desc,auth,publi,type,price,titlee;
    Button bu,b2,b3;

    DatabaseReference databaseReference;

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                ecode=(EditText)root.findViewById(R.id.UpdateBookCode);
                edesc=(EditText)root.findViewById(R.id.UpdateBookDescription);
                eauth=(EditText)root.findViewById(R.id.UpdateBookAuthor);
                epubli=(EditText)root.findViewById(R.id.UpdateBookPublisher);
                etype=(EditText)root.findViewById(R.id.UpdateBookType);
                eprice=(EditText)root.findViewById(R.id.UpdateBookPrice);
                etitle=(EditText)root.findViewById(R.id.UpdateBookTitle);
                bu=(Button)root.findViewById(R.id.UpdateSearchBookButton);
                b2=(Button)root.findViewById(R.id.UpdateBookButton);
                b3=(Button)root.findViewById(R.id.DeleteBookButton);

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

                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }
                });
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        code=ecode.getText().toString();
                        desc=edesc.getText().toString();
                        price=eprice.getText().toString();
                        auth=eauth.getText().toString();
                        price=eprice.getText().toString();
                        publi=epubli.getText().toString();
                        type=etype.getText().toString();

                        Query query=databaseReference.orderByChild("title").equalTo(titlee);
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                            {
                                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                                {
                                    dataSnapshot1.getRef().child("author").setValue(auth);
                                    dataSnapshot1.getRef().child("code").setValue(code);
                                    dataSnapshot1.getRef().child("description").setValue(desc);
                                    dataSnapshot1.getRef().child("price").setValue(price);
                                    dataSnapshot1.getRef().child("publisher").setValue(publi);
                                    dataSnapshot1.getRef().child("type").setValue(type);
                                    Toast.makeText(getContext(),"Password Changed Sucessfully",Toast.LENGTH_LONG).show();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                Toast.makeText(getContext(),"Error..!",Toast.LENGTH_LONG).show();

                            }
                        });

                    }
                });
                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        titlee=etitle.getText().toString();
                        Query query=databaseReference.orderByChild("title").equalTo(titlee);
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot deletesnapshot:dataSnapshot.getChildren())
                                {
                                    deletesnapshot.getRef().removeValue();
                                    Toast.makeText(getActivity(),"Deleted",Toast.LENGTH_LONG).show();
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