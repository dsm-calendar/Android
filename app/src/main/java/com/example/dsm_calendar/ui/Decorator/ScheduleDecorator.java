package com.example.dsm_calendar.ui.Decorator;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.data.DTO.Schedule;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.TreeSet;

public class ScheduleDecorator implements DayViewDecorator {

    private Queue<Integer> countQueue = new ArrayDeque<>();
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

        if (count > 0) {
            countQueue.add(count);
            return true;
        }
        return false;
    }

    @Override
    public void decorate(DayViewFacade view) {
//        Integer boxCount = countQueue.poll();
//        int count = boxCount;
//        int color = Color.BLACK;
//
//        if (count <= 3) {
//            color = (0x29 - (0xD * count) * 10000) +
//                    (0x6D - (0x24 * count) * 100) + (0x98 - (0x32 * count));
//        }

        Drawable d = ContextCompat.getDrawable(context, R.drawable.view_round_blue_background);
//        d.setTint(color);
        view.setBackgroundDrawable(d);
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
