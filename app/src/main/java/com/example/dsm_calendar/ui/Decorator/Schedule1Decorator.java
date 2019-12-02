package com.example.dsm_calendar.ui.Decorator;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.dsm_calendar.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.ArrayList;
import java.util.Collection;

public class Schedule1Decorator implements DayViewDecorator {

    private ArrayList<CalendarDay> list;
    private Context context;

    public Schedule1Decorator(Collection<CalendarDay> list, Context context) {
        this.list = new ArrayList<>(list);
        this.context = context;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        for (CalendarDay unit : list){
            if (day == unit){
                return true;
            }
        }
        return false;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.view_round_blue_background));
    }
}
