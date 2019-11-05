package com.example.dsm_calendar.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.contract.ScheduleContract;
import com.example.dsm_calendar.data.SampleSchedule;
import com.example.dsm_calendar.data.ScheduleRepository;
import com.example.dsm_calendar.presenter.SchedulePresenter;
import com.example.dsm_calendar.ui.Decorator.EventDecorator;
import com.example.dsm_calendar.ui.Decorator.OnDayDecorator;
import com.example.dsm_calendar.ui.Decorator.SaturdayDecorator;
import com.example.dsm_calendar.ui.Decorator.SundayDecorator;
import com.example.dsm_calendar.ui.activity.AddScheduleActivity;
import com.example.dsm_calendar.ui.adapter.ScheduleRVAdapter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ScheduleFragment extends Fragment implements ScheduleContract.View {

    private TextView noListTextView;
    private RecyclerView recyclerView;
    private ScheduleRVAdapter adapter;
    private ImageButton scheduleAddButton;
    private MaterialCalendarView calendarView;
    private SchedulePresenter schedulePresenter = new SchedulePresenter(this, new ScheduleRepository());

    private String selectedDate;
    private CalendarDay day;
    private ArrayList<CalendarDay> scheduleDayList = new ArrayList<>();


    public ScheduleFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);

        noListTextView = rootView.findViewById(R.id.tv_no_list_my_schedule);

        recyclerView = rootView.findViewById(R.id.rv_schedule_schedule);
        adapter = new ScheduleRVAdapter(getActivity(), schedulePresenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        getScheduleDate();
        calendarView = rootView.findViewById(R.id.cv_schedule_calendar);
        calendarView.addDecorators(
                new SaturdayDecorator(),
                new SundayDecorator(),
                new OnDayDecorator(),
                new EventDecorator(Color.RED, scheduleDayList));
        calendarView.setOnDateChangedListener(((widget, date, selected) -> {
            selectedDate = date.getYear() + "-" + (date.getMonth()+1) + "-" + date.getDay();
            day = date;
        }));

        scheduleAddButton = rootView.findViewById(R.id.button_schedule_add);
        scheduleAddButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddScheduleActivity.class);
            startActivity(intent);
        });

        schedulePresenter.onStarted();
        checkList();
        setScheduleDecorate();

        return rootView;
    }

    private void checkList(){
        if(adapter.list.size() == 0){
            noListTextView.setVisibility(View.VISIBLE);
        } else {
            noListTextView.setVisibility(View.GONE);
        }
    }

    private void setScheduleDecorate(){
        getScheduleDate();
        calendarView.addDecorator(new EventDecorator(Color.RED, scheduleDayList));
    }

    private void getScheduleDate(){
        if(adapter.list != null){
            for(int i = 0; i < adapter.getItemCount(); ++i){
                scheduleDayList.add(adapter.list.get(i).getDay());
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void showMessageForDeleteSchedule() {
        Toast.makeText(getActivity(), "delete", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessageForItemAdded() {
        Toast.makeText(getActivity(), "item added", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessageForSelectDate() {
        Toast.makeText(getActivity(), "please select date", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getItems(ArrayList<SampleSchedule> testSchedule) {
        adapter.list = testSchedule;
    }

    @Override
    public void addSchedule(SampleSchedule schedule) {
        adapter.list.add(schedule);
        Collections.sort(adapter.list, (o1, o2) -> o1.getDate().compareTo(o2.getDate()));
        adapter.notifyDataSetChanged();
        setScheduleDecorate();
    }

    @Override
    public void deleteSchedule(int position) {
        adapter.list.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, adapter.getItemCount());
    }
}
