package com.example.dsm_calendar;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ScheduleRVAdapter extends RecyclerView.Adapter<ScheduleRVAdapter.ScheduleViewHolder> {

    private ArrayList<SampleSchedule> list;
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    private Context context;

    public ScheduleRVAdapter(Context context, ArrayList<SampleSchedule> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_schedule, parent, false);
        ScheduleViewHolder holder = new ScheduleViewHolder(item);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {

        SampleSchedule schedule = list.get(position);

        holder.bind(schedule);

        holder.itemView.setOnClickListener( v -> {
            boolean expanded = schedule.getExpended();
            schedule.setExpended(!expanded);
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ScheduleViewHolder extends RecyclerView.ViewHolder{
        TextView tv_title;
        TextView tv_date;
        TextView tv_content;
        LinearLayout item;
        LinearLayout content;

        ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_schedule_title);
            tv_date = itemView.findViewById(R.id.tv_schedule_date);
            tv_content = itemView.findViewById(R.id.tv_schedule_content);
            item = itemView.findViewById(R.id.schedule_rv_item);
            content = itemView.findViewById(R.id.content);
        }

        private void bind(SampleSchedule schedule) {
            boolean expended = schedule.getExpended();
            content.setVisibility(expended ? View.VISIBLE : View.GONE);

            tv_title.setText(schedule.title);
            tv_date.setText(schedule.date);
            tv_content.setText(schedule.content);
        }
    }
}
