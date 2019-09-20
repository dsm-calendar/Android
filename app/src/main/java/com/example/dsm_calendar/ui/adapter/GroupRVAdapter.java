package com.example.dsm_calendar.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.ui.fragment.GroupSingleFragment;

import java.util.ArrayList;

public class GroupRVAdapter extends RecyclerView.Adapter<GroupRVAdapter.GroupViewHolder> {

    private ArrayList<String> groupList;
    private Context context;

    public GroupRVAdapter(ArrayList<String> groups, Context context){
        this.groupList = groups;
        this.context = context;
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
        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_group_name = itemView.findViewById(R.id.tv_group_name);
            itemView.setOnClickListener(v -> {
                Toast.makeText(context, "item clicked", Toast.LENGTH_SHORT).show();
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_container, new GroupSingleFragment());
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.addToBackStack(null);
                transaction.commit();
            });
        }

        private void bind(String groupName){
            tv_group_name.setText(groupName);
        }
    }
}
