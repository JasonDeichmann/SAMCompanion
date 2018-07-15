package com.example.kayletiu.samcompanion;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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

        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navbar);
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
                                Intent intent1 = new Intent (GamesActivity.this, MainActivity.class);
                                startActivityForResult(intent1, 0);
                                break;
                            case R.id.id_menu_games:
                                Intent intent2 = new Intent(GamesActivity.this, GamesActivity.class);
                                startActivity(intent2);
                                break;
                            case R.id.id_menu_sam:
                                AlertDialog.Builder companionAlert = new AlertDialog.Builder(GamesActivity.this);
                                companionAlert.setTitle("SAM Companion");
                                companionAlert.setMessage("Would you like to talk to a companion?");
                                companionAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        AlertDialog.Builder searchAlert = new AlertDialog.Builder(GamesActivity.this);
                                        LayoutInflater li = LayoutInflater.from(GamesActivity.this);
                                        final View view = li.inflate(R.layout.searching_companion, null);
                                        searchAlert.setTitle("Please wait");
                                        searchAlert.setMessage("Looking for a Companion...");
                                        searchAlert.setView(view);
                                        searchAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                bottomNavigationView.setSelectedItemId(R.id.id_menu_games);
                                            }
                                        });
                                        searchAlert.show();
                                        //Intent companionIntent = new Intent(MainActivity.this, Companion.class);
                                        //startActivity(companionIntent);
                                    }
                                });
                                companionAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        bottomNavigationView.setSelectedItemId(R.id.id_menu_games);
                                    }
                                });
                                companionAlert.show();
                                break;
                            case R.id.id_menu_partners:
                                Intent intent4 = new Intent (GamesActivity.this, PartnersActivity.class);
                                startActivityForResult(intent4, 0);
                                break;
                            case R.id.id_menu_exercise:
                                Intent intent5 = new Intent (GamesActivity.this, ExerciseActivity.class);
                                startActivityForResult(intent5, 0);
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
