package com.example.kayletiu.samcompanion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddQuote extends AppCompatActivity {

    private EditText quoteContent, quoteAuthor;
    private Button sendBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quote);


        sendBtn = findViewById(R.id.quote_send);
        quoteContent = findViewById(R.id.quote_content);
        quoteAuthor = findViewById(R.id.quote_author);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!quoteAuthor.getText().toString().equals("") && !quoteContent.getText().toString().equals("")){
                    Intent intent = new Intent();
                    Bundle b1 = new Bundle();
                    b1.putString("quoteContent", quoteContent.getText().toString());
                    b1.putString("quoteAuthor", quoteAuthor.getText().toString());
                    intent.putExtras(b1);
                    setResult(RESULT_OK, intent);
                    finish();
                }

            }
        });

    }
}