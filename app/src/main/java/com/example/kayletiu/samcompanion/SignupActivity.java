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
import android.view.View;
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
 * Firebase backend by Jason Deichmann on 15/07/2018
 */

public class SignupActivity extends AppCompatActivity {

    private EditText user, pass,pass2;
    private Button signup;

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
            Log.i("loggedOn", "true");
            Intent intent1 = new Intent(SignupActivity.this, MainActivity.class);
            startActivity(intent1);
        }
        else{
            editor.putBoolean("loggedOn", false);
            editor.apply();
            Log.i("loggedOn", "false" + " " + sharedPref.getBoolean("loggedOn", false));
        }

        setContentView(R.layout.activity_signup);
        user = (EditText)findViewById(R.id.usersign);
        pass = (EditText)findViewById(R.id.pass1);
        pass2 = (EditText) findViewById(R.id.pass2);
        signup = (Button) findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                //Check if password matches
                if (!pass.getText().toString().equals(pass2.getText().toString())) {
                    AlertDialog.Builder userPasswordAlert = new AlertDialog.Builder(SignupActivity.this);
                    userPasswordAlert.setTitle("Incorrect Password!");
                    userPasswordAlert.setMessage("The entered passwords do not match.");
                    userPasswordAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            pass.setText("");
                            pass2.setText("");
                        }
                    });

                    userPasswordAlert.show();
                } else {
                    //Get data from firebase
                    try {
                        //Get users/create new user
                        ValueEventListener usersListener = new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                GenericTypeIndicator<List<UserModel>> userType = new GenericTypeIndicator<List<UserModel>>() {
                                };
                                userData = dataSnapshot.getValue(userType);

                                if (userData != null) {
                                    Integer latestUserID = userData.get(userData.size() - 1).getUserID() + 1;
                                    Log.i("userID-NextHighest", Integer.toString(latestUserID));

                                    //Check if user already exists
                                    Boolean userExists = false;
                                    for (int x = 0; x < userData.size(); x++) {
                                        if (user.getText().toString().contains(userData.get(x).getUsername())) {
                                            userExists = true;
                                        }
                                    }

                                    if (userExists) {
                                        Log.i("userExists", "user already exists");
                                        AlertDialog.Builder usernameExistAlert = new AlertDialog.Builder(SignupActivity.this);
                                        usernameExistAlert.setTitle("Username already exists!");
                                        usernameExistAlert.setMessage("Please choose a different username.");
                                        usernameExistAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                user.setText("");
                                                pass.setText("");
                                                pass2.setText("");
                                            }
                                        });

                                        usernameExistAlert.show();
                                    } else {
                                        //Enter new user in the database
                                        UserModel newUser = new UserModel(latestUserID, user.getText().toString(), pass.getText().toString(), 1);
                                        userData.add(newUser);
                                        //Write to database
                                        usersReference.setValue(userData);
                                        AlertDialog.Builder userCreatedAlert = new AlertDialog.Builder(SignupActivity.this);
                                        userCreatedAlert.setTitle("Registration Successful!");
                                        userCreatedAlert.setMessage("Your account was successfully registered.");
                                        userCreatedAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                                startActivity(intent);
                                            }
                                        });

                                        userCreatedAlert.show();
                                    }
                                } else {
                                    //Assumes there is no user in the database
                                    userData = new ArrayList<UserModel>();
                                    UserModel newUser = new UserModel(1, user.getText().toString(), pass.getText().toString(), 1);
                                    userData.add(newUser);
                                    //Write to database
                                    usersReference.setValue(userData);
                                    AlertDialog.Builder userCreatedAlert = new AlertDialog.Builder(SignupActivity.this);
                                    userCreatedAlert.setTitle("Registration Successful!");
                                    userCreatedAlert.setMessage("Your account was successfully registered.");
                                    userCreatedAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            //Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                            //startActivity(intent);
                                        }
                                    });

                                    userCreatedAlert.show();
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
            }
        });
    }
}
