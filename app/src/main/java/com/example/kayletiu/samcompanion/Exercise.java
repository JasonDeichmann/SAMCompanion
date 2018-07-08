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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

            Button SHEALTHbtn = findViewById(R.id.SHEALTH);
            SHEALTHbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getRunningTrackerInfo();
                }
            });
        }

    public TrackerInfo getRunningTrackerInfo() {
        // Create a TrackerManager instance
        TrackerManager trackerManager = new TrackerManager(getApplicationContext());

        // Get the "Running" tracker info's instance
        TrackerInfo trackerInfo = trackerManager.getTrackerInfo(TrackerManager.TrackerId.RUNNING);

        if(trackerInfo.isAvailable())
            return trackerInfo;
        else
            return null;
    }
    }
