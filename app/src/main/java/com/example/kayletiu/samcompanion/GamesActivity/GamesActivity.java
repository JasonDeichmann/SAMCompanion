package com.example.kayletiu.samcompanion.GamesActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kayletiu.samcompanion.Helper;
import com.example.kayletiu.samcompanion.MainActivity;
import com.example.kayletiu.samcompanion.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mharjorie Sandel on 07/07/2018.
 */

public class GamesActivity extends AppCompatActivity {

    private List<Games> gamesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private GamesAdapter gAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        setContentView(R.layout.activity_games);
        recyclerView = findViewById(R.id.RecyclerView_Games);

        gAdapter = new GamesAdapter(gamesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(gAdapter);

        prepareGamesData();

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void prepareGamesData() {
        Games games = new Games("Words With Friends", R.drawable.icon_wordswithfriends, R.drawable.bg_wordswithfriends);
        gamesList.add(games);
        games = new Games("Everwing", R.drawable.icon_everwing, R.drawable.bg_everwing);
        gamesList.add(games);
        games = new Games("Basketball", R.drawable.icon_basketball, R.drawable.bg_basketball);
        gamesList.add(games);
        games = new Games("Ludo Club", R.drawable.icon_ludoclub, R.drawable.bg_ludoclub);
        gamesList.add(games);
        games = new Games("Cookie Crush", R.drawable.icon_cookiecrush, R.drawable.bg_cookiecrush);
        gamesList.add(games);
        gAdapter.notifyDataSetChanged();
    }

}
