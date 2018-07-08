package com.example.kayletiu.samcompanion.GamesActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.kayletiu.samcompanion.R;

public class InGame extends AppCompatActivity {

    SharedPreferences preferencesSettings;
    SharedPreferences.Editor preferenceEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);
        preferencesSettings = PreferenceManager.getDefaultSharedPreferences(this);
        preferencesSettings = (this.getSharedPreferences("MySettings", Context.MODE_PRIVATE));
        ConstraintLayout c1 = findViewById(R.id.ConstraintLayout_InGame);
        int bgurl = preferencesSettings.getInt("bgurl", 0);
        c1.setBackground(this.getApplicationContext().getResources().getDrawable(bgurl, null));
        ImageButton exitbtn = findViewById(R.id.imageButton_back);
        exitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
