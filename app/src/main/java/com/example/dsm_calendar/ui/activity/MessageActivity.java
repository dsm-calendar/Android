package com.example.dsm_calendar.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.contract.MessageContract;
import com.example.dsm_calendar.data.MessageRepository;
import com.example.dsm_calendar.presenter.MessagePresenter;
import com.example.dsm_calendar.ui.adapter.MessageRVAdapter;
import com.example.dsm_calendar.ui.dialog.GroupInviteDialog;
import com.example.dsm_calendar.ui.dialog.MessageDeleteDialog;
import com.example.dsm_calendar.util.GroupInviteDialogListener;
import com.example.dsm_calendar.util.MessageDeleteDialogListener;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity implements MessageContract.View {

    private RecyclerView recyclerView;
    private MessageRVAdapter adapter;
    private GroupInviteDialog groupInviteDialog;
    private MessageDeleteDialog messageDeleteDialog;
    private MessagePresenter messagePresenter = new MessagePresenter(this, new MessageRepository());

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

        groupInviteDialog = new GroupInviteDialog(this);
        groupInviteDialog.setInviteDialogListener(new GroupInviteDialogListener() {
            @Override
            public void onYesClicked() {
                Toast.makeText(groupInviteDialog.getContext(), "yes!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNoClicked() {
                Toast.makeText(groupInviteDialog.getContext(), "no....", Toast.LENGTH_SHORT).show();
            }
        });
        messageDeleteDialog = new MessageDeleteDialog(this);
        messageDeleteDialog.setMessageDeleteDialogListener(new MessageDeleteDialogListener() {
            @Override
            public void onYesClicked() {
                Toast.makeText(groupInviteDialog.getContext(), "yes!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNoClicked() {
                Toast.makeText(groupInviteDialog.getContext(), "no....", Toast.LENGTH_SHORT).show();
            }
        });
        groupInviteDialog.setCanceledOnTouchOutside(true);
        messageDeleteDialog.setCanceledOnTouchOutside(true);

        recyclerView = findViewById(R.id.rv_message_message);
        adapter = new MessageRVAdapter(this, messagePresenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        messagePresenter.onStarted();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showInviteDialog() {
        groupInviteDialog.show();
    }

    @Override
    public void showDeleteDialog() {
        messageDeleteDialog.show();
    }

    @Override
    public void addItems(ArrayList<String> testMessage, ArrayList<String> testDate) {
        adapter.messageList = testMessage;
        adapter.dateList = testDate;
    }
}
