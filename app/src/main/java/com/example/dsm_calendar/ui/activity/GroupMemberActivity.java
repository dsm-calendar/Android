package com.example.dsm_calendar.ui.activity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.contract.GroupMemberContract;
import com.example.dsm_calendar.data.GroupMemberRepository;
import com.example.dsm_calendar.data.SampleStudent;
import com.example.dsm_calendar.presenter.GroupMemberPresenter;
import com.example.dsm_calendar.ui.adapter.GroupMemberRVAdapter;
import com.example.dsm_calendar.ui.dialog.GroupMemberMenuDialog;
import com.example.dsm_calendar.util.DialogListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class GroupMemberActivity extends AppCompatActivity implements GroupMemberContract.View {

    private ImageButton groupMemberBack;
    private RecyclerView rvMember;
    private GroupMemberRVAdapter adapter;
    private FloatingActionButton addMemberButton;
    private GroupMemberMenuDialog groupMemberMenuDialog;

    private GroupMemberPresenter presenter = new GroupMemberPresenter(this, new GroupMemberRepository());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_member);

        groupMemberBack = findViewById(R.id.button_group_member_back);
        rvMember = findViewById(R.id.rv_group_member);
        addMemberButton = findViewById(R.id.fab_group_member_actionButton);

        adapter = new GroupMemberRVAdapter(this, presenter);
        rvMember.setAdapter(adapter);
        rvMember.setLayoutManager(new LinearLayoutManager(this));

        groupMemberBack.setOnClickListener(v -> presenter.onClickBack());
        addMemberButton.setOnClickListener(v -> presenter.onClickAdd());

        groupMemberMenuDialog = new GroupMemberMenuDialog();
        groupMemberMenuDialog.setListener(new DialogListener.GroupMemberMenuDialogListener() {
            @Override
            public void onClickMemberAuth() {
                Toast.makeText(getApplicationContext(), "auth", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickMemberKick() {
                Toast.makeText(getApplicationContext(), "kick", Toast.LENGTH_SHORT).show();
            }
        });

        presenter.onStarted();
    }

    @Override
    public void showGroupMemberDetailDialog() {
        groupMemberMenuDialog.show(getSupportFragmentManager(), "group Member setting");
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void addItems(ArrayList<SampleStudent> students) {
        adapter.students = students;
    }
}
