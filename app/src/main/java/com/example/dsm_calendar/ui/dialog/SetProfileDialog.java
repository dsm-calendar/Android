package com.example.dsm_calendar.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.util.DialogListener;


public class SetProfileDialog extends Dialog implements View.OnClickListener {

    private ImageButton offButton;
    private ImageView selected;
    private ImageButton sprout;
    private ImageButton woman;
    private ImageButton man;
    private ImageButton school;
    private ImageButton confirm;

    private int selectedIcon;
    private DialogListener.SetProfileDialogListener listener;

    public SetProfileDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;

        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.dialog_setprofile);

        offButton = findViewById(R.id.button_setprofile_off);
        selected = findViewById(R.id.iv_selectedImage);
        sprout = findViewById(R.id.button_ic_sprout);
        woman = findViewById(R.id.button_ic_person_w);
        man = findViewById(R.id.button_ic_person_m);
        school = findViewById(R.id.button_ic_school);
        confirm = findViewById(R.id.button_setprofile_confirm);

        offButton.setOnClickListener(this);
        sprout.setOnClickListener(this);
        woman.setOnClickListener(this);
        man.setOnClickListener(this);
        school.setOnClickListener(this);
        confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_setprofile_off:
                dismiss();
                break;
            case R.id.button_ic_sprout:
                selectedIcon = 0;
                selected.setImageResource(R.drawable.ic_sprout);
                break;
            case R.id.button_ic_person_w:
                selectedIcon = 1;
                selected.setImageResource(R.drawable.ic_person_w);
                break;
            case R.id.button_ic_person_m:
                selectedIcon = 2;
                selected.setImageResource(R.drawable.ic_person_m);
                break;
            case R.id.button_ic_school:
                selectedIcon = 3;
                selected.setImageResource(R.drawable.ic_school);
                break;
            case R.id.button_setprofile_confirm:
                listener.onConfirmClicked(selectedIcon);
                break;
        }
    }

    public void setSetProfileDialogListener(DialogListener.SetProfileDialogListener listener){
        this.listener = listener;
    }
}
