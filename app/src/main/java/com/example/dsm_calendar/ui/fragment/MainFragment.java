package com.example.dsm_calendar.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.dsm_calendar.ui.adapter.MainRVAdapter;
import com.example.dsm_calendar.ui.adapter.MainBannerAdapter;
import com.example.dsm_calendar.R;
import com.rd.PageIndicatorView;

import java.util.ArrayList;

public class MainFragment extends Fragment implements RadioButton.OnClickListener {

    private RecyclerView recyclerView;
    private MainRVAdapter adapter;
    private ArrayList<String> noticeList = new ArrayList<>();
    private ArrayList<String> todayList = new ArrayList<>();

    public MainFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //TODO: fix timetable layout
        noticeList.add("sample");
        noticeList.add("sample2");
        noticeList.add("sample3");
        noticeList.add("long long long long long long sample");
        noticeList.add("short sample?");
        noticeList.add("sample what?");

        todayList.add("do laundry");
        todayList.add("homework");
        todayList.add("buy some beer");
        todayList.add("sleep like a boss");
        todayList.add("buy a box of monster energy");
        todayList.add("goto bank");

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = rootView.findViewById(R.id.rv_main_listBox);
        adapter = new MainRVAdapter(getActivity(), noticeList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        rootView.findViewById(R.id.button_main_notice).setOnClickListener(this);
        rootView.findViewById(R.id.button_main_schedule).setOnClickListener(this);

        Integer[] obj = {R.drawable.sample_sportscar, R.drawable.sample_rainbow, R.drawable.sample_ocean, R.drawable.sample_universe, R.drawable.sample_car};

        //https://github.com/romandanylyk/PageIndicatorView
        PageIndicatorView pageIndicatorView = rootView.findViewById(R.id.piv_main_indicator);
        pageIndicatorView.setCount(obj.length);
        pageIndicatorView.setSelection(0);
        MainBannerAdapter adapter = new MainBannerAdapter(getActivity(), obj);
        ViewPager pager = rootView.findViewById(R.id.vp_main_main);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) {
                pageIndicatorView.setSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) { }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_main_notice){
            adapter = new MainRVAdapter(getActivity(), noticeList);
            recyclerView.setAdapter(adapter);
            Toast.makeText(getActivity(),"notice", Toast.LENGTH_SHORT).show();
        } else if(v.getId() == R.id.button_main_schedule){
            adapter = new MainRVAdapter(getActivity(), todayList);
            recyclerView.setAdapter(adapter);
            Toast.makeText(getActivity(),"schedule", Toast.LENGTH_SHORT).show();
        }
    }
}
