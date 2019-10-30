package com.example.dsm_calendar.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.util.DialogListener;

public class GroupMemberAddDialog extends Dialog implements View.OnClickListener{

    private ImageButton offButton;
    private EditText editText;
    private Button invite;
    private Button cancel;

    private DialogListener.AddGroupMemberDialogListener listener;

    public GroupMemberAddDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;

        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.dialog_addgroupmember);

        offButton = findViewById(R.id.button_addgroupmember_off);
        editText = findViewById(R.id.et_addgroupmember);
        invite = findViewById(R.id.button_addgroupmember_invite);
        cancel = findViewById(R.id.button_addgroupmember_cancel);

        offButton.setOnClickListener(this);
        invite.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_addgroupmember_off:
            case R.id.button_addgroupmember_cancel:
                dismiss();
                break;
            case R.id.button_addgroupmember_invite:
                listener.onInviteClicked(editText.getText().toString());
                break;
        }
    }

    public void setAddGroupMemberDialogListener(DialogListener.AddGroupMemberDialogListener listener){
        this.listener = listener;
    }
}
