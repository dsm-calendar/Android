package com.example.dsm_calendar.ui.Decorator;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.data.DTO.Schedule;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Collection;
import java.util.HashSet;

public class Schedule1Decorator implements DayViewDecorator {

    private HashSet<Schedule> list;
    private Context context;

    public Schedule1Decorator(Collection<Schedule> list, Context context) {
        this.list = new HashSet<>(list);
        this.context = context;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return list.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.view_round_blue_background));
    }
}
