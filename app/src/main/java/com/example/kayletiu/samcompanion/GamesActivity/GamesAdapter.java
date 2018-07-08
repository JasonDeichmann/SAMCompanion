package com.example.kayletiu.samcompanion.GamesActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kayletiu.samcompanion.MainActivity;
import com.example.kayletiu.samcompanion.R;

import java.util.List;

/**
 * Created by Kayle Tiu on 08/07/2018.
 */

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.MyViewHolder> {

    private static final String LOG_TAG = "RestoAdapter";
    private List<Games> gamesList;
    private static final String NAME_KEY = "name_key";
    private static final String DES_KEY = "des_key";
    private static final String WEIGHT_KEY = "weight_key";
    private static final String POSITION_KEY = "position_key";

    public class MyViewHolder extends RecyclerView.ViewHolder {
        SharedPreferences preferencesSettings;
        SharedPreferences.Editor preferenceEditor;
        public TextView name;
        public ImageView icon;
        public Button playbtn;

        public MyViewHolder(View view) {
            super(view);
            preferencesSettings = PreferenceManager.getDefaultSharedPreferences(view.getContext());
            preferencesSettings = (view.getContext()).getSharedPreferences("MySettings",Context.MODE_PRIVATE);
            name = view.findViewById(R.id.TextView_GamesList_Name);
            icon = view.findViewById(R.id.ImageView_GameIcon);
            playbtn = view.findViewById(R.id.Button_Play);
        }

        public void setupListener(Button button, final int bgurl){
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    preferencesSettings = PreferenceManager.getDefaultSharedPreferences((Activity) view.getContext());
                    preferencesSettings = (view.getContext()).getSharedPreferences("MySettings", Context.MODE_PRIVATE);
                    preferenceEditor = preferencesSettings.edit();
                    preferenceEditor.putInt("bgurl", bgurl);
                    preferenceEditor.apply();
                    Intent intent = new Intent(view.getContext(), InGame.class);
                    ((Activity) view.getContext()).startActivityForResult(intent,0);
                }
            });
        }

    }

    public GamesAdapter(List<Games> restaurantsList) {
        this.gamesList = restaurantsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Games games = gamesList.get(position);
        holder.name.setText(games.getName());
        holder.icon.setImageResource(games.getImgurl());
        Button button = holder.playbtn;
        holder.setupListener(button, games.getBgurl());

    }

    @Override
    public int getItemCount() {
        return gamesList.size();
    }
}
