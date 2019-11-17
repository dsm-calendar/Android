package com.example.dsm_calendar.ui.viewHolder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.data.DTO.Schedule;

public class ScheduleViewHolder extends RecyclerView.ViewHolder{

    private TextView tv_title;
    private TextView tv_date;
    private TextView tv_content;
    private LinearLayout item;
    private LinearLayout content;
    private ImageButton delete;

    private View.OnClickListener listener;

    public ScheduleViewHolder(@NonNull View itemView, View.OnClickListener listener) {
        super(itemView);

        tv_title = itemView.findViewById(R.id.tv_schedule_title);
        tv_date = itemView.findViewById(R.id.tv_schedule_date);
        tv_content = itemView.findViewById(R.id.tv_schedule_content);
        item = itemView.findViewById(R.id.schedule_rv_item);
        content = itemView.findViewById(R.id.content);
        delete = itemView.findViewById(R.id.button_my_schedule_delete);

        this.listener = listener;
    }

    public void bind(Schedule schedule) {
        boolean expended = schedule.getExpended();
        content.setVisibility(expended ? View.VISIBLE : View.GONE);
        delete.setVisibility(expended ? View.VISIBLE : View.GONE);

        tv_title.setText(schedule.getScheduleTitle());
        tv_date.setText(String.format("%s ~ %s", schedule.getStartDate(), schedule.getEndDate()));
        tv_content.setText(schedule.getScheduleContent());
        delete.setOnClickListener(listener);
    }
}
