package com.example.dsm_calendar.ui.Decorator;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.dsm_calendar.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.ArrayList;
import java.util.Collection;

public class ScheduleDecorator implements DayViewDecorator {

    private ArrayList<CalendarDay> list;
    private Context context;

    public ScheduleDecorator(Collection<CalendarDay> list, Context context) {
        this.list = new ArrayList<>(list);
        this.context = context;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        for (CalendarDay unit : list){
            if (unit == day)
                return true;
        }
        return false;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.view_dot_background));
    }
}
