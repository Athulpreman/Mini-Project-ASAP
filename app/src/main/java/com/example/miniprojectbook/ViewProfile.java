package com.example.miniprojectbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ViewProfile extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9;
    String name,admno,place,dist,parent,mobile,email1,pass;
    Button b1,b2;
    DatabaseReference refe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        e1=(EditText)findViewById(R.id.StudentName);
        e2=(EditText)findViewById(R.id.StudentAdmNo);
        e3=(EditText)findViewById(R.id.StudentPlace);
        e4=(EditText)findViewById(R.id.StudentDistrict);
        e5=(EditText)findViewById(R.id.StudentParent);
        e6=(EditText)findViewById(R.id.StudentMobileNo);
        e7=(EditText)findViewById(R.id.StudentEmailId);
        e8=(EditText)findViewById(R.id.StudentPass);
        e9=(EditText)findViewById(R.id.StudentPasswordChange);

        refe= FirebaseDatabase.getInstance().getReference().child("Student");

        b1=(Button)findViewById(R.id.AddBookSubmit);
        b2=(Button)findViewById(R.id.StudentPasswordResetButton);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {   Intent ina=getIntent();
                name=ina.getStringExtra("name");
                admno=ina.getStringExtra("adno");
                place=ina.getStringExtra("place");
                dist=ina.getStringExtra("dist");
                parent=ina.getStringExtra("parent");
                mobile=ina.getStringExtra("mobile");
                email1=ina.getStringExtra("email");
                pass=ina.getStringExtra("pass");

                e1.setText(name);
                e2.setText(admno);
                e3.setText(place);
                e4.setText(dist);
                e5.setText(parent);
                e6.setText(mobile);
                e7.setText(email1);
                e8.setText(pass);

            }

        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final String s=e9.getText().toString();
                Query query=refe.orderByChild("email").equalTo(email1);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                        {
                            dataSnapshot1.getRef().child("password").setValue(s);

                            Toast.makeText(getApplicationContext(),"Password Changed Sucessfully",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                        Toast.makeText(getApplicationContext(),"Error..!",Toast.LENGTH_LONG).show();

                    }
                });

            }
        });
    }
}
