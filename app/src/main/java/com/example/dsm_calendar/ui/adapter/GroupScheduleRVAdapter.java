package com.example.dsm_calendar.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.data.SampleSchedule;
import com.example.dsm_calendar.presenter.GroupSchedulePresenter;
import com.example.dsm_calendar.ui.viewHolder.ScheduleViewHolder;

import java.util.ArrayList;

public class GroupScheduleRVAdapter extends RecyclerView.Adapter<ScheduleViewHolder> {

    public ArrayList<SampleSchedule> schedules = new ArrayList<>();
    private int position;
    private GroupSchedulePresenter presenter;

    public GroupScheduleRVAdapter(GroupSchedulePresenter presenter){
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_schedule, parent, false);
        ScheduleViewHolder scheduleViewHolder = new ScheduleViewHolder(view, v -> { presenter.onItemDeleteClicked(position); });
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        this.position = position;
        SampleSchedule sampleSchedule = schedules.get(position);
        holder.bind(sampleSchedule);

        holder.itemView.setOnClickListener( v -> {
            boolean expanded = sampleSchedule.getExpended();
            sampleSchedule.setExpended(!expanded);
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }
}
