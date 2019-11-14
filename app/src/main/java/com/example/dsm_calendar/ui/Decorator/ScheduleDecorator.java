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
    private int count;

    public ScheduleDecorator(TreeSet<Schedule> list, Context context) {
        this.list = new TreeSet<>(list);
        this.context = context;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        count = getContainNum(day);
        return count > 0;
    }

    @Override
    public void decorate(DayViewFacade view) {
        switch (count){
            case 1: view.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.view_round_blue_background));
            break;
            case 2: view.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.view_round_darkblue_background));
            break;
//            case 3: view.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.view_round_darkblue_background));
//            break;
            default: view.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.view_dot_background));
            break;
        }
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
