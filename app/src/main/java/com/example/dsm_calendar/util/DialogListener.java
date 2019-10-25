package com.example.dsm_calendar.util;

import com.prolificinteractive.materialcalendarview.CalendarDay;

public interface DialogListener {

    interface SetProfileDialogListener {
        void onConfirmClicked(int iconIndex);
    }

    interface GroupAddDialogListener{
        void onConfirmClicked(String name);
    }

    interface GroupNameEditDialogListener{
        void onConfirmClicked(String name);
    }

    interface GroupDeleteDialogListener{
        void onYesClicked();
        void onNoClicked();
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

    interface GroupMenuDialogListener {
        void onClickEditGroupTitle();
        void onClickDeleteGroup();
    }

    interface GroupMemberMenuDialogListener{
        void onClickMemberAuth();
        void onClickMemberKick();
    }
}
