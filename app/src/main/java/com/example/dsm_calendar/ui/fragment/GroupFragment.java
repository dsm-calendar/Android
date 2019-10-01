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

        groups.add("동휘와 함께하는 게임 만들기");
        groups.add("윤성이와 함께하는 디자인");
        groups.add("승민이와 함께하는 안드로이드");
        groups.add("하경이와 함께하는 서버만들기");
        groups.add("담임쌤과 함께하는 \"코아\"개념 배우기");
        groups.add("희명이와 함께하는 탈모갤러리");
        groups.add("민트니스가 함께하는 근성장 팩토리");
        groups.add("채홍이와 함께하는 인생파탄내기");
        groups.add("이제는 쓸게없어 막쓰는 그룹");
        groups.add("hello world!");
        groups.add("Tlqkf");

        View rootView = inflater.inflate(R.layout.fragment_group, container, false);
        recyclerView = rootView.findViewById(R.id.rv_group_view);
        adapter = new GroupRVAdapter(groups, getActivity());
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
}
