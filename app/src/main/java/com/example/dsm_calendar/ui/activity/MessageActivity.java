package com.example.dsm_calendar.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.contract.MessageContract;
import com.example.dsm_calendar.data.DTO.Message;
import com.example.dsm_calendar.data.MessageRepository;
import com.example.dsm_calendar.presenter.MessagePresenter;
import com.example.dsm_calendar.ui.adapter.MessageRVAdapter;
import com.example.dsm_calendar.ui.dialog.GroupInviteDialog;
import com.example.dsm_calendar.ui.dialog.MessageDeleteDialog;
import com.example.dsm_calendar.util.DialogListener;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity implements MessageContract.View {

    private TextView noListTextView;
    private RecyclerView recyclerView;
    private MessageRVAdapter adapter;
    private GroupInviteDialog groupInviteDialog;
    private MessageDeleteDialog messageDeleteDialog;
    private MessagePresenter messagePresenter = new MessagePresenter(this, new MessageRepository(this));

    private ArrayList<Message> messageList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Toolbar toolbar = findViewById(R.id.tb_message_message);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_cross_out);

        messagePresenter.onStarted();

        groupInviteDialog = new GroupInviteDialog(this);
        groupInviteDialog.setInviteDialogListener(new DialogListener.GroupInviteDialogListener() {
            @Override
            public void onYesClicked(int messageId) {
                Toast.makeText(groupInviteDialog.getContext(), "yes!", Toast.LENGTH_SHORT).show();
                messagePresenter.onAcceptInviteClicked(messageId);
            }

            @Override
            public void onNoClicked(int messageId) {
                Toast.makeText(groupInviteDialog.getContext(), "no....", Toast.LENGTH_SHORT).show();
                messagePresenter.onRejectInviteClicked(messageId);
            }
        });
        messageDeleteDialog = new MessageDeleteDialog(this);
        messageDeleteDialog.setMessageDeleteDialogListener(new DialogListener.MessageDeleteDialogListener() {
            @Override
            public void onYesClicked(int messageId) {
                Toast.makeText(groupInviteDialog.getContext(), "yes!", Toast.LENGTH_SHORT).show();
                messagePresenter.onDeleteMessageClicked(messageId);
            }

            @Override
            public void onNoClicked() {
                Toast.makeText(groupInviteDialog.getContext(), "no....", Toast.LENGTH_SHORT).show();
            }
        });

        noListTextView = findViewById(R.id.tv_no_list_message);

        recyclerView = findViewById(R.id.rv_message_message);
        adapter = new MessageRVAdapter(this, messagePresenter, messageList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        checkList();
    }

    void checkList(){
        if (adapter.getItemCount() == 0){
            noListTextView.setVisibility(View.VISIBLE);
        } else {
            noListTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showInviteDialog(int messageId) {
        groupInviteDialog.setMessageId(messageId);
        groupInviteDialog.show();
    }

    @Override
    public void showDeleteDialog(int messageId) {
        messageDeleteDialog.setMessageId(messageId);
        messageDeleteDialog.show();
    }

    @Override
    public void showMessageForAcceptInviteSuccess() {
        Toast.makeText(this, "Invite Accepted", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageForAcceptInviteFail(String message) {
        Toast.makeText(this, "Accept Fail\nmessage: " + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageForRejectInviteSuccess() {
        Toast.makeText(this, "Invite Rejected", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageForRejectInviteFail(String message) {
        Toast.makeText(this, "Reject Fail\nmessage: " + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageForDeleteSuccess() {
        Toast.makeText(this, "Message Deleted", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageForDeleteFail(String message) {
        Toast.makeText(this, "Delete Failed\nmessage: " + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageForLoadingFail(String message) {
        Toast.makeText(this, "Loading Failed\nmessage: " + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void addItems(ArrayList<Message> messageList) {
        this.messageList = messageList;
    }
}
