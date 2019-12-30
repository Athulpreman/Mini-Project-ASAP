package com.example.miniprojectbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentRegistration extends AppCompatActivity {

    EditText ename,eadm,eplace,epara,emob,eemail,epass,erepass;
    Spinner edist;
    String sname,sadm,splace,sdist,spara,smob,semail,spass,srepass;
    Button but;
    Student student;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);
        ename=(EditText)findViewById(R.id.StudentName);
        eadm=(EditText)findViewById(R.id.StudentAdmNo);
        eplace=(EditText)findViewById(R.id.StudentPlace);
        edist=(Spinner)findViewById(R.id.StudentDistrict);
        epara=(EditText)findViewById(R.id.StudentParent);
        emob=(EditText)findViewById(R.id.StudentMobileNo);
        eemail=(EditText)findViewById(R.id.StudentEmailId);
        epass=(EditText)findViewById(R.id.StudentPass);
        erepass=(EditText)findViewById(R.id.StudentConfirmPass);
        but=(Button)findViewById(R.id.AddBookSubmit);

        student=new Student();
        ref= FirebaseDatabase.getInstance().getReference().child("Student");

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sname=ename.getText().toString();
                sdist=edist.getSelectedItem().toString();
                sadm=eadm.getText().toString();
                splace=eplace.getText().toString();
                spara=epara.getText().toString();
                smob=emob.getText().toString();
                semail=eemail.getText().toString();
                spass=epass.getText().toString();
                srepass=erepass.getText().toString();

                if (sname.isEmpty())
                {
                    ename.setError("Name required");
                    ename.requestFocus();
                }
                else if (sadm.isEmpty())
                {
                    eadm.setError("Admission no. required");
                    eadm.requestFocus();
                }
                else if (splace.isEmpty())
                {
                    eplace.setError("Place required");
                    eplace.requestFocus();
                }

                else if (spara.isEmpty())
                {
                    epara.setError("Name of parent required");
                    epara.requestFocus();
                }else if (smob.isEmpty())
                {
                    emob.setError("Mob no. required");
                    emob.requestFocus();
                }else if (semail.isEmpty())
                {
                    eemail.setError("Email required");
                    eemail.requestFocus();
                }else if (spass.isEmpty())
                {
                    epass.setError("A password required");
                    epass.requestFocus();
                }
                else if (!srepass.equals(spass))
                {
                    erepass.setError("Password doent match");
                    erepass.requestFocus();
                }
                else
                {

                    student.setName(sname);
                    student.setAdmissionNo(sadm);
                    student.setPlace(splace);
                    student.setDistrict(sdist);
                    student.setParentName(spara);
                    student.setMobile(smob);
                    student.setEmail(semail);
                    student.setPassword(spass);

                    ref.push().setValue(student).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(),"registered succesfully",Toast.LENGTH_LONG).show();
                                ename.setText("");
                                eadm.setText("");
                                eplace.setText("");
                                epara.setText("");
                                emob.setText("");
                                eemail.setText("");
                                epass.setText("");
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Error....!Registration failed..",Toast.LENGTH_LONG).show();


                            }
                        }
                    });



                }



            }
        });
    }
}
