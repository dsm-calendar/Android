package com.example.dsm_calendar.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.contract.GroupContract;
import com.example.dsm_calendar.data.GroupRepository;
import com.example.dsm_calendar.presenter.GroupPresenter;
import com.example.dsm_calendar.ui.adapter.GroupRVAdapter;
import com.example.dsm_calendar.ui.dialog.GroupAddDialog;
import com.example.dsm_calendar.util.GroupAddDialogListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class GroupFragment extends Fragment implements GroupContract.View {

    private RecyclerView recyclerView;
    private GroupRVAdapter adapter;
    private ArrayList<String> groups = new ArrayList<>();
    private GroupAddDialog groupAddDialog;
    private FloatingActionButton fab_add;
    private GroupPresenter groupPresenter = new GroupPresenter(this, new GroupRepository());

    public GroupFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_group, container, false);

        recyclerView = rootView.findViewById(R.id.rv_group_view);
        adapter = new GroupRVAdapter(getActivity());
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
        groupAddDialog.setGroupAddDialogListener(new GroupAddDialogListener() {
            @Override
            public void onConfirmClicked(String name) {
                Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
            }
        });
        groupAddDialog.setCanceledOnTouchOutside(true);

        fab_add = rootView.findViewById(R.id.fab_group_actionButton);
        fab_add.setOnClickListener(v -> groupPresenter.onClickAddGroup());

        groupPresenter.onStarted(groups);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void showGroupAddDialog() {
        groupAddDialog.show();
    }

    @Override
    public void addItems(ArrayList<String> testGroup) {
        adapter.groupList = testGroup;
    }
}
