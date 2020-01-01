package com.example.miniprojectbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentLoggedIn extends AppCompatActivity {

    Button b1,b2,b3;
    String name,admno,place,dist,parent,mobile,email,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_logged_in);

        b1=(Button)findViewById(R.id.StudentSearchBook);
        b2=(Button)findViewById(R.id.StudentViewAllBook);
        b3=(Button)findViewById(R.id.StudentViewProfile);

        Intent in=getIntent();
        name=in.getStringExtra("name");
        admno=in.getStringExtra("adno");
        place=in.getStringExtra("place");
        dist=in.getStringExtra("district");
        parent=in.getStringExtra("parent");
        mobile=in.getStringExtra("mobile");
        email=in.getStringExtra("email");
        pass=in.getStringExtra("pass");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ee=new Intent(getApplicationContext(),SearchBook.class);
                startActivity(ee);

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(getApplicationContext(),ViewAllBook.class);
                ii.putExtra("email",email);
                startActivity(ii);

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent inte=new Intent(getApplicationContext(),ViewProfile.class);

                inte.putExtra("name",name);
                inte.putExtra("adno",admno);
                inte.putExtra("place",place);
                inte.putExtra("dist",dist);
                inte.putExtra("parent",parent);
                inte.putExtra("mobile",mobile);
                inte.putExtra("email",email);
                inte.putExtra("pass",pass);
                startActivity(inte);

            }
        });
    }
}
