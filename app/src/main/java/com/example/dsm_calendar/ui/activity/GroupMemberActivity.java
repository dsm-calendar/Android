package com.example.dsm_calendar.ui.activity;

import android.os.Bundle;
import android.text.Layout;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.contract.GroupMemberContract;
import com.example.dsm_calendar.ui.adapter.GroupMemberRVAdapter;

public class GroupMemberActivity extends AppCompatActivity implements GroupMemberContract.View {

    private ImageButton groupMemberBack;
    private RecyclerView rvMember;
    private GroupMemberRVAdapter adapter;
    private ConstraintLayout addMemberButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_member);

        groupMemberBack = findViewById(R.id.button_group_member_back);
        rvMember = findViewById(R.id.rv_group_member);
        addMemberButton = findViewById(R.id.cl_group_member_add);

        adapter = new GroupMemberRVAdapter(this);
        rvMember.setAdapter(adapter);
        rvMember.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showGroupMemberDetailDialog() {
        //TODO: make and show dialog
    }
}
