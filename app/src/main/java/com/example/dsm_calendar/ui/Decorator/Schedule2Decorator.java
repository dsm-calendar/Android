package com.example.dsm_calendar.ui.Decorator;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.data.DTO.Schedule;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class Schedule2Decorator implements DayViewDecorator {

    private ArrayList<Schedule> list;
    private Context context;

    public Schedule2Decorator(Collection<Schedule> list, Context context) {
        this.list = new ArrayList<>(list);
        this.context = context;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        for (Schedule schedule : list){
            if (day.isInRange(schedule.getStartDay(), schedule.getEndDay())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.view_round_darkblue_background));
    }
}
