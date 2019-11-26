package com.example.dsm_calendar.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.util.DialogListener;

public class GroupMemberAuthDialog extends Dialog implements View.OnClickListener {

    private ImageButton offButton;
    private ImageView imageView;
    private TextView textView;
    private TextView userAuth;
    private ImageButton check;

    private int authCode;
    private int memberId;
    private DialogListener.GroupMemberAuthDialogListener listener;

    public GroupMemberAuthDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;

        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.dialog_groupmemberauth);

        offButton = findViewById(R.id.button_groupmemberauth_off);
        imageView = findViewById(R.id.iv_selectedmember);
        textView = findViewById(R.id.tv_selectedmember);
        userAuth = findViewById(R.id.tv_userauth);
        check = findViewById(R.id.button_groupmemberauth_check);

        findViewById(R.id.button_groupmemberauth_admin).setOnClickListener(this);
        findViewById(R.id.button_groupmemberauth_writer).setOnClickListener(this);
        findViewById(R.id.button_groupmemberauth_reader).setOnClickListener(this);
        offButton.setOnClickListener(this);
        check.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_groupmemberauth_admin:
                authCode = 3;
                userAuth.setText("관리자 권한");
                break;
            case R.id.button_groupmemberauth_writer:
                authCode = 2;
                userAuth.setText("쓰기 권한");
                break;
            case R.id.button_groupmemberauth_reader:
                authCode = 1;
                userAuth.setText("읽기 권한");
                break;
            case R.id.button_groupmemberauth_off:
                dismiss();
                break;
            case R.id.button_groupmemberauth_check:
                listener.onClickCheck(authCode, memberId);
                break;
        }
    }

    public void setGroupMemberAuthDialogListener(DialogListener.GroupMemberAuthDialogListener listener){
        this.listener = listener;
    }

    public void setMemberId(int memberId){
        this.memberId = memberId;
    }
}
