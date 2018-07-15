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


public class PostAdapter  extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    private List<Post> postList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView author, content, title,datePosted;
        public Button reply;


        public MyViewHolder(View view) {
            super(view);
            author = (TextView) view.findViewById(R.id.post_author);
            title = view.findViewById(R.id.post_title);
            content = (TextView) view.findViewById(R.id.post_content);
            datePosted = view.findViewById(R.id.post_dateposted);
            reply = view.findViewById(R.id.post_reply);

        }
    }
    public PostAdapter(List<Post> postList) {
        this.postList = postList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_post, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final Post post = postList.get(position);
        holder.author.setText("Posted by " + post.getAuthor());
        holder.title.setText(post.getTitle());
        holder.content.setText(post.getContent());
        holder.datePosted.setText(post.getDatePosted());
        holder.reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ReplyPost.class);
                Bundle bundle = new Bundle();
                bundle.putString("author", post.getAuthor());
                bundle.putString("title", post.getTitle());
                bundle.putString("content", post.getContent());
                bundle.putString("datePosted", post.getDatePosted());
                bundle.putInt("id", post.getId());
                Log.d("HAHA", post.getId() +"");
                bundle.putInt("position", position);
                intent.putExtras(bundle);
                ((Activity) v.getContext()).startActivityForResult(intent, 10);
            }
        });
    }


    @Override
    public int getItemCount() {
        return postList.size();
    }
}