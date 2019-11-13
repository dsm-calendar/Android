package com.example.dsm_calendar.ui.Decorator;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.data.Schedule;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.TreeSet;

public class ScheduleDecorator implements DayViewDecorator {

    private TreeSet<Schedule> list;
    private Context context;

    public ScheduleDecorator(TreeSet<Schedule> list, Context context) {
        this.list = new TreeSet<>(list);
        this.context = context;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return getContainNum(day) > 0;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.view_dot_background));
    }

    private int getContainNum(CalendarDay day) {
        int ret = 0;

        for (Schedule schedule : list) {
            if (schedule.getStartDay().isAfter(day))
                break;

            if (schedule.contain(day))
                ++ret;
        }

        return ret;
    }
}
