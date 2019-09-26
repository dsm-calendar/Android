package com.example.dsm_calendar.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.dsm_calendar.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class GroupSingleFragment extends Fragment implements View.OnClickListener {

    private FloatingActionButton fab_main;
    private FloatingActionButton fab_setting;
    private FloatingActionButton fab_member;
    private Animation fab_open, fab_hide;
    private NestedScrollView nsv;
    private Boolean IsFabOpen = false;
    private TabLayout tabLayout;
    private ConstraintLayout groupToolbar;
    private ImageButton backButton;

    public GroupSingleFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //TODO: add fab to group single fragment
        View rootView = inflater.inflate(R.layout.fragment_group_single, container, false);

        tabLayout = getActivity().findViewById(R.id.main_tabBar);
        tabLayout.setVisibility(View.GONE);

        groupToolbar = getActivity().findViewById(R.id.group_toolbar);
        groupToolbar.setVisibility(View.VISIBLE);

        backButton = groupToolbar.findViewById(R.id.group_off);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().remove(GroupSingleFragment.this).commit();
                fragmentManager.popBackStack();
            }
        });

        fab_main = rootView.findViewById(R.id.fab_group_single_main);
        fab_setting = rootView.findViewById(R.id.fab_group_single_setting);
        fab_member = rootView.findViewById(R.id.fab_group_single_member);
        nsv = rootView.findViewById(R.id.nestedScrollView_group_single);
        fab_open = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_open);
        fab_hide = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_hide);

        fab_main.setOnClickListener(this);
        fab_setting.setOnClickListener(this);
        fab_member.setOnClickListener(this);

        nsv.setOnScrollChangeListener((View.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if(scrollY > oldScrollY){
                fab_main.hide();
            } else {
                fab_main.show();
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        groupToolbar.setVisibility(View.GONE);
        tabLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_group_single_main:
                toggleFab();
                break;
            case R.id.fab_group_single_setting:
                Toast.makeText(getActivity(), "setting", Toast.LENGTH_SHORT).show();
                toggleFab();
                break;
            case R.id.fab_group_single_member:
                Toast.makeText(getActivity(), "member", Toast.LENGTH_SHORT).show();
                toggleFab();
                break;
        }
    }

    private void toggleFab(){
        if(IsFabOpen){
            fab_setting.startAnimation(fab_hide);
            fab_member.startAnimation(fab_hide);
            fab_setting.setClickable(false);
            fab_member.setClickable(false);
            IsFabOpen = false;
        } else {
            fab_setting.startAnimation(fab_open);
            fab_member.startAnimation(fab_open);
            fab_setting.setClickable(true);
            fab_member.setClickable(true);
            IsFabOpen = true;
        }
    }
}
