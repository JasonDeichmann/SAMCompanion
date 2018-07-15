package com.example.kayletiu.samcompanion;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.samsung.android.sdk.shealth.Shealth;
import com.samsung.android.sdk.shealth.tracker.TrackerInfo;
import com.samsung.android.sdk.shealth.tracker.TrackerManager;

/**
 * Created by Mharjorie Sandel on 07/07/2018.
 */

public class ExerciseActivity extends AppCompatActivity {

    private Shealth mShealth;
    private static final String APP_TAG = "HealthServiceExample";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
       // Toolbar toolbar = findViewById(R.id.toolbar5);
       // TextView title = (TextView) findViewById(R.id.tv_game);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navbar);
        Helper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(4);
        menuItem.setChecked(true);

        ImageView biking = findViewById(R.id.ImageView_Biking);
        biking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    launchTracker(0);
            }
        });
        ImageView Running = findViewById(R.id.ImageView_Running);
        Running.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchTracker(1);
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.id_menu_community:
                                Intent intent1 = new Intent (ExerciseActivity.this, MainActivity.class);
                                startActivityForResult(intent1, 0);
                                break;
                            case R.id.id_menu_games:
                                Intent intent2 = new Intent(ExerciseActivity.this, GamesActivity.class);
                                startActivity(intent2);
                                break;
                            case R.id.id_menu_sam:
                                Intent intent3 = new Intent(ExerciseActivity.this, Companion.class);
                                startActivity(intent3);
                                break;
                            case R.id.id_menu_partners:
                                Intent intent4 = new Intent (ExerciseActivity.this, PartnersActivity.class);
                                startActivityForResult(intent4, 0);
                                break;
                            case R.id.id_menu_exercise:
                                Intent intent5 = new Intent (ExerciseActivity.this, ExerciseActivity.class);
                                startActivityForResult(intent5, 0);
                                break;
                        }

                        return true;
                    }
                });
    }

    public void launchTracker(int track) {

        // Initialize the Health service
        mShealth = new Shealth();
        try {
            mShealth.initialize(this);
        } catch (Exception e) {
            Log.d(APP_TAG, "Health Service Initialization failed.-" + e.toString());
        }

        // Check the feature availability for all wanted features.
        if (mShealth.isFeatureEnabled(Shealth.FEATURE_TRACKER, Shealth.FEATURE_TRACKER_LAUNCH_EXTENDED)) {

            // All wanted features are available.
            // You can use all Health Service's APIs.

        } else {
            // If any feature is not available, Samsung Health should be upgraded.
            Log.d(APP_TAG, "Samsung Health should be upgraded");

            // Let the user upgrade Samsung Health to the latest version.
            Intent intent;
            intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=com.sec.android.app.shealth"));
            this.startActivity(intent);
        }

        // Get the "Weight" tracker's info
        TrackerManager trackerManager = new TrackerManager(this);
        TrackerInfo trackerInfo= trackerManager.getTrackerInfo(TrackerManager.TrackerId.EXERCISE_BIKE);
        if (track == 0) {
            trackerInfo = trackerManager.getTrackerInfo(TrackerManager.TrackerId.EXERCISE_BIKE);
        }
        else if (track == 1){
            trackerInfo = trackerManager.getTrackerInfo(TrackerManager.TrackerId.RUNNING);
        }

        // Check the tracker's availability
        if (!trackerInfo.isAvailable())
            Log.d(APP_TAG, "The tracker is not available.");

        // Launch the "Weight" tracker
        try {
            trackerManager.startActivity(this, trackerInfo.getId());
        } catch (Exception e1) {
            Log.d(APP_TAG, "Starting the tracker failed" + e1.toString());
        }
    }

}
