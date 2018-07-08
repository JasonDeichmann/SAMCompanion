package com.example.kayletiu.samcompanion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jayvee Gabriel on 08/07/2018.
 */


public class QuotesAdapter  extends RecyclerView.Adapter<QuotesAdapter.MyViewHolder> {
    private List<Quote> quotesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView user,author, content;


        public MyViewHolder(View view) {
            super(view);
            author = (TextView) view.findViewById(R.id.quote_author);
            user= view.findViewById(R.id.quote_user);

            content = (TextView) view.findViewById(R.id.quote_content);


        }
    }
    public QuotesAdapter(List<Quote> quotesList) {
        this.quotesList = quotesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        Quote q = quotesList.get(position);
        holder.user.setText("Posted by: " + q.getUser());
        holder.content.setText(q.getContent());
        holder.author.setText(q.getAuthor());

    }


    @Override
    public int getItemCount() {
        return quotesList.size();
    }
}
