package com.example.kayletiu.samcompanion.PartnersActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.kayletiu.samcompanion.GamesActivity.Games;
import com.example.kayletiu.samcompanion.GamesActivity.GamesAdapter;
import com.example.kayletiu.samcompanion.R;

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
