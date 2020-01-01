package com.example.miniprojectbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class StudentLogin extends AppCompatActivity {

    EditText t1,t2;
    TextView textView1,textView2;
    Button button;
    String email1,password1;
    DatabaseReference references;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        t1=(EditText) findViewById(R.id.StudentUsername);
        t2=(EditText) findViewById(R.id.StudentPassword);
        textView1=(TextView) findViewById(R.id.StudentRegistration);
        textView2=(TextView) findViewById(R.id.StudentToAdmin);
        button=(Button)findViewById(R.id.studentLogin1);


        student=new Student();
        references= FirebaseDatabase.getInstance().getReference().child("Student");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                email1=t1.getText().toString();
                password1=t2.getText().toString();
                if (email1.isEmpty())
                {
                    t1.setError("Enter Mail id");
                    t1.requestFocus();

                }
                else if (password1.isEmpty())
                {
                    t2.setError("Enter Password");
                    t2.requestFocus();

                }

                else
                {
                    Query query=references.orderByChild("email").equalTo(email1);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren())
                            {
                                student=snapshot.getValue(Student.class);
                                String pass=student.password;
                                if (pass.equals(password1))
                                {
                                    Toast.makeText(getApplicationContext(),"Login Sucessfull",Toast.LENGTH_LONG).show();
                                    Intent inten=new Intent(getApplicationContext(),StudentLoggedIn.class);
                                    inten.putExtra("name",student.name);
                                    inten.putExtra("adno",student.admissionNo);
                                    inten.putExtra("place",student.place);
                                    inten.putExtra("district",student.district);
                                    inten.putExtra("parent",student.parentName);
                                    inten.putExtra("mobile",student.mobile);
                                    inten.putExtra("email",student.email);
                                    inten.putExtra("pass",student.password);
                                    startActivity(inten);
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Incorrect Password",Toast.LENGTH_LONG).show();

                                }

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError)
                        {
                            Toast.makeText(getApplicationContext(),"error...!",Toast.LENGTH_LONG).show();
                        }
                    });
                }



            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten=new Intent(getApplicationContext(),StudentRegistration.class);
                startActivity(inten);
            }
        });
    }
}
