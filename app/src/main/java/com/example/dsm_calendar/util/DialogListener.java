package com.example.dsm_calendar.util;

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
        void onYesClicked();

        void onNoClicked();
    }

    interface GroupInviteDialogListener {
        void onYesClicked();

        void onNoClicked();
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
        void onYesClicked();

        void onNoClicked();
    }

    interface SelectDateDialogListener {
        void onClickConfirm();
    }

    interface GroupMenuDialogListener {
        void onClickEditGroupTitle();

        void onClickDeleteGroup();
    }

    interface GroupMemberMenuDialogListener {
        void onClickMemberAuth();

        void onClickMemberKick();
    }

    interface LogoutDialogListener {
        void onConfirmClicked();
    }
}
