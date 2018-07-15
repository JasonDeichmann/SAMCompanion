package com.example.kayletiu.samcompanion;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

//import com.example.kayletiu.samcompanion.GamesActivity.GamesActivity;

public class MainActivity extends AppCompatActivity implements News.OnFragmentInteractionListener, Home.OnFragmentInteractionListener,
        Quotes.OnFragmentInteractionListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navbar);
        Helper.disableShiftMode(bottomNavigationView);
        // Toolbar toolbar = findViewById(R.id.toolbar);
        //toolbar.setTitle(getResources().getString(R.string.app_name));
        TabLayout tabLayout = findViewById(R.id.tablayout);
        TabItem tabChats = findViewById(R.id.tabNews);
        TabItem tabStatus = findViewById(R.id.tabHome);
        TabItem tabCalls = findViewById(R.id.tabQuotes);
        final ViewPager viewPager = findViewById(R.id.viewPager);
        final PagerAdapter pageAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.id_menu_community:
                                Intent intent1 = new Intent (MainActivity.this, ExerciseActivity.class);
                                startActivityForResult(intent1, 0);
                                break;
                            case R.id.id_menu_games:
                                Intent intent2 = new Intent(MainActivity.this, GamesActivity.class);
                                startActivity(intent2);
                                break;
                            case R.id.id_menu_sam:
                                Intent intent3 = new Intent(MainActivity.this, Companion.class);
                                startActivity(intent3);
                                break;
                            case R.id.id_menu_partners:
                                Intent intent4 = new Intent (MainActivity.this, PartnersActivity.class);
                                startActivityForResult(intent4, 0);
                                break;
                            case R.id.id_menu_exercise:
                                Intent intent5 = new Intent (MainActivity.this, ExerciseActivity.class);
                                startActivityForResult(intent5, 0);
                                break;
                        }

                        return true;
                    }
                });


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}