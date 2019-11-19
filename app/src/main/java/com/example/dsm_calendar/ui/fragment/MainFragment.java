package com.example.dsm_calendar.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.dsm_calendar.contract.MainFragmentContract;
import com.example.dsm_calendar.data.DTO.MainResponse;
import com.example.dsm_calendar.data.DTO.Notice;
import com.example.dsm_calendar.data.DTO.TimeTableUnit;
import com.example.dsm_calendar.data.MainFragmentRepository;
import com.example.dsm_calendar.presenter.MainFragmentPresenter;
import com.example.dsm_calendar.ui.activity.MainActivity;
import com.example.dsm_calendar.ui.activity.NoticeActivity;
import com.example.dsm_calendar.ui.adapter.MainRVNoticeAdapter;
import com.example.dsm_calendar.ui.adapter.MainRVTodayAdapter;
import com.example.dsm_calendar.ui.adapter.MainBannerAdapter;
import com.example.dsm_calendar.R;
import com.rd.PageIndicatorView;

import java.util.ArrayList;

public class MainFragment extends Fragment implements RadioButton.OnClickListener, MainFragmentContract.View {

    private RecyclerView recyclerView;
    private MainRVTodayAdapter mainRVTodayAdapter;
    private MainRVNoticeAdapter mainRVNoticeAdapter;
    private MainBannerAdapter mainBannerAdapter;
    private TextView noListTextView;
    private PageIndicatorView pageIndicatorView;

    private MainFragmentPresenter presenter = new MainFragmentPresenter(this, new MainFragmentRepository(getActivity()));
    private MainActivity rootActivity;
    private ArrayList<TimeTableUnit> timeTableUnits = new ArrayList<>();

    public MainFragment(MainActivity mainActivity) {
        this.rootActivity = mainActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        noListTextView = rootView.findViewById(R.id.tv_no_list_main);

        recyclerView = rootView.findViewById(R.id.rv_main_listBox);
        mainRVTodayAdapter = new MainRVTodayAdapter(getActivity(), presenter);
        mainRVNoticeAdapter = new MainRVNoticeAdapter(getActivity(), presenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        rootView.findViewById(R.id.button_main_notice).setOnClickListener(this);
        rootView.findViewById(R.id.button_main_schedule).setOnClickListener(this);

        mainBannerAdapter = new MainBannerAdapter(getActivity());

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
        setBanner(rootView);
        setRecyclerView();
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_main_notice){
            recyclerView.setAdapter(mainRVNoticeAdapter);
            checkList(mainRVNoticeAdapter.getItemCount());
        } else if(v.getId() == R.id.button_main_schedule){
            recyclerView.setAdapter(mainRVTodayAdapter);
            checkList(mainRVTodayAdapter.getItemCount());
        }
    }

    private void setBanner(View rootView){
        //https://github.com/romandanylyk/PageIndicatorView
        pageIndicatorView = rootView.findViewById(R.id.piv_main_indicator);
        pageIndicatorView.setCount(mainBannerAdapter.bannerList.size());
        pageIndicatorView.setSelection(0);

    }

    private void setRecyclerView(){
        recyclerView.setAdapter(mainRVNoticeAdapter);
        checkList(mainRVNoticeAdapter.getItemCount());
    }

    private void checkList(int size){
        if(size == 0){
            noListTextView.setVisibility(View.VISIBLE);
        } else {
            noListTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void setMainFragment(MainResponse response) {
        mainRVNoticeAdapter.notice = response.getNotices();
        mainBannerAdapter.bannerList = response.getEventList();
        timeTableUnits = response.getTimeTables();
    }

    @Override
    public void showMessageForLoadMainPageFail(String message) {
        Toast.makeText(getActivity(), "error: " + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startNoticeActivity() {
        Intent intent = new Intent(getActivity(), NoticeActivity.class);
        startActivity(intent);
    }

    @Override
    public void moveToScheduleFragment() {
        rootActivity.movePage(0);
    }
}
