package com.example.dsm_calendar.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.contract.GroupFragmentContract;
import com.example.dsm_calendar.data.DTO.Room;
import com.example.dsm_calendar.data.GroupFragmentRepository;
import com.example.dsm_calendar.presenter.GroupFragmentPresenter;
import com.example.dsm_calendar.ui.activity.GroupSingleActivity;
import com.example.dsm_calendar.ui.adapter.GroupFragmentRVAdapter;
import com.example.dsm_calendar.ui.dialog.GroupAddDialog;
import com.example.dsm_calendar.ui.dialog.GroupDeleteDialog;
import com.example.dsm_calendar.ui.dialog.GroupMenuDialog;
import com.example.dsm_calendar.ui.dialog.GroupNameEditDialog;
import com.example.dsm_calendar.util.DialogListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class GroupFragment extends Fragment implements GroupFragmentContract.View {

    private TextView noListTextView;
    private RecyclerView recyclerView;
    private GroupFragmentRVAdapter adapter;
    private GroupAddDialog groupAddDialog;
    private GroupMenuDialog groupMenuDialog;
    private GroupNameEditDialog groupNameEditDialog;
    private GroupDeleteDialog groupDeleteDialog;
    private FloatingActionButton fab_add;
    private GroupFragmentPresenter groupFragmentPresenter = new GroupFragmentPresenter(this, new GroupFragmentRepository(getActivity()));

    public GroupFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_group, container, false);

        noListTextView = rootView.findViewById(R.id.tv_no_list_group);

        recyclerView = rootView.findViewById(R.id.rv_group_view);
        adapter = new GroupFragmentRVAdapter(getActivity(), groupFragmentPresenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0){
                    if (fab_add.isShown()){
                        fab_add.hide();
                    }
                } else if(dy < 0){
                    if(!fab_add.isShown()){
                        fab_add.show();
                    }
                }
            }
        });

        groupAddDialog = new GroupAddDialog(getActivity());
        groupAddDialog.setGroupAddDialogListener(name -> groupFragmentPresenter.onAddGroup(new Room(0, name, 0, 0)));

        groupMenuDialog = new GroupMenuDialog();
        groupMenuDialog.setGroupMenuDialogListener(new DialogListener.GroupMenuDialogListener() {
            @Override
            public void onClickEditGroupTitle() {
                groupNameEditDialog.show();
            }

            @Override
            public void onClickDeleteGroup() {
                groupDeleteDialog.show();
            }
        });

        groupNameEditDialog = new GroupNameEditDialog(getActivity());
        groupNameEditDialog.setGroupNameEditListener(new DialogListener.GroupNameEditDialogListener() {
            @Override
            public void onConfirmClicked(String name) {
                //TODO: get position of clicked item to rename
            }
        });

        groupDeleteDialog = new GroupDeleteDialog(getActivity());
        groupDeleteDialog.setGroupDeleteDialogListener(new DialogListener.GroupDeleteDialogListener() {
            @Override
            public void onYesClicked() {
                //TODO: get item position to delete or get out of group
            }

            @Override
            public void onNoClicked() {
                Toast.makeText(getActivity(), "그룹 삭제가 취소되었습니다", Toast.LENGTH_LONG).show();
            }
        });

        fab_add = rootView.findViewById(R.id.fab_group_actionButton);
        fab_add.setOnClickListener(v -> groupAddDialog.show());

        groupFragmentPresenter.onStarted();
        checkList();

        return rootView;
    }

    private void checkList(){
        if (adapter.groupList.size() == 0){
            noListTextView.setVisibility(View.VISIBLE);
        } else {
            noListTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void showGroupMenuDialog(Room room) {
        groupMenuDialog.setName(room.getRoomTitle());
        groupMenuDialog.show(getFragmentManager(), "bottomSheet");
    }

    @Override
    public void startGroupActivity(Room room) {
        Intent intent = new Intent(getActivity(), GroupSingleActivity.class);
        // TODO put room extra to intent
        startActivity(intent);
    }

    @Override
    public void showMessageForAddGroupSuccess() {
        Toast.makeText(getActivity(), "그룹이 성공적으로 만들어졌습니다", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageForGetGroupListFail(String message) {
        Toast.makeText(getActivity(), "error: " + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageForAddGroupFail(String message) {
        Toast.makeText(getActivity(), "error: " + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void addItems(ArrayList<Room> rooms) {
        adapter.groupList = rooms;
        adapter.notifyDataSetChanged();
    }
}
