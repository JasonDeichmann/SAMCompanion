package com.example.kayletiu.samcompanion;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

//import com.example.kayletiu.samcompanion.GamesActivity.GamesActivity;

public class MainActivity extends AppCompatActivity implements News.OnFragmentInteractionListener, Home.OnFragmentInteractionListener,
        Quotes.OnFragmentInteractionListener{

    private DatabaseReference usersReference = FirebaseDatabase.getInstance().getReference("users");
    List<UserModel> userData = new ArrayList<UserModel>();
    Integer userIndex = 0;
    Integer ngoIndex = 0;
    Integer highestLobby = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Shared prefs
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = sharedPref.edit();

        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navbar);
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
                                Intent intent1 = new Intent (MainActivity.this, MainActivity.class);
                                startActivityForResult(intent1, 0);
                                break;
                            case R.id.id_menu_games:
                                Intent intent2 = new Intent(MainActivity.this, GamesActivity.class);
                                startActivity(intent2);
                                break;
                            case R.id.id_menu_sam:
                                AlertDialog.Builder companionAlert = new AlertDialog.Builder(MainActivity.this);
                                companionAlert.setTitle("SAM Companion");
                                companionAlert.setMessage("Would you like to talk to a companion?");
                                companionAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //Alert searching
                                        AlertDialog.Builder searchAlert = new AlertDialog.Builder(MainActivity.this);
                                        LayoutInflater li = LayoutInflater.from(MainActivity.this);
                                        final View view = li.inflate(R.layout.searching_companion, null);
                                        searchAlert.setTitle("Please wait");
                                        searchAlert.setMessage("Looking for a Companion...");
                                        searchAlert.setView(view);
                                        searchAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                bottomNavigationView.setSelectedItemId(R.id.id_menu_community);
                                                //Firebase
                                                try {
                                                    //Get users
                                                    ValueEventListener usersListener = new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                                            GenericTypeIndicator<List<UserModel>> userType = new GenericTypeIndicator<List<UserModel>>() {
                                                            };
                                                            userData = dataSnapshot.getValue(userType);

                                                            if (userData != null) {
                                                                //Match userID
                                                                Integer userIndex = 0;
                                                                for (int x = 0; x < userData.size(); x++) {
                                                                    if (sharedPref.getInt("userID", 0) == userData.get(x).getUserID()) {
                                                                        userIndex = x;
                                                                    }
                                                                }
                                                                userData.get(userIndex).setSearching(0);
                                                                usersReference.setValue(userData);
                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(DatabaseError databaseError) {
                                                            Log.i("Error", "error getting users");
                                                        }
                                                    };
                                                    usersReference.addListenerForSingleValueEvent(usersListener);

                                                } catch (Exception e) {
                                                    Log.i("Error", "firebase error");
                                                }
                                            }
                                        });
                                        searchAlert.show();

                                        //Firebase
                                        try {
                                            //Get users
                                            ValueEventListener usersListener = new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                    GenericTypeIndicator<List<UserModel>> userType = new GenericTypeIndicator<List<UserModel>>() {
                                                    };
                                                    userData = dataSnapshot.getValue(userType);

                                                    if (userData != null) {
                                                        //Match userID
                                                        Integer userIndex = 0;
                                                        for (int x = 0; x < userData.size(); x++) {
                                                            if (sharedPref.getInt("userID", 0) == userData.get(x).getUserID()) {
                                                                userIndex = x;
                                                            }
                                                        }
                                                        if(userData.get(userIndex).getSearching() == 0) {
                                                            userData.get(userIndex).setSearching(1);
                                                            usersReference.setValue(userData);
                                                        }
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(DatabaseError databaseError) {
                                                    Log.i("Error", "error getting users");
                                                }
                                            };
                                            usersReference.addListenerForSingleValueEvent(usersListener);

                                        } catch (Exception e) {
                                            Log.i("Error", "firebase error");
                                        }
                                    }
                                });
                                companionAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        bottomNavigationView.setSelectedItemId(R.id.id_menu_community);
                                    }
                                });
                                companionAlert.show();
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

        ///If user is NGO
        //Firebase
        try {
            //Get users
            ValueEventListener usersNGOListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    GenericTypeIndicator<List<UserModel>> userType = new GenericTypeIndicator<List<UserModel>>() {
                    };
                    userData = dataSnapshot.getValue(userType);

                    if (userData != null) {
                        //Match userID
                        for (int x = 0; x < userData.size(); x++) {
                            if (userData.get(x).getChatRoom() >= highestLobby) {
                                highestLobby = userData.get(x).getChatRoom() + 1;
                                Log.i("chatting", "highest lobby: " + highestLobby);
                            }
                            if (sharedPref.getInt("userID", 0) == userData.get(x).getUserID()) {
                                ngoIndex = x;
                            }
                        }
                        //NGO accept search request
                        if (userData.get(ngoIndex).getChatting() == 0) {
                            Log.i("chatting", "request chat/get chatting");
                            Boolean userFound = false;
                            if (userData.get(ngoIndex).getUserType() == 2) {
                                Log.i("chatting", "user is ngo");
                                for (int x = 0; x < userData.size(); x++) {
                                    if (userData.get(x).getSearching() == 1) {
                                        userIndex = x;
                                        userFound = true;
                                    }
                                }
                                if(userFound) {
                                    //Alert searching
                                    Log.i("chatting", "user found!");
                                    AlertDialog.Builder searchNGOAlert = new AlertDialog.Builder(MainActivity.this);
                                    LayoutInflater li = LayoutInflater.from(MainActivity.this);
                                    final View view = li.inflate(R.layout.searching_companion, null);
                                    searchNGOAlert.setTitle(userData.get(userIndex).getUsername() + " is looking for a companion!");
                                    searchNGOAlert.setMessage("Would you like to talk to " + userData.get(userIndex).getUsername() + "?");
                                    searchNGOAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            //Change NGO values
                                            userData.get(ngoIndex).setChatting(1);
                                            userData.get(ngoIndex).setChatRoom(highestLobby);

                                            //Change user values
                                            userData.get(userIndex).setSearching(0);
                                            userData.get(userIndex).setChatting(1);
                                            userData.get(userIndex).setChatRoom(highestLobby);

                                            usersReference.setValue(userData);

                                            editor.putInt("chatRoom", highestLobby);
                                            editor.apply();
                                            Intent i2 = new Intent(MainActivity.this, Companion.class);
                                            startActivity(i2);

                                        }
                                    });
                                    searchNGOAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            bottomNavigationView.setSelectedItemId(R.id.id_menu_community);

                                        }
                                    });
                                    searchNGOAlert.show();
                                }
                            }
                        }

                        if(userData.get(userIndex).getChatting() == 1 && userData.get(userIndex).getUserType() == 1){
                            editor.putInt("chatRoom", userData.get(userIndex).getChatRoom());
                            editor.apply();
                            Intent i = new Intent(MainActivity.this, Companion.class);
                            startActivity(i);
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.i("Error", "error getting users");
                }
            };
            usersReference.addValueEventListener(usersNGOListener);

        } catch (Exception e) {
            Log.i("Error", "firebase error");
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}