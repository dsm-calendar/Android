package com.example.dsm_calendar.ui.adapter;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.dsm_calendar.ui.fragment.GroupFragment;
import com.example.dsm_calendar.ui.fragment.MainFragment;
import com.example.dsm_calendar.ui.fragment.ScheduleFragment;
import com.google.android.material.tabs.TabLayout;

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private String[] titleList = {"일정보기", "Main", "그룹목록"};
    private TabLayout tabLayout;

    public MainPagerAdapter(FragmentManager fm, TabLayout tabLayout) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.tabLayout = tabLayout;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new ScheduleFragment();
            case 1: return new MainFragment();
            case 2: return new GroupFragment();
            default: return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList[position];
    }
}
