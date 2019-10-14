package com.example.dsm_calendar.ui.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.dsm_calendar.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class GroupMemberMenuDialog extends BottomSheetDialogFragment implements View.OnClickListener {

    private ImageButton offButton;
    private ConstraintLayout editMemberAuth;
    private ConstraintLayout kickMember;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_group_member_menu, container, false);

        offButton = view.findViewById(R.id.button_group_member_menu_off);
        editMemberAuth = view.findViewById(R.id.cl_group_member_menu_fix_member_auth);
        kickMember = view.findViewById(R.id.cl_group_member_menu_kick_member);

        offButton.setOnClickListener(this);
        editMemberAuth.setOnClickListener(this);
        kickMember.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_group_member_menu_off:
                dismiss();
                break;
            case R.id.cl_group_member_menu_fix_member_auth:
                Toast.makeText(getActivity(), "auth", Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.cl_group_member_menu_kick_member:
                Toast.makeText(getActivity(), "kick", Toast.LENGTH_SHORT).show();
                dismiss();
                break;
        }
    }
}
