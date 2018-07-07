package com.example.kayletiu.samcompanion;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Mharjorie Sandel on 07/07/2018.
 */

public class GamesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        TextView title = (TextView) findViewById(R.id.tv_game);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navbar);
        Helper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.id_menu_community:
                                Intent intent1 = new Intent(GamesActivity.this, MainActivity.class);
                                startActivity(intent1);
                                Toast.makeText(GamesActivity.this, "com.example.kayletiu.samcompanion.Community!", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.id_menu_games:

                                Toast.makeText(GamesActivity.this, "Games!", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.id_menu_sam:
                                Toast.makeText(GamesActivity.this, "Sam!", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.id_menu_partners:
                                Toast.makeText(GamesActivity.this, "Partners!", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.id_menu_exercise:
                                Toast.makeText(GamesActivity.this, "Exercise!", Toast.LENGTH_SHORT).show();
                                break;

                        }

                        return true;
                    }
                });
    }
}
