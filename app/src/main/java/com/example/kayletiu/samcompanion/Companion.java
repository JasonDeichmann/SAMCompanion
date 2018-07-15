package com.example.kayletiu.samcompanion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
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
    List<Integer> userData = new ArrayList<Integer>();
    private List<ChatModel> chatModelList = new ArrayList<>();
    private ChatAdapter chatAdapter;
    //Firebase
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("messages");
    private DatabaseReference usersDatabase = FirebaseDatabase.getInstance().getReference("users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companion);

        //Navbar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navbar);
        Helper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.id_menu_community:
                                Intent intent1 = new Intent (Companion.this, MainActivity.class);
                                startActivityForResult(intent1, 0);
                                break;
                            case R.id.id_menu_games:
                                Intent intent2 = new Intent(Companion.this, GamesActivity.class);
                                startActivity(intent2);
                                break;
                            case R.id.id_menu_sam:
                                Intent intent3 = new Intent(Companion.this, Companion.class);
                                startActivity(intent3);
                                break;
                            case R.id.id_menu_partners:
                                Intent intent4 = new Intent (Companion.this, PartnersActivity.class);
                                startActivityForResult(intent4, 0);
                                break;
                            case R.id.id_menu_exercise:
                                Intent intent5 = new Intent (Companion.this, ExerciseActivity.class);
                                startActivityForResult(intent5, 0);
                                break;
                        }

                        return true;
                    }
                });


        //Setup EditText
        final EditText edit_txt = (EditText) findViewById(R.id.editTextChatMessage);

        //Setup sharedPreferences
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = sharedPref.edit();
        //editor.putString("userID", "1");
        //editor.clear();
        //editor.apply();


        //Log.i("userID", sharedPref.getInt("userID", 0));
        Log.i("userID-Current", Integer.toString(sharedPref.getInt("userID", 0)));


        //Setup recycler view
        final RecyclerView recyclerViewChat = (RecyclerView) findViewById(R.id.recyclerViewChat);
        chatAdapter = new ChatAdapter(chatModelList, sharedPref);
        final RecyclerView.LayoutManager rLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewChat.setLayoutManager(rLayoutManager);
        recyclerViewChat.setItemAnimator(new DefaultItemAnimator());
        recyclerViewChat.setAdapter(chatAdapter);

        //Get data from firebase
        try {
            //Get all messages
            ValueEventListener postListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    GenericTypeIndicator<List<ChatModel>> type = new GenericTypeIndicator<List<ChatModel>>() {};
                    newData = dataSnapshot.getValue(type);
                    if(newData != null) {
                        list.clear();
                        list.addAll(newData);
                        chatModelList.clear();
                        chatModelList.addAll(newData);
                        chatAdapter.notifyDataSetChanged();
                    }
                    else{
                        list.clear();
                        chatModelList.clear();
                        chatAdapter.notifyDataSetChanged();
                    }
                    if(recyclerViewChat.getAdapter().getItemCount() > 0)
                        recyclerViewChat.smoothScrollToPosition(recyclerViewChat.getAdapter().getItemCount() - 1);
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.i("Error", "error");
                }
            };
            mDatabase.addValueEventListener(postListener);


        }catch(Exception e){
            Log.i("Error", "firebase error retrieving data");
        }


        //Add Listener for EditText
        edit_txt.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                   list.add(new ChatModel(edit_txt.getText().toString(), sharedPref.getInt("userID", 0)));

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
