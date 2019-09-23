package com.example.dsm_calendar.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;

import com.example.dsm_calendar.R;

public class GroupInviteDialog extends Dialog {

    private View.OnClickListener offButtonListener;
    private View.OnClickListener yesButtonListener;
    private View.OnClickListener noButtonListener;
    private ImageButton offButton;
    private Button yesButton;
    private Button noButton;

    public GroupInviteDialog(
            @NonNull Context context,
            View.OnClickListener offButtonListener,
            View.OnClickListener yesButtonListener,
            View.OnClickListener noButtonListener) {
        super(context);
        this.offButtonListener = offButtonListener;
        this.yesButtonListener = yesButtonListener;
        this.noButtonListener = noButtonListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;

        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.dialog_group_invite);
        offButton = findViewById(R.id.button_dialog_invite_group_off);
        yesButton = findViewById(R.id.button_dialog_invite_group_yes);
        noButton = findViewById(R.id.button_dialog_invite_group_no);

        offButton.setOnClickListener(offButtonListener);
        yesButton.setOnClickListener(yesButtonListener);
        noButton.setOnClickListener(noButtonListener);
    }
}
