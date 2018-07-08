package com.example.kayletiu.samcompanion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class AddPost extends AppCompatActivity {

    private int latestId = 1;
    private String author = "doggy";
    private EditText post;
    private EditText title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);


        Button b1 = findViewById(R.id.btn_back);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Button b2 = findViewById(R.id.btn_send);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if(!post.getText().toString().equals("") && !title.getText().toString().equals("")){
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("content",post.getText().toString());
                bundle.putString("title", title.getText().toString());
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }


//
//                write();
//                finish();
            }
        });
        post = findViewById(R.id.text_post);
        title = findViewById(R.id.text_title);
//
//        String [] language = { "JavaScript", "Java", "C"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, language);
//        AutoCompleteTextView acTextView = findViewById(R.id.autoCompleteTextView);
//        acTextView.setThreshold(1);
//        acTextView.setAdapter(adapter);
    }




}
