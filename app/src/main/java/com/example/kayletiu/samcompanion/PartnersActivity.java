package com.example.kayletiu.samcompanion;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class PartnersActivity extends AppCompatActivity {

    private List<Partner> partnerList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PartnersAdapter pAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partners);

        recyclerView = findViewById(R.id.RecyclerView_Partners);

        pAdapter = new PartnersAdapter(partnerList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(pAdapter);

        preparePartnersData();

        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navbar);
        Helper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.id_menu_community:
                                Intent intent1 = new Intent (PartnersActivity.this, MainActivity.class);
                                startActivityForResult(intent1, 0);
                                break;
                            case R.id.id_menu_games:
                                Intent intent2 = new Intent(PartnersActivity.this, GamesActivity.class);
                                startActivity(intent2);
                                break;
                            case R.id.id_menu_sam:
                                AlertDialog.Builder companionAlert = new AlertDialog.Builder(PartnersActivity.this);
                                companionAlert.setTitle("SAM Companion");
                                companionAlert.setMessage("Would you like to talk to a companion?");
                                companionAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        AlertDialog.Builder searchAlert = new AlertDialog.Builder(PartnersActivity.this);
                                        LayoutInflater li = LayoutInflater.from(PartnersActivity.this);
                                        final View view = li.inflate(R.layout.searching_companion, null);
                                        searchAlert.setTitle("Please wait");
                                        searchAlert.setMessage("Looking for a Companion...");
                                        searchAlert.setView(view);
                                        searchAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                bottomNavigationView.setSelectedItemId(R.id.id_menu_partners);
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
                                        bottomNavigationView.setSelectedItemId(R.id.id_menu_partners);
                                    }
                                });
                                companionAlert.show();
                                break;
                            case R.id.id_menu_partners:
                                Intent intent4 = new Intent (PartnersActivity.this, PartnersActivity.class);
                                startActivityForResult(intent4, 0);
                                break;
                            case R.id.id_menu_exercise:
                                Intent intent5 = new Intent (PartnersActivity.this, ExerciseActivity.class);
                                startActivityForResult(intent5, 0);
                                break;
                        }

                        return true;
                    }
                });
    }

    private void preparePartnersData() {
        Partner partner = new Partner(R.drawable.icon_pmha, "Philippine Mental Health Association, Inc.", "Mental Health Advocates",
                "July 03, 2018 \n Marikina Lusog Isip ng Kabataan (LINK) Club Adviser's Meeting 8AM " +
                        "\n \n July 10-12, 2018 \n Mental Health Community-Based Program (Level 3) in Marikina Healthy City Center"
                        + "\n \n July 17-20, 2018 \n Mental Health Community-Based Program (Level 3) in San Carlos, Pangasinan");
        partnerList.add(partner);
        partner = new Partner(R.drawable.icon_ncmh, "National Center For Mental Health", "",
                "July 20, 2018 \n Mental Health Seminar" +
                        "\n\n August 01, 2018 \n How to cope up with Anxiety Talk");
        partnerList.add(partner);
        pAdapter.notifyDataSetChanged();
    }

}
