package com.example.kayletiu.samcompanion;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jason Deichmann on 7/7/18.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder>{

    private List<ChatModel> chatModelList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView message;

        public MyViewHolder(View view){
            super(view);
            message = (TextView) view.findViewById(R.id.textViewChatMessage);
        }

        public TextView getMessage(){
            return message;
        }
    }

    public ChatAdapter(List<ChatModel> chatModelList){
        this.chatModelList = chatModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        ChatModel chatModel = chatModelList.get(position);
        holder.message.setText(chatModel.getMessage());
    }

    @Override
    public int getItemCount(){
        return chatModelList.size();
    }
}
