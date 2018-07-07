package com.example.kayletiu.samcompanion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class companion_activity extends AppCompatActivity {

    //Firebase db variables
    private FirebaseDatabase database = FirebaseDatabase.getInstance{};
    private DatabaseReference myRef = database.getReference("message");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companion_activity);


    }
}
