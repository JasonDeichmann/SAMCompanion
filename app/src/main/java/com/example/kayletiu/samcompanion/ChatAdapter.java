package com.example.kayletiu.samcompanion;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

        public MyViewHolder(View view){
            super(view);
            message = (TextView) view.findViewById(R.id.textViewChatMessage);
        }

        public TextView getMessage(){
            return message;
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
        Log.i("userMsg", chatModel.getMessage() + " " + position);
        holder.message.setText(chatModel.getMessage());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);

        if(chatModel.getUserID() == sharedPref.getInt("userID", 0)){
            params.weight = 1.0f;
            params.gravity = Gravity.END;
            holder.message.setLayoutParams(params);
            holder.message.setBackgroundResource(R.drawable.chat_message);
            holder.message.setTextColor(Color.WHITE);
            holder.message.setText(chatModel.getMessage());
        }
        else{
            params.weight = 1.0f;
            params.gravity = Gravity.START;
            holder.message.setBackgroundResource(R.drawable.chat_message_other);
            holder.message.setTextColor(Color.BLACK);
            holder.message.setLayoutParams(params);
            holder.message.setText(chatModel.getMessage());
        }
        //Log.i("userMessage", chatModelList.get(position).getMessage());
    }

    @Override
    public int getItemCount(){
        return chatModelList.size();
    }
}
