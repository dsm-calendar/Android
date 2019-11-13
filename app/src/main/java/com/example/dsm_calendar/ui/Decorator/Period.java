package com.example.dsm_calendar.ui.Decorator;

import com.prolificinteractive.materialcalendarview.CalendarDay;

public class Period implements Comparable<Period> {
    private CalendarDay start;
    private CalendarDay end;

    public Period(CalendarDay start, CalendarDay end) {
        this.start = start;
        this.end = end;
    }

    public CalendarDay getStart() {
        return start;
    }

    public CalendarDay getEnd() {
        return end;
    }

    public boolean contain(CalendarDay day) {
        return day.isInRange(start, end);
    }

    @Override
    public int compareTo(Period o) {
        return start.isBefore(o.start) ? -1 : start.isAfter(o.start) ? 1 : 0;
    }
}
