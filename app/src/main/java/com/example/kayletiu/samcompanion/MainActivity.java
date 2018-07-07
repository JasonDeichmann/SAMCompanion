package com.example.kayletiu.samcompanion;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabItem;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;
import android.support.design.widget.TabLayout;

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
                                Toast.makeText(MainActivity.this, "com.example.kayletiu.samcompanion.Community!", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.id_menu_games:
                                Intent intent1 = new Intent(MainActivity.this, GamesActivity.class);
                                startActivity(intent1);
                                Toast.makeText(MainActivity.this, "Games!", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.id_menu_sam:
                                Toast.makeText(MainActivity.this, "Sam!", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.id_menu_partners:
                                Toast.makeText(MainActivity.this, "Partners!", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.id_menu_exercise:
                                Toast.makeText(MainActivity.this, "Exercise!", Toast.LENGTH_SHORT).show();
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
