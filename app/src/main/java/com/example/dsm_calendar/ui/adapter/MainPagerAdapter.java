package com.example.dsm_calendar.ui.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.dsm_calendar.ui.fragment.GroupRootFragment;
import com.example.dsm_calendar.ui.fragment.MainFragment;
import com.example.dsm_calendar.ui.fragment.ScheduleFragment;

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private String[] titleList = {"일정보기", "Main", "그룹목록"};

    public MainPagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
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
            case 2: return new GroupRootFragment();
            default: return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList[position];
    }
}
