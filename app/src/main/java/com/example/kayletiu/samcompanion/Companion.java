package com.example.kayletiu.samcompanion;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Companion extends AppCompatActivity {

    private List<ChatModel> list = new ArrayList<>();
    List<ChatModel> newData = new ArrayList<ChatModel>();
    private List<ChatModel> chatModelList = new ArrayList<>();
    private ChatAdapter chatAdapter;
    //Firebase
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("messages");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companion);

        //Setup recycler view
        RecyclerView recyclerViewChat = (RecyclerView) findViewById(R.id.recyclerViewChat);
        chatAdapter = new ChatAdapter(chatModelList);
        RecyclerView.LayoutManager rLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewChat.setLayoutManager(rLayoutManager);
        recyclerViewChat.setItemAnimator(new DefaultItemAnimator());
        recyclerViewChat.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerViewChat.setAdapter(chatAdapter);

        //Setup EditText
        final EditText edit_txt = (EditText) findViewById(R.id.editTextChatMessage);

        //Get data from firebase
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<ChatModel>> type = new GenericTypeIndicator<List<ChatModel>>(){};
                newData = dataSnapshot.getValue(type);
                list.clear();
                list.addAll(newData);
                chatModelList.clear();
                chatModelList.addAll(newData);
                chatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("Error", "error");
            }
        };
        mDatabase.addValueEventListener(postListener);



        //Add Listener for EditText
        edit_txt.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                   list.add(new ChatModel(edit_txt.getText().toString()));

                    //Write to database
                    mDatabase.setValue(list);

                    edit_txt.setText("");

                    //Set the list to the recyclerView
                    chatModelList.clear();
                    chatModelList.addAll(list);
                    chatAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });
    }
}
