package com.example.dsm_calendar.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.util.DialogListener;

public class GroupNameEditDialog extends Dialog implements View.OnClickListener{

    private ImageButton offButton;
    private EditText editText;
    private ImageButton checkButton;
    private DialogListener.GroupNameEditDialogListener listener;

    public GroupNameEditDialog(@NonNull Context context) {
        super(context);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;

        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.dialog_groupname_edit);

        offButton = findViewById(R.id.button_groupname_edit_off);
        editText = findViewById(R.id.et_groupname_edit_name);
        checkButton = findViewById(R.id.button_groupname_edit_confirm);

        offButton.setOnClickListener(this);
        checkButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_groupname_edit_off:
                dismiss();
                break;
            case R.id.button_groupname_edit_confirm:
                listener.onConfirmClicked(editText.getText().toString());
                editText.setText("");
                dismiss();
                break;
        }
    }

    public void setGroupNameEditListener(DialogListener.GroupNameEditDialogListener listener){
        this.listener = listener;
    }
}
