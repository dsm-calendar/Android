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
import com.example.dsm_calendar.data.SampleStudent;

import java.util.ArrayList;

public class GroupMemberRVAdapter extends RecyclerView.Adapter<GroupMemberRVAdapter.GroupMemberViewHolder> {

    private Context context;
    //TODO: GroupMemberPresenter
    public ArrayList<SampleStudent> students = new ArrayList<>();

    public GroupMemberRVAdapter(Context context){
        this.context = context;
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
        SampleStudent student = students.get(position);
        holder.bind(student);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    class GroupMemberViewHolder extends RecyclerView.ViewHolder {

        ImageView profile;
        TextView std_no;
        TextView name;
        ImageButton detail;

        public GroupMemberViewHolder(@NonNull View itemView) {
            super(itemView);

            profile = itemView.findViewById(R.id.iv_item_group_member);
            std_no = itemView.findViewById(R.id.tv_item_group_member_std_no);
            name = itemView.findViewById(R.id.tv_item_group_member_name);
            detail = itemView.findViewById(R.id.button_item_group_member_detail);
        }

        public void bind(SampleStudent student){
            std_no.setText(student.getStd_no());
            name.setText(student.getName());

            //TODO: setOnClickListener to detail
        }
    }
}
