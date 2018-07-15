package com.example.kayletiu.samcompanion;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;;////////
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mharjorie Sandel on 08/07/2018.
 */

public class LoginActivity extends AppCompatActivity {
private EditText username, password;
private Button login;

//Firebase
    private DatabaseReference usersReference = FirebaseDatabase.getInstance().getReference("users");
    List<UserModel> userData = new ArrayList<UserModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ///user
        //userID - firebase
        //username - firebase
        //UserPassword - firebase
        //loggedOn - local

        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = sharedPref.edit();

        //Check if user is already logged in
        if(sharedPref.getBoolean("loggedOn", false)){
            Log.i("loggedOn",  ""+ sharedPref.getBoolean("loggedOn", false));
            Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent1);
        }
        else{
            editor.putBoolean("loggedOn", false);
            editor.apply();
            Log.i("loggedOn", "false");
        }

        setContentView(R.layout.activity_login);
        username = (EditText)findViewById(R.id.usersign);
        password = (EditText)findViewById(R.id.pass1);
        login = (Button) findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

            try {
                //Get users
                ValueEventListener usersListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        GenericTypeIndicator<List<UserModel>> userType = new GenericTypeIndicator<List<UserModel>>() {
                        };
                        userData = dataSnapshot.getValue(userType);

                        if (userData != null) {
                            //Match username and password
                            Boolean userExists = false;
                            Integer userID = 0;
                            for (int x = 0; x < userData.size(); x++) {
                                if (username.getText().toString().equals(userData.get(x).getUsername()) && password.getText().toString().equals(userData.get(x).getPassword())) {
                                    userExists = true;
                                    userID = userData.get(x).getUserID();
                                }
                            }

                            if (userExists) {
                                Log.i("userExists", "login successful");
                                editor.putBoolean("loggedOn", true);
                                editor.putInt("userID", userID);
                                editor.apply();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                //Invalid username/password
                                AlertDialog.Builder userInvalidAlert = new AlertDialog.Builder(LoginActivity.this);
                                userInvalidAlert.setTitle("Invalid Username/Password");
                                userInvalidAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        password.setText("");
                                    }
                                });
                                userInvalidAlert.show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.i("Error", "error getting users");
                    }
                };
                usersReference.addListenerForSingleValueEvent(usersListener);

            } catch (Exception e) {
                Log.i("Error", "firebase error");
            }
            }
        });
    }
}
