package com.example.dsm_calendar.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.util.GroupAddDialogListener;

public class GroupAddDialog extends Dialog implements  View.OnClickListener{

    private EditText editText;
    private ImageView offButton;
    private ImageButton checkButton;
    private GroupAddDialogListener listener;

    public GroupAddDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;

        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.dialog_addgroup);

        editText = findViewById(R.id.et_addgroup_name);
        offButton = findViewById(R.id.button_addgroup_off);
        checkButton = findViewById(R.id.button_addgroup_confirm);

        offButton.setOnClickListener(this);
        checkButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_addgroup_off:
                dismiss();
                break;
            case R.id.button_addgroup_confirm:
                listener.onConfirmClicked(editText.getText().toString());
                editText.setText("");
                dismiss();
                break;
        }
    }

    public void setGroupAddDialogListener(GroupAddDialogListener listener){
        this.listener = listener;
    }
}
