package com.example.kayletiu.samcompanion;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.samsung.android.sdk.shealth.Shealth;
import com.samsung.android.sdk.shealth.tracker.TrackerInfo;
import com.samsung.android.sdk.shealth.tracker.TrackerManager;

public class Exercise extends AppCompatActivity {
    private Shealth mShealth;
    private static final String APP_TAG = "HealthServiceExample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

            Button SHEALTHbtn = findViewById(R.id.SHEALTH);
            SHEALTHbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    launchWeightTracker();
                }
            });
        }
    public void launchWeightTracker() {

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
        TrackerInfo trackerInfo = trackerManager.getTrackerInfo(TrackerManager.TrackerId.WEIGHT);

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
