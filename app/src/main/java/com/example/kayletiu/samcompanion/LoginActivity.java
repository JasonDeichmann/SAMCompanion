package com.example.kayletiu.samcompanion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;;////////
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Mharjorie Sandel on 08/07/2018.
 */

public class LoginActivity extends AppCompatActivity {
private EditText username, password;
private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText)findViewById(R.id.usersign);
        password = (EditText)findViewById(R.id.pass1);
        login = (Button) findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
