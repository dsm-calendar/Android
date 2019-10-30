package com.example.dsm_calendar.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.contract.GroupMemberContract;
import com.example.dsm_calendar.data.DTO.Student;
import com.example.dsm_calendar.data.GroupMemberRepository;
import com.example.dsm_calendar.presenter.GroupMemberPresenter;
import com.example.dsm_calendar.ui.adapter.GroupMemberRVAdapter;
import com.example.dsm_calendar.ui.dialog.GroupMemberAddDialog;
import com.example.dsm_calendar.ui.dialog.GroupMemberAuthDialog;
import com.example.dsm_calendar.ui.dialog.GroupMemberKickDialog;
import com.example.dsm_calendar.ui.dialog.GroupMemberMenuDialog;
import com.example.dsm_calendar.util.DialogListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class GroupMemberActivity extends AppCompatActivity implements GroupMemberContract.View {

    private TextView noListTextView;
    private ImageButton groupMemberBack;
    private RecyclerView rvMember;
    private GroupMemberRVAdapter adapter;
    private FloatingActionButton addMemberButton;
    private GroupMemberMenuDialog groupMemberMenuDialog;
    private GroupMemberAddDialog groupMemberAddDialog;
    private GroupMemberAuthDialog groupMemberAuthDialog;
    private GroupMemberKickDialog groupMemberKickDialog;

    private GroupMemberPresenter presenter = new GroupMemberPresenter(this, new GroupMemberRepository());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_member);

        noListTextView = findViewById(R.id.tv_no_list_group_member);
        groupMemberBack = findViewById(R.id.button_group_member_back);
        rvMember = findViewById(R.id.rv_group_member);
        addMemberButton = findViewById(R.id.fab_group_member_actionButton);

        adapter = new GroupMemberRVAdapter(this, presenter);
        rvMember.setAdapter(adapter);
        rvMember.setLayoutManager(new LinearLayoutManager(this));

        groupMemberAddDialog = new GroupMemberAddDialog(this);
        groupMemberAddDialog.setAddGroupMemberDialogListener(ID -> presenter.onInviteClicked(ID));

        groupMemberAuthDialog = new GroupMemberAuthDialog(this);
        groupMemberAuthDialog.setGroupMemberAuthDialogListener(authCode -> presenter.onMemberAuthChanged(authCode));

        groupMemberKickDialog = new GroupMemberKickDialog(this);
        groupMemberKickDialog.setGroupMemberKickDialogListener(new DialogListener.GroupMemberKickDialogListener() {
            @Override
            public void onYesClicked() {
                presenter.onMemberKickClicked();
            }

            @Override
            public void onNoClicked() {
                groupMemberKickDialog.dismiss();
            }
        });

        groupMemberBack.setOnClickListener(v -> finish());
        addMemberButton.setOnClickListener(v -> groupMemberAddDialog.show());

        groupMemberMenuDialog = new GroupMemberMenuDialog();
        groupMemberMenuDialog.setListener(new DialogListener.GroupMemberMenuDialogListener() {
            @Override
            public void onClickMemberAuth() {
                groupMemberAuthDialog.show();
            }

            @Override
            public void onClickMemberKick() {
                groupMemberKickDialog.show();
            }
        });

        presenter.onStarted();
        checkList();
    }

    private void checkList() {
        if (adapter.students.size() == 0) {
            noListTextView.setVisibility(View.VISIBLE);
        } else {
            noListTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showGroupMemberDetailDialog() {
        groupMemberMenuDialog.show(getSupportFragmentManager(), "group Member setting");
    }

    @Override
    public void addItems(ArrayList<Student> students) {
        adapter.students = students;
    }

    @Override
    public void dismissInviteDialog() {
        groupMemberAddDialog.dismiss();
    }

    @Override
    public void dismissGroupMemberAuthDialog() {
        groupMemberAuthDialog.dismiss();
    }

    @Override
    public void showMessageForInviteSuccess() {
        Toast.makeText(this, "invited!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessageForInviteFail(String message) {
        Toast.makeText(this, "invite fail\nmessage: " + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageForAuthChangeSuccess(int authCode) {
        String auth = null;
        switch (authCode) {
            case 1:
                auth = "admin";
                break;
            case 2:
                auth = "writer";
                break;
            case 3:
                auth = "reader";
                break;
        }
        Toast.makeText(this, "user authorization: " + auth, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageForAuthChangeFail(String message) {
        Toast.makeText(this, "change fail\nmessage: " + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageForKickSuccess() {
        Toast.makeText(this, "kick success", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageForKickFail(String message) {
        Toast.makeText(this, "kick failed\nmessage: " + message, Toast.LENGTH_LONG).show();
    }
}
