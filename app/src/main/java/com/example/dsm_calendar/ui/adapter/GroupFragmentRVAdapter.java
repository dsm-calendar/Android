package com.example.dsm_calendar.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.data.DTO.Room;
import com.example.dsm_calendar.presenter.GroupFragmentPresenter;

import java.util.ArrayList;

public class GroupFragmentRVAdapter extends RecyclerView.Adapter<GroupFragmentRVAdapter.GroupViewHolder> {

    public ArrayList<Room> groupList = new ArrayList<>();
    private Context context;
    private GroupFragmentPresenter groupFragmentPresenter;

    public GroupFragmentRVAdapter(Context context, GroupFragmentPresenter groupFragmentPresenter){
        this.context = context;
        this.groupFragmentPresenter = groupFragmentPresenter;
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
        Room room = groupList.get(position);
        holder.bind(room, position);
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    class GroupViewHolder extends RecyclerView.ViewHolder {
        TextView tv_group_name ;
        ImageButton menu;

        GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_group_name = itemView.findViewById(R.id.tv_item_group_name);
            menu = itemView.findViewById(R.id.button_item_group_menu);
        }

        private void bind(Room room, int position){
            tv_group_name.setText(room.getRoomTitle());
            itemView.setOnClickListener(v -> groupFragmentPresenter.onClickItems(room));
            menu.setOnClickListener(v -> groupFragmentPresenter.onClickItemMenu(room, position));
        }
    }
}
