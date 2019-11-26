package com.example.dsm_calendar.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.util.DialogListener;

public class GroupMemberKickDialog extends Dialog implements View.OnClickListener{

    private ImageButton offButton;
    private Button yesButton;
    private Button noButton;
    private DialogListener.GroupMemberKickDialogListener listener;

    private int memberId;
    private int position;

    public GroupMemberKickDialog(@NonNull Context context) {
        super(context);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;

        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.dialog_kickmember);
        setCanceledOnTouchOutside(true);

        offButton = findViewById(R.id.button_kickgroupmember_off);
        yesButton = findViewById(R.id.button_kickgroupmember_yes);
        noButton = findViewById(R.id.button_kickgroupmember_no);

        offButton.setOnClickListener(this);
        yesButton.setOnClickListener(this);
        noButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_kickgroupmember_off:
                dismiss();
                break;
            case R.id.button_kickgroupmember_yes:
                listener.onYesClicked(memberId, position);
                break;
            case R.id.button_kickgroupmember_no:
                listener.onNoClicked();
                break;
        }
    }

    public void setGroupMemberKickDialogListener(DialogListener.GroupMemberKickDialogListener listener){
        this.listener = listener;
    }

    public void setMemberInfo(int memberId, int position){
        this.memberId = memberId;
        this.position = position;
    }
}
