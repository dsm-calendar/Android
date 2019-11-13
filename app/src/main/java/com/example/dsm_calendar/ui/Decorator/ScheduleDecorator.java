package com.example.dsm_calendar.ui.Decorator;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.dsm_calendar.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.TreeSet;

public class ScheduleDecorator implements DayViewDecorator {

    private TreeSet<Period> list;
    private Context context;

    public ScheduleDecorator(TreeSet<Period> list, Context context) {
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

        for (Period period : list) {
            if (period.getStart().isAfter(day))
                break;

            if (period.contain(day))
                ++ret;
        }

        return ret;
    }
}
