package com.example.miniprojectbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miniprojectbook.ui.home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    EditText e1,e2;
    Button b1;
    TextView t1;
    String AdminUsername,AdminPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1=(EditText)findViewById(R.id.Username);
        e2=(EditText)findViewById(R.id.Password);
        b1=(Button)findViewById(R.id.adminLogin);
        t1=(TextView)findViewById(R.id.adminStudentRegistration);

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent in=new Intent(getApplicationContext(),StudentLogin.class);
                startActivity(in);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                AdminUsername=e1.getText().toString();
                AdminPassword=e2.getText().toString();


                if (AdminUsername.isEmpty())
                {
                    e1.setError("Username required");
                    e1.requestFocus();
                }
                else if (AdminPassword.isEmpty())
                {
                    e2.setError("Password required");
                    e2.requestFocus();
                }
                else
                {
                    if (AdminUsername.equals("admin")&&AdminPassword.equals("1234"))
                    {
                        Toast.makeText(getApplicationContext(),"Succesfully Loged in",Toast.LENGTH_LONG).show();
                        e1.setText("");
                        e2.setText("");
                        Intent intent=new Intent(getApplicationContext(), AdminLoggedIn.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Incorrect username or password",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }
}
