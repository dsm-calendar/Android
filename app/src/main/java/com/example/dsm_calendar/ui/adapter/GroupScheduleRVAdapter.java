package com.example.dsm_calendar.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.data.DTO.Schedule;
import com.example.dsm_calendar.data.Singleton.UserPreference;
import com.example.dsm_calendar.presenter.GroupSchedulePresenter;
import com.example.dsm_calendar.ui.viewHolder.ScheduleViewHolder;

import java.util.ArrayList;

public class GroupScheduleRVAdapter extends RecyclerView.Adapter<ScheduleViewHolder> {

    public ArrayList<Schedule> schedules = new ArrayList<>();
    private int position;
    private GroupSchedulePresenter presenter;
    private Context context;

    public GroupScheduleRVAdapter(GroupSchedulePresenter presenter, Context context){
        this.presenter = presenter;
        this.context = context;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_schedule, parent, false);
        ScheduleViewHolder scheduleViewHolder = new ScheduleViewHolder(
                view, UserPreference.getInstance(context).getIsAdmin(), v -> presenter.onItemDeleteClicked(schedules.get(position).getScheduleId(), position));
        return scheduleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        this.position = position;
        Schedule schedule = schedules.get(position);
        holder.bind(schedule);

        holder.itemView.setOnClickListener( v -> {
            boolean expanded = schedule.getExpended();
            schedule.setExpended(!expanded);
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }
}
