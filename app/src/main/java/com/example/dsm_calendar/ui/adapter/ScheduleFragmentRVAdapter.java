package com.example.dsm_calendar.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.data.Schedule;
import com.example.dsm_calendar.presenter.ScheduleFragmentPresenter;
import com.example.dsm_calendar.ui.viewHolder.ScheduleViewHolder;

import java.util.ArrayList;

public class ScheduleFragmentRVAdapter extends RecyclerView.Adapter<ScheduleViewHolder> {

    public ArrayList<Schedule> list = new ArrayList<>();
    private Context context;
    private ScheduleFragmentPresenter scheduleFragmentPresenter;

    private int position;

    public ScheduleFragmentRVAdapter(Context context, ScheduleFragmentPresenter scheduleFragmentPresenter){
        this.context = context;
        this.scheduleFragmentPresenter = scheduleFragmentPresenter;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_schedule, parent, false);
        ScheduleViewHolder holder = new ScheduleViewHolder(item, v -> scheduleFragmentPresenter.onItemDeleteClicked(position));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        this.position = position;
        Schedule schedule = list.get(position);
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
}
