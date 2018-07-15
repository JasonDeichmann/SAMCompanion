package com.example.kayletiu.samcompanion;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPost extends AppCompatActivity {

    private int latestId = 1;
    private String author = "doggy";
    private EditText post;
    private EditText title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_post);
        //Toolbar toolbar = findViewById(R.id.toolbaradd);

        // toolbar.setTitle(getResources().getString(R.string.app_name));
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navbar);
        Helper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.id_menu_community:
                                Intent intent1 = new Intent(AddPost.this, MainActivity.class);
                                startActivity(intent1);

                                break;
                            case R.id.id_menu_games:
                                Intent intent2 = new Intent(AddPost.this, GamesActivity.class);
                                startActivity(intent2);
                                break;
                            case R.id.id_menu_sam:
                                Intent intent3 = new Intent(AddPost.this,Companion.class);
                                startActivity(intent3);
                                break;
                            case R.id.id_menu_partners:
                                break;
                            case R.id.id_menu_exercise:
                                Intent intent5 = new Intent(AddPost.this, ExerciseActivity.class);
                                startActivity(intent5);
                                break;

                        }

                        return true;
                    }
                });
        Button b1 = findViewById(R.id.btn_back);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Button b2 = findViewById(R.id.btn_send);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!post.getText().toString().equals("") && !title.getText().toString().equals("")){
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("content",post.getText().toString());
                    bundle.putString("title", title.getText().toString());
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }


//
//                write();
//                finish();
            }
        });
        post = findViewById(R.id.text_post);
        title = findViewById(R.id.text_title);
//
//        String [] language = { "JavaScript", "Java", "C"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, language);
//        AutoCompleteTextView acTextView = findViewById(R.id.autoCompleteTextView);
//        acTextView.setThreshold(1);
//        acTextView.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_add, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_menu_back:
                Toast.makeText(this, "EMail", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        return true;
    }




}