package com.example.dsm_calendar.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.ui.adapter.GroupRVAdapter;
import com.example.dsm_calendar.ui.dialog.GroupAddDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class GroupFragment extends Fragment {

    private RecyclerView recyclerView;
    private GroupRVAdapter adapter;
    private ArrayList<String> groups = new ArrayList<>();
    private GroupAddDialog groupAddDialog;

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
        recyclerView = rootView.findViewById(R.id.group_rv);
        adapter = new GroupRVAdapter(groups, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        groupAddDialog = new GroupAddDialog(getContext(), offButtonListener, checkButtonListener);

        FloatingActionButton fab_add = rootView.findViewById(R.id.group_fab);
        fab_add.setOnClickListener(v -> {
            groupAddDialog.show();
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private View.OnClickListener offButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            groupAddDialog.dismiss();
        }
    };

    private View.OnClickListener checkButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getContext(), "check", Toast.LENGTH_SHORT).show();
        }
    };
}
