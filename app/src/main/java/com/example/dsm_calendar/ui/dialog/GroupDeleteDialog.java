package com.example.dsm_calendar.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.WindowManager;

import androidx.annotation.NonNull;

public class GroupDeleteDialog extends Dialog {

    public GroupDeleteDialog(@NonNull Context context) {
        super(context);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;

        getWindow().setAttributes(layoutParams);
    }
}
