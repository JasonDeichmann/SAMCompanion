package com.example.kayletiu.samcompanion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ReplyPost extends AppCompatActivity {

    private TextView tv_author, tv_title, tv_content, tv_datePosted;
    private Button replyBtn;
    private EditText et_content;
    private int id;

    private String filename;
    private List<Reply> tempList = new ArrayList<>();

    private List<Post> repliesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ReplyAdapter mAdapter;
    private Home.OnFragmentInteractionListener mListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_post);

        Bundle b1 = getIntent().getExtras();
        if(b1 != null){
            String author = b1.getString("author");
            String title =  b1.getString("title");
            String content =  b1.getString("content");
            String datePosted = b1.getString("datePosted");
            id = b1.getInt("id");
            filename = "replies"+id+".txt";

            tv_title = findViewById(R.id.reply_title);

            tv_title.setText(title);


            et_content = findViewById(R.id.reply_reply);

            replyBtn = findViewById(R.id.reply_button);
            replyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!et_content.getText().toString().equals("")){

                        tempList.remove(0);
                        write();
                        finish();
                    }
                }
            });
            tempList.add(new Reply(id,content));
            retrieve();

            recyclerView = (RecyclerView) findViewById(R.id.recyclerview_reply);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            mAdapter = new ReplyAdapter(tempList);
            recyclerView.setAdapter(mAdapter);
            recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));

        }
    }
    public void retrieve(){
        FileInputStream fis;
        InputStreamReader isr;
        BufferedReader br;
        try{

            fis = openFileInput(filename);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            int tempId = Integer.parseInt(br.readLine());
            String tempContent = br.readLine();
            while(tempContent != null){
                tempList.add(new Reply(tempId,tempContent));
                tempId = Integer.parseInt(br.readLine());
                tempContent = br.readLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void write(){

        tempList.add(new Reply(id + 1,et_content.getText().toString()));


        File file = null;
        FileOutputStream outputStream = null;

        try{
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            file = getFilesDir();
            for(int i = 0; i < tempList.size(); i++){
                outputStream.write((tempList.get(i).getPostID() + "\n").getBytes());
                outputStream.write((tempList.get(i).getContent() + "\n").getBytes());

            }



            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        Log.d("TRYTRYTRY", tempList.size()+"");
    }

}
