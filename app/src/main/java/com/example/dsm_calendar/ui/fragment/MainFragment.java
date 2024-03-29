package com.example.dsm_calendar.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.contract.MainFragmentContract;
import com.example.dsm_calendar.data.DTO.MainResponse;
import com.example.dsm_calendar.data.DTO.TimeTableUnit;
import com.example.dsm_calendar.data.MainFragmentRepository;
import com.example.dsm_calendar.data.Singleton.UserPreference;
import com.example.dsm_calendar.presenter.MainFragmentPresenter;
import com.example.dsm_calendar.ui.activity.BannerManageActivity;
import com.example.dsm_calendar.ui.activity.MainActivity;
import com.example.dsm_calendar.ui.activity.NoticeActivity;
import com.example.dsm_calendar.ui.adapter.MainBannerAdapter;
import com.example.dsm_calendar.ui.adapter.MainRVNoticeAdapter;
import com.example.dsm_calendar.ui.adapter.MainRVTodayAdapter;
import com.rd.PageIndicatorView;

import java.util.ArrayList;

public class MainFragment extends Fragment implements RadioButton.OnClickListener, MainFragmentContract.View {

    private RecyclerView recyclerView;
    private RadioGroup radioGroup;
    private MainRVTodayAdapter mainRVTodayAdapter;
    private MainRVNoticeAdapter mainRVNoticeAdapter;
    private MainBannerAdapter mainBannerAdapter;
    private TextView noListTextView;
    private TextView noBannerTextView;
    private PageIndicatorView pageIndicatorView;

    private MainFragmentPresenter presenter = new MainFragmentPresenter(this, new MainFragmentRepository(getActivity()));
    private MainActivity rootActivity;
    private ArrayList<TimeTableUnit> timeTableUnits = new ArrayList<>();

    private TableLayout table;
    private ArrayList<TextView> tables;
    private boolean isNoticeChecked = true;
    private boolean isAdmin = UserPreference.getInstance(getActivity()).getIsAdmin();

    private View rootView;

    public MainFragment(MainActivity mainActivity) {
        this.rootActivity = mainActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        noListTextView = rootView.findViewById(R.id.tv_no_list_main);
        noListTextView.setOnClickListener(this);

        noBannerTextView = rootView.findViewById(R.id.tv_no_list_banner);
        noBannerTextView.setOnClickListener(this);

        radioGroup = rootView.findViewById(R.id.rg_main_listButtons);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId){
                case R.id.button_main_notice:
                    recyclerView.setAdapter(mainRVNoticeAdapter);
                    checkNoticeList(mainRVNoticeAdapter.getItemCount());
                    isNoticeChecked = true;
                    break;
                case R.id.button_main_schedule:
                    recyclerView.setAdapter(mainRVTodayAdapter);
                    checkNoticeList(mainRVTodayAdapter.getItemCount());
                    isNoticeChecked = false;
                    break;
            }
        });

        recyclerView = rootView.findViewById(R.id.rv_main_listBox);
        mainRVTodayAdapter = new MainRVTodayAdapter(getActivity(), presenter);
        mainRVNoticeAdapter = new MainRVNoticeAdapter(getActivity(), presenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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
        if (v.getId() == R.id.tv_no_list_main && isNoticeChecked && isAdmin){
            Intent intent = new Intent(getActivity(), NoticeActivity.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.tv_no_list_banner && UserPreference.getInstance(getActivity()).getIsAdmin()){
            Intent intent = new Intent(getActivity(), BannerManageActivity.class);
            startActivity(intent);
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
        checkNoticeList(mainRVNoticeAdapter.getItemCount());
    }

    private void checkNoticeList(int size){
        if(size == 0){
            noListTextView.setVisibility(View.VISIBLE);
        } else {
            noListTextView.setVisibility(View.GONE);
        }
    }

    private void checkBannerList(int size){
        if (size == 0){
            noBannerTextView.setVisibility(View.VISIBLE);
        } else {
            noBannerTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void setMainFragment(MainResponse response) {
        mainRVNoticeAdapter.notice = response.getNotices();
        mainRVNoticeAdapter.notifyDataSetChanged();
        mainRVTodayAdapter.today = response.getSchedules();
        mainRVTodayAdapter.notifyDataSetChanged();
        mainBannerAdapter.bannerList = response.getEventList();
        mainBannerAdapter.notifyDataSetChanged();
        timeTableUnits = response.getTimeTables();
        setTimeTable();
        recyclerView.setAdapter(mainRVNoticeAdapter);
        checkBannerList(mainBannerAdapter.getCount());
        checkNoticeList(mainRVNoticeAdapter.getItemCount());
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

    private void setTimeTable() {
        table = rootView.findViewById(R.id.tl_main_table);
        tables = new ArrayList<>();

        for(int i = 0; i < table.getChildCount(); ++i){
            TableRow row = (TableRow)table.getChildAt(i);

            for (int j = 0; j < row.getChildCount(); ++j) {
                View view = row.getChildAt(j);
                if (view.getId() != View.NO_ID)
                    tables.add((TextView)view);
            }
        }

        for (TimeTableUnit unit : timeTableUnits) {
            int idx = unit.getTimeTableIndex() % 100 - 11;
            TextView table = tables.get((idx / 10) + (idx % 10) * 5);
            table.setText(unit.getSubject() +"\n" + unit.getTeacher());
        }
    }
}
