package com.example.miniprojectbook.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.miniprojectbook.R;

public class HomeFragment extends Fragment {

    EditText acode,atitle,adesc,aauth,apubli,atype,aprice;
    String scode,stitle,sdesc,sauth,spubli,stype,sprice;
    Button b;

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
                b=(Button) root.findViewById(R.id.AddBookSubmit);


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
                            atitle.setError("Title needed");
                            atitle.requestFocus();
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

                        }
                    }
                });

            }
        });
        return root;
    }
}