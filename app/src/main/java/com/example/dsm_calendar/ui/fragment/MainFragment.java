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

import com.example.dsm_calendar.contract.MainFragmentContract;
import com.example.dsm_calendar.data.MainFragmentRepository;
import com.example.dsm_calendar.presenter.MainFragmentPresenter;
import com.example.dsm_calendar.ui.adapter.MainRVAdapter;
import com.example.dsm_calendar.ui.adapter.MainBannerAdapter;
import com.example.dsm_calendar.R;
import com.rd.PageIndicatorView;

import java.util.ArrayList;

public class MainFragment extends Fragment implements RadioButton.OnClickListener, MainFragmentContract.View {

    private RecyclerView recyclerView;
    public MainRVAdapter mainRVAdapter;
    public MainBannerAdapter mainBannerAdapter;
    private ArrayList<String> noticeList = new ArrayList<>();
    private ArrayList<String> todayList = new ArrayList<>();
    private ArrayList<Integer> bannerList = new ArrayList<>();

    private MainFragmentPresenter presenter = new MainFragmentPresenter(this, new MainFragmentRepository());

    public MainFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = rootView.findViewById(R.id.rv_main_listBox);
        mainRVAdapter = new MainRVAdapter(getActivity(), noticeList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mainRVAdapter);

        rootView.findViewById(R.id.button_main_notice).setOnClickListener(this);
        rootView.findViewById(R.id.button_main_schedule).setOnClickListener(this);

        //https://github.com/romandanylyk/PageIndicatorView
        PageIndicatorView pageIndicatorView = rootView.findViewById(R.id.piv_main_indicator);
        pageIndicatorView.setCount(bannerList.size());
        pageIndicatorView.setSelection(0);
        mainBannerAdapter = new MainBannerAdapter(getActivity(), bannerList);

        ViewPager pager = rootView.findViewById(R.id.vp_main_fragment);
        pager.setAdapter(mainBannerAdapter);
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

        presenter.onStarted();
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_main_notice){
            mainRVAdapter = new MainRVAdapter(getActivity(), noticeList);
            recyclerView.setAdapter(mainRVAdapter);
        } else if(v.getId() == R.id.button_main_schedule){
            mainRVAdapter = new MainRVAdapter(getActivity(), todayList);
            recyclerView.setAdapter(mainRVAdapter);
        }
    }

    @Override
    public void getNotice(ArrayList<String> notices) {
        noticeList = notices;
    }

    @Override
    public void getSchedule(ArrayList<String> schedules) {
        todayList = schedules;
    }

    @Override
    public void getBanners(ArrayList<Integer> banners) {
        mainBannerAdapter.bannerList = banners;
        mainBannerAdapter.notifyDataSetChanged();
    }

    @Override
    public void getMyTimeTable() {
        //TODO: set text on time table
    }
}
