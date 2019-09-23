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
import com.example.dsm_calendar.ui.dialog.MessageDeleteDialog;
import com.example.dsm_calendar.ui.dialog.GroupInviteDialog;

import java.util.ArrayList;

public class MessageRVAdapter extends RecyclerView.Adapter<MessageRVAdapter.MessageViewHolder> {

    private ArrayList<String> messageList;
    private ArrayList<String> dateList;
    private Context context;
    private GroupInviteDialog groupInviteDialog;
    private MessageDeleteDialog messageDeleteDialog;

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
        groupInviteDialog = new GroupInviteDialog(context, MCDOffButtonListener, MCDYesButtonListener, MCDNoButtonListener);
        messageDeleteDialog = new MessageDeleteDialog(context, MDDOffButtonListener, MDDYesButtonListener, MDDNoButtonListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        String message = messageList.get(position);
        String date = dateList.get(position);

        holder.bind(message, date);

        holder.itemView.setOnClickListener( v -> {
            groupInviteDialog.show();
        });

        holder.itemView.setOnLongClickListener( v -> {
            messageDeleteDialog.show();
            return true;
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

    private View.OnClickListener MCDOffButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            groupInviteDialog.dismiss();
        }
    };

    private View.OnClickListener MCDYesButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(groupInviteDialog.getContext(), "yes!", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener MCDNoButtonListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Toast.makeText(groupInviteDialog.getContext(), "no....", Toast.LENGTH_SHORT).show();
        }
    };

    private  View.OnClickListener MDDOffButtonListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            messageDeleteDialog.dismiss();
        }
    };

    private View.OnClickListener MDDYesButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(groupInviteDialog.getContext(), "yes!", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener MDDNoButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(groupInviteDialog.getContext(), "no....", Toast.LENGTH_SHORT).show();
        }
    };
}
