package com.example.miniprojectbook.ui.home;

import android.content.Intent;
import android.net.Uri;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {

    EditText acode,atitle,adesc,aauth,apubli,atype,aprice;
    ImageView e;
    String scode,stitle,sdesc,sauth,spubli,stype,sprice,Imagepath,EmptyImg;
    Button b,b2;
    Book book;
    DatabaseReference reference;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                acode=(EditText)root.findViewById(R.id.AddBookCode);
                atitle=(EditText)root.findViewById(R.id.AddBookTitle);
                adesc=(EditText)root.findViewById(R.id.AddBookDescription);
                aauth=(EditText)root.findViewById(R.id.AddBookAuthor);
                apubli=(EditText)root.findViewById(R.id.AddBookPublisher);
                atype=(EditText)root.findViewById(R.id.AddBookType);
                aprice=(EditText)root.findViewById(R.id.AddBookPrice);
                e=(ImageView) root.findViewById(R.id.ImageIdPrint);
                b=(Button) root.findViewById(R.id.AddBookSubmit);
                b2=(Button) root.findViewById(R.id.AddBookImageButton);

                book=new Book();
                reference= FirebaseDatabase.getInstance().getReference().child("Book1");

                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        Intent i=new Intent(Intent.ACTION_GET_CONTENT);
                        i.setType("image/*");
                        startActivityForResult(i,1);

                    }
                });
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        scode=acode.getText().toString();
                        stitle=atitle.getText().toString();
                        sdesc=adesc.getText().toString();
                        sauth=aauth.getText().toString();
                        spubli=apubli.getText().toString();
                        stype=atype.getText().toString();
                        sprice=aprice.getText().toString();

                        if (scode.isEmpty())
                        {
                            acode.setError("Code needed");
                            acode.requestFocus();
                        }
                        else if (stitle.isEmpty())
                        {
                            atitle.setError("Title needed");
                            atitle.requestFocus();
                        }
                        else if (sdesc.isEmpty())
                        {
                            adesc.setError("Title needed");
                            adesc.requestFocus();
                        }else if (sauth.isEmpty())
                        {
                            aauth.setError("Title needed");
                            aauth.requestFocus();
                        }else if (spubli.isEmpty())
                        {
                            apubli.setError("Title needed");
                            apubli.requestFocus();
                        }else if (stype.isEmpty())
                        {
                            atype.setError("Title needed");
                            atype.requestFocus();
                        }else if (sprice.isEmpty())
                        {
                            aprice.setError("Title needed");
                            aprice.requestFocus();
                        }

                        else
                        {
                            book.setCode(scode);
                            book.setTitle(stitle);
                            book.setDescription(sdesc);
                            book.setAuthor(sauth);
                            book.setPublisher(spubli);
                            book.setType(stype);
                            book.setPrice(sprice);
                            book.setImg(Imagepath);
                            reference.push().setValue(book).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        acode.setText("");
                                        atitle.setText("");
                                        aprice.setText("");
                                        adesc.setText("");
                                        atype.setText("");
                                        aauth.setText("");
                                        apubli.setText("");
                                        Picasso.with(getContext()).load(EmptyImg).into(e);
                                        Toast.makeText(getActivity(),"Sucessfully Uploaded",Toast.LENGTH_LONG).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(getActivity(),"Error uploading...!",Toast.LENGTH_LONG).show();

                                    }
                                }
                            });

                        }
                    }
                });

            }
        });
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {
            if (resultCode==RESULT_OK)
            {
                Uri fileuri=data.getData();

                StorageReference folder= FirebaseStorage.getInstance().getReference().child("Studentphoto");

                String timestamp=String.valueOf(System.currentTimeMillis());

                final StorageReference filename=folder.child(timestamp+fileuri.getLastPathSegment());

                filename.putFile(fileuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        filename.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                Imagepath=String.valueOf(uri);
                                Picasso.with(getContext()).load(Imagepath).into(e);
                            }
                        });

                    }
                });
            }

        }
    }
}