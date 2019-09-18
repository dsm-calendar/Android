package com.example.dsm_calendar.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.ui.activity.MessageActivity;

import java.util.ArrayList;

public class MessageRVAdapter extends RecyclerView.Adapter<MessageRVAdapter.MessageViewHolder> {

    private ArrayList<String> messageList;
    private ArrayList<String> dateList;
    private Context context;

    public MessageRVAdapter(ArrayList<String> message, ArrayList<String> date, Context context){
        this.messageList = message;
        this.dateList = date;
        this.context = context;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_message, parent, false);
        MessageViewHolder holder = new MessageViewHolder(item);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        String message = messageList.get(position);
        String date = dateList.get(position);

        holder.bind(message, date);
        String test = String.format("item %d clicked", position);
        holder.itemView.setOnClickListener( v -> {
            Toast.makeText(context, test, Toast.LENGTH_LONG).show();
            ((MessageActivity)context).finish();
        });
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    class MessageViewHolder extends RecyclerView.ViewHolder {

        TextView tv_message;
        TextView tv_date;

        MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_message = itemView.findViewById(R.id.tv_message);
            tv_date = itemView.findViewById(R.id.tv_message_date);
        }

        void bind(String message, String date){
            tv_message.setText(message);
            tv_date.setText(date);
        }
    }


}
