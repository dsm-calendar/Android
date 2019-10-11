package com.example.dsm_calendar.util;

import com.prolificinteractive.materialcalendarview.CalendarDay;

public interface DialogListener {

    interface GroupAddDialogListener{
        void onConfirmClicked(String name);
    }

    interface GroupInviteDialogListener{
        void onYesClicked();
        void onNoClicked();
    }

    interface MessageDeleteDialogListener{
        void onYesClicked();
        void onNoClicked();
    }

    interface ScheduleAddDialogListener{
        void onClickConfirm(String title, String date, String content, CalendarDay day);
    }
}
