package com.example.dsm_calendar.util;

import com.example.dsm_calendar.data.DTO.Room;
import com.prolificinteractive.materialcalendarview.CalendarDay;

public interface DialogListener {

    interface SetProfileDialogListener {
        void onConfirmClicked(int iconIndex);
    }

    interface GroupAddDialogListener {
        void onConfirmClicked(String name);
    }

    interface GroupNameEditDialogListener {
        void onConfirmClicked(String name);
    }

    interface GroupDeleteDialogListener {
        void onYesClicked(int roomId, int position);
        void onNoClicked();
    }

    interface GroupInviteDialogListener {
        void onYesClicked(int messageId);
        void onNoClicked(int messageId);
    }

    interface AddGroupMemberDialogListener {
        void onInviteClicked(String ID);
    }

    interface GroupMemberAuthDialogListener {
        void onClickCheck(int authCode);
    }

    interface GroupMemberKickDialogListener {
        void onYesClicked();
        void onNoClicked();
    }

    interface MessageDeleteDialogListener {
        void onYesClicked(int messageId);
        void onNoClicked();
    }

    interface SelectDateDialogListener {
        void onClickConfirm(CalendarDay date);
    }

    interface GroupMenuDialogListener {
        void onClickEditGroupTitle();
        void onClickDeleteGroup(Room room, int position);
    }

    interface GroupMemberMenuDialogListener {
        void onClickMemberAuth();
        void onClickMemberKick();
    }

    interface LogoutDialogListener {
        void onConfirmClicked();
    }
}
