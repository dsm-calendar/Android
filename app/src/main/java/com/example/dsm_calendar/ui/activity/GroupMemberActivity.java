package com.example.dsm_calendar.ui.activity;

import android.content.Intent;
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
import com.example.dsm_calendar.data.DTO.RoomMember;
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

    private GroupMemberPresenter presenter = new GroupMemberPresenter(this, new GroupMemberRepository(this));
    private int roomId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_member);

        Intent intent = getIntent();
        roomId = intent.getIntExtra("roomId", -1);

        noListTextView = findViewById(R.id.tv_no_list_group_member);
        groupMemberBack = findViewById(R.id.button_group_member_back);
        rvMember = findViewById(R.id.rv_group_member);
        addMemberButton = findViewById(R.id.fab_group_member_actionButton);

        adapter = new GroupMemberRVAdapter(this, presenter);
        rvMember.setAdapter(adapter);
        rvMember.setLayoutManager(new LinearLayoutManager(this));

        groupMemberAddDialog = new GroupMemberAddDialog(this);
        groupMemberAddDialog.setAddGroupMemberDialogListener(ID -> presenter.onInviteClicked(ID, roomId));

        groupMemberAuthDialog = new GroupMemberAuthDialog(this);
        groupMemberAuthDialog.setGroupMemberAuthDialogListener((authCode, member) -> presenter.onMemberAuthChanged(authCode, member.getMemberId(), member));

        groupMemberKickDialog = new GroupMemberKickDialog(this);
        groupMemberKickDialog.setGroupMemberKickDialogListener(new DialogListener.GroupMemberKickDialogListener() {
            @Override
            public void onYesClicked(RoomMember member, int index) {
                presenter.onMemberKickClicked(roomId, member, index);
                groupMemberKickDialog.dismiss();
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
            public void onClickMemberAuth(RoomMember member) {
                groupMemberAuthDialog.setMemberId(member);
                groupMemberAuthDialog.show();
            }

            @Override
            public void onClickMemberKick(RoomMember member, int position) {
                groupMemberKickDialog.setMemberInfo(member, position);
                groupMemberKickDialog.show();
            }
        });

        presenter.onStarted(roomId);
        checkList();
    }

    private void checkList() {
        if (adapter.members.size() == 0) {
            noListTextView.setVisibility(View.VISIBLE);
        } else {
            noListTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showGroupMemberDetailDialog(RoomMember member, int position) {
        groupMemberMenuDialog.setMemberInfo(member, position);
        groupMemberMenuDialog.show(getSupportFragmentManager(), "group Member setting");
    }

    @Override
    public void addItems(ArrayList<RoomMember> members) {
        adapter.members = members;
        adapter.notifyDataSetChanged();
        checkList();
    }

    @Override
    public void deleteItem(int index) {
        adapter.members.remove(index);
        adapter.notifyItemRemoved(index);
        adapter.notifyItemRangeChanged(index, adapter.getItemCount());
        checkList();
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

    @Override
    public void showMessageForGetMembersFail(String message) {
        Toast.makeText(this, "loading failed\nmessage: " + message, Toast.LENGTH_LONG).show();
    }
}
