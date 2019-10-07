package com.example.dsm_calendar.util;

public interface DialogListener {
    interface AccessCodeDialogListener{
        void onClickConfirm(String code);
    }

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
        void onClickConfirm(String title, String content);
    }
}
