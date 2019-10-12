package com.example.dsm_calendar.ui.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.presenter.GroupPresenter;

import java.util.ArrayList;

public class GroupRVAdapter extends RecyclerView.Adapter<GroupRVAdapter.GroupViewHolder> {

    public ArrayList<String> groupList;
    private Context context;
    private GroupPresenter groupPresenter;

    public GroupRVAdapter(Context context, GroupPresenter groupPresenter){
        this.context = context;
        this.groupPresenter = groupPresenter;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_group, parent, false);
        GroupViewHolder holder = new GroupViewHolder(item);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        String name = groupList.get(position);
        holder.bind(name);
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    public class GroupViewHolder extends RecyclerView.ViewHolder {
        TextView tv_group_name ;
        ImageButton menu;

        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_group_name = itemView.findViewById(R.id.tv_item_group_name);
            menu = itemView.findViewById(R.id.button_item_group_menu);
        }

        private void bind(String groupName){
            tv_group_name.setText(groupName);
            itemView.setOnClickListener(v -> groupPresenter.onClickItems());
            menu.setOnClickListener(v -> groupPresenter.onClickItemMenu());
        }
    }
}
