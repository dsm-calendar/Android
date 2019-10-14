package com.example.dsm_calendar.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.util.DialogListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class GroupMenuDialog extends BottomSheetDialogFragment implements View.OnClickListener {

    private ImageButton offButton;
    private ConstraintLayout editGroupName;
    private ConstraintLayout deleteGroup;
    private DialogListener.GroupMenuDialogListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_group_menu, container, false);

        offButton = view.findViewById(R.id.button_group_menu_off);
        editGroupName = view.findViewById(R.id.cl_group_menu_fix_group_title);
        deleteGroup = view.findViewById(R.id.cl_group_menu_delete_group);

        offButton.setOnClickListener(this);
        editGroupName.setOnClickListener(this);
        deleteGroup.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_group_menu_off:
                dismiss();
                break;
            case R.id.cl_group_menu_fix_group_title:
                listener.onClickEditGroupTitle();
                dismiss();
                break;
            case R.id.cl_group_menu_delete_group:
                listener.onClickDeleteGroup();
                dismiss();
                break;
        }
    }

    public void setGroupMenuDialogListener(DialogListener.GroupMenuDialogListener listener){
        this.listener = listener;
    }
}
