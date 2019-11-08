package com.example.dsm_calendar.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.data.SampleSchedule;
import com.example.dsm_calendar.presenter.SchoolSchedulePresenter;
import com.example.dsm_calendar.ui.viewHolder.ScheduleViewHolder;

import java.util.ArrayList;

public class SchoolScheduleAdapter extends RecyclerView.Adapter<ScheduleViewHolder> {

    public ArrayList<SampleSchedule> scheduleList = new ArrayList<>();
    private SchoolSchedulePresenter presenter;
    private int position;

    public SchoolScheduleAdapter(SchoolSchedulePresenter presenter){
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_schedule, parent, false);
        ScheduleViewHolder viewHolder = new ScheduleViewHolder(view, v -> presenter.onDeleteClicked());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        this.position = position;
        SampleSchedule schedule = scheduleList.get(position);
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
