package com.example.kayletiu.samcompanion;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Kayle Tiu on 08/07/2018.
 */

public class PartnersAdapter extends RecyclerView.Adapter<PartnersAdapter.MyViewHolder> {
    public PartnersAdapter(List<Partner> partnersList) {
        this.partnersList = partnersList;
    }

    private List<Partner> partnersList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView icon;
        public TextView announcements;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.TextView_OrgName);
            icon = view.findViewById(R.id.ImageView_OrgIcon);
            announcements = view.findViewById(R.id.TextView_Announcements);
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.partner_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Partner partner = partnersList.get(position);
        holder.name.setText(partner.getName());
        holder.icon.setImageResource(partner.getImgurl());
        holder.announcements.setText(partner.getAnnouncements());
    }

    @Override
    public int getItemCount() {
        return partnersList.size();
    }
}
