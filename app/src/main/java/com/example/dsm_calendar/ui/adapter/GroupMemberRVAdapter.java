package com.example.dsm_calendar.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.data.DTO.RoomMember;
import com.example.dsm_calendar.data.DTO.Student;
import com.example.dsm_calendar.presenter.GroupMemberPresenter;

import java.util.ArrayList;

public class GroupMemberRVAdapter extends RecyclerView.Adapter<GroupMemberRVAdapter.GroupMemberViewHolder> {

    private Context context;
    private GroupMemberPresenter presenter;
    public ArrayList<RoomMember> members = new ArrayList<>();

    public GroupMemberRVAdapter(Context context, GroupMemberPresenter presenter){
        this.context = context;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public GroupMemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_member, parent, false);
        GroupMemberViewHolder holder = new GroupMemberViewHolder(item);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupMemberViewHolder holder, int position) {
        RoomMember member = members.get(position);
        holder.bind(member, position);
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    class GroupMemberViewHolder extends RecyclerView.ViewHolder {

        ImageView profile;
        TextView name;
        TextView auth;
        ImageButton detail;

        public GroupMemberViewHolder(@NonNull View itemView) {
            super(itemView);

            profile = itemView.findViewById(R.id.iv_item_group_member);
            name = itemView.findViewById(R.id.tv_item_group_member_name);
            auth = itemView.findViewById(R.id.tv_item_group_member_auth);
            detail = itemView.findViewById(R.id.button_item_group_member_detail);
        }

        public void bind(RoomMember member, int position){
            String memberAuth = "";
            switch (member.getMemberRight()){
                case 1: memberAuth = "읽기 권한"; break;
                case 2: memberAuth = "쓰기 권한"; break;
                case 3: memberAuth = "관리자 권한"; break;
            }
            auth.setText(memberAuth);
            name.setText(member.getUserId());
            detail.setOnClickListener(v -> presenter.onClickDetail(member, position));
        }
    }
}
