package com.example.dsm_calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

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

        noticeList.add("sample");
        noticeList.add("sample2");
        noticeList.add("sample3");
        noticeList.add("long long long long long sample");
        noticeList.add("hohohohohoho");
        noticeList.add("sample what?");

        todayList.add("do laundry");
        todayList.add("homework");
        todayList.add("buy some beer");
        todayList.add("sleep like a boss");
        todayList.add("buy a box of monster energy");
        todayList.add("goto bank");

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = rootView.findViewById(R.id.main_rv);
        adapter = new MainRVAdapter(getContext(), noticeList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        rootView.findViewById(R.id.notice_radioButton).setOnClickListener(this);
        rootView.findViewById(R.id.schedule_radioButton).setOnClickListener(this);

        Integer[] obj = {R.drawable.sample_car2, R.drawable.sample_rainbow};

        MainVPBannerAdapter adapter = new MainVPBannerAdapter(getContext(), obj);
        ViewPager pager = rootView.findViewById(R.id.main_viewPager);
        pager.setAdapter(adapter);
        LinearLayout sliderDotsPanel = rootView.findViewById(R.id.slider_dots);
        int dotsCount = adapter.getCount();
        ImageView[] dots = new ImageView[dotsCount];


        for (int i = 0; i < dotsCount; i++){
            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.default_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8,0,0,8);
            sliderDotsPanel.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.selected_dot));
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i = 0; i<dotsCount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.default_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.selected_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.notice_radioButton){
            adapter = new MainRVAdapter(getContext(), noticeList);
            recyclerView.setAdapter(adapter);
            Toast.makeText(getContext(),"notice", Toast.LENGTH_SHORT).show();
        } else if(v.getId() == R.id.schedule_radioButton){
            adapter = new MainRVAdapter(getContext(), todayList);
            recyclerView.setAdapter(adapter);
            Toast.makeText(getContext(),"schedule", Toast.LENGTH_SHORT).show();
        }
    }
}
