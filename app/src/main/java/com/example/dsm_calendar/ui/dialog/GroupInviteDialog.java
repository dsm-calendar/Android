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
import com.example.dsm_calendar.util.GroupInviteDialogListener;

public class GroupInviteDialog extends Dialog implements View.OnClickListener {

    private ImageButton offButton;
    private Button yesButton;
    private Button noButton;
    private GroupInviteDialogListener listener;

    public GroupInviteDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;

        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.dialog_invitegroup);
        offButton = findViewById(R.id.button_invitegroup_off);
        yesButton = findViewById(R.id.button_invitegroup_yes);
        noButton = findViewById(R.id.button_invitegroup_no);

        offButton.setOnClickListener(this);
        yesButton.setOnClickListener(this);
        noButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_invitegroup_off:
                dismiss();
                break;
            case R.id.button_invitegroup_yes:
                listener.onYesClicked();
                dismiss();
                break;
            case R.id.button_invitegroup_no:
                listener.onNoClicked();
                dismiss();
                break;
        }
    }

    public void setInviteDialogListener(GroupInviteDialogListener listener){
        this.listener = listener;
    }
}
