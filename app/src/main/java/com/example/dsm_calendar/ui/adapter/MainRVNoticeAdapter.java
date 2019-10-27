package com.example.dsm_calendar.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;

import java.util.ArrayList;

public class MainRVNoticeAdapter extends RecyclerView.Adapter<MainRVNoticeAdapter.MainNoticeViewHolder> {

    public ArrayList<String> notice = new ArrayList<>();
    private Context context;

    public MainRVNoticeAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public MainNoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_main, parent, false);
        MainNoticeViewHolder holder = new MainNoticeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainNoticeViewHolder holder, int position) {
        holder.bind(notice.get(position));
    }

    @Override
    public int getItemCount() {
        return notice.size();
    }

    public class MainNoticeViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MainNoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.main_rv_item_tv);
        }

        public void bind(String notice){
            textView.setText(notice);
        }
    }
}
