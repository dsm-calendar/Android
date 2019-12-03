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
import com.example.dsm_calendar.data.DTO.Message;
import com.example.dsm_calendar.presenter.MessagePresenter;
import com.example.dsm_calendar.ui.dialog.MessageDeleteDialog;
import com.example.dsm_calendar.ui.dialog.GroupInviteDialog;

import java.util.ArrayList;

public class MessageRVAdapter extends RecyclerView.Adapter<MessageRVAdapter.MessageViewHolder> {

    public ArrayList<Message> messageList = new ArrayList<>();
    private Context context;
    private MessagePresenter messagePresenter;

    public MessageRVAdapter(Context context, MessagePresenter messagePresenter){
        this.context = context;
        this.messagePresenter = messagePresenter;
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
        Message message = messageList.get(position);

        holder.bind(message);
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

        void bind(Message message){
            String date = message.getSendDateNow();
            String content = message.getSendUserId() + "그룹에 초대되었습니다.";

            tv_message.setText(content);
            tv_date.setText(date);
            itemView.setOnClickListener(v -> messagePresenter.onClickItem(message.getMessageId()));
            itemView.setOnLongClickListener(v -> {
                messagePresenter.onLongClickItem(message.getMessageId());
                return true;
            });
        }
    }
}
