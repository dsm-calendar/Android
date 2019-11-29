package com.example.dsm_calendar.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.data.DTO.Schedule;
import com.example.dsm_calendar.presenter.SchoolSchedulePresenter;
import com.example.dsm_calendar.ui.viewHolder.ScheduleViewHolder;

import java.util.ArrayList;

public class SchoolScheduleAdapter extends RecyclerView.Adapter<ScheduleViewHolder> {

    public ArrayList<Schedule> scheduleList = new ArrayList<>();
    private SchoolSchedulePresenter presenter;
    private int position;
    private boolean isAdmin;

    public SchoolScheduleAdapter(SchoolSchedulePresenter presenter, boolean isAdmin){
        this.presenter = presenter;
        this.isAdmin = isAdmin;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_schedule, parent, false);
        ScheduleViewHolder viewHolder = new ScheduleViewHolder(view, isAdmin,  v -> presenter.onDeleteClicked(position));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        this.position = position;
        Schedule schedule = scheduleList.get(position);
        holder.bind(schedule);

        holder.itemView.setOnClickListener( v -> {
            boolean expanded = schedule.getExpended();
            schedule.setExpended(!expanded);
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }
}
