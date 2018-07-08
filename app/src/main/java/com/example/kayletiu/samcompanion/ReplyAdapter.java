package com.example.kayletiu.samcompanion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jayvee Gabriel on 08/07/2018.
 */


public class ReplyAdapter  extends RecyclerView.Adapter<ReplyAdapter.MyViewHolder> {
    private List<Reply> replies;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView author, content, title,datePosted;
        public Button reply;


        public MyViewHolder(View view) {
            super(view);
            author = (TextView) view.findViewById(R.id.post_author);
            content = (TextView) view.findViewById(R.id.post_content);
            datePosted = view.findViewById(R.id.post_dateposted);

        }
    }
    public ReplyAdapter(List<Reply> replies) {
        this.replies = replies;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_reply, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final Reply post = replies.get(position);
        holder.author.setText("Posted by " + "dogfood");

        holder.content.setText(post.getContent());
        holder.datePosted.setText("09-07-18");

    }


    @Override
    public int getItemCount() {
        return replies.size();
    }
}
