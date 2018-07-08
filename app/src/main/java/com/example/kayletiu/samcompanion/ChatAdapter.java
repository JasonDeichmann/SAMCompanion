package com.example.kayletiu.samcompanion;

import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static android.view.View.GONE;

/**
 * Created by Jason Deichmann on 7/7/18.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder>{

    private List<ChatModel> chatModelList;
    private SharedPreferences sharedPref;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView message;
        public TextView messageOther;

        public MyViewHolder(View view){
            super(view);
            message = (TextView) view.findViewById(R.id.textViewChatMessage);
            messageOther = (TextView) view.findViewById(R.id.textViewChatMessageOtherUser);
        }

        public TextView getMessage(){
            return message;
        }

        public TextView getMessageOther(){
            return messageOther;
        }
    }

    public ChatAdapter(List<ChatModel> chatModelList, SharedPreferences sharedPref){
        this.chatModelList = chatModelList;
        this.sharedPref = sharedPref;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        ChatModel chatModel = chatModelList.get(position);
        //Differentiate user message and others
        if(chatModelList.get(position).getUserID() == sharedPref.getInt("userID", 0)){
            holder.message.setText(chatModel.getMessage());
            holder.messageOther.setVisibility(GONE);
        }
        else{
            holder.messageOther.setText(chatModel.getMessage());
            holder.message.setVisibility(GONE);
        }
        //Log.i("userMessage", chatModelList.get(position).getMessage());
    }

    @Override
    public int getItemCount(){
        return chatModelList.size();
    }
}
