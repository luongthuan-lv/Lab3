package com.luongthuan.lab3.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.luongthuan.lab3.AsyncTask.GetLoader;
import com.luongthuan.lab3.R;

public class MainActivity extends AppCompatActivity {
EditText edtSearch;
Button btnSearch;
RecyclerView rvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtSearch=findViewById(R.id.edtSearch);
        btnSearch=findViewById(R.id.btnSearch);
        rvContent=findViewById(R.id.rvContent);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search=edtSearch.getText().toString().trim();
                GetLoader getLoader=new GetLoader(rvContent,MainActivity.this);
                getLoader.execute(search);
            }
        });
    }


}