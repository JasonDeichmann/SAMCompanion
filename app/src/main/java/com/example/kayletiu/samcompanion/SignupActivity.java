package com.example.kayletiu.samcompanion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Mharjorie Sandel on 08/07/2018.
 */

public class SignupActivity extends AppCompatActivity {
    private EditText user, pass,pass2;
    private Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        user = (EditText)findViewById(R.id.usersign);
        pass = (EditText)findViewById(R.id.pass1);
        pass2 = (EditText) findViewById(R.id.pass2);
        signup = (Button) findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
