package com.example.dsm_calendar.ui.fragment;

import android.content.Intent;
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
import com.example.dsm_calendar.contract.ScheduleFragmentContract;
import com.example.dsm_calendar.data.Schedule;
import com.example.dsm_calendar.data.ScheduleFragmentRepository;
import com.example.dsm_calendar.data.Singleton.BusProvider;
import com.example.dsm_calendar.presenter.ScheduleFragmentPresenter;
import com.example.dsm_calendar.ui.Decorator.OnDayDecorator;
import com.example.dsm_calendar.ui.Decorator.SaturdayDecorator;
import com.example.dsm_calendar.ui.Decorator.ScheduleDecorator;
import com.example.dsm_calendar.ui.Decorator.SundayDecorator;
import com.example.dsm_calendar.ui.activity.AddScheduleActivity;
import com.example.dsm_calendar.ui.adapter.ScheduleFragmentRVAdapter;
import com.example.dsm_calendar.util.ScheduleEvent;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.TreeSet;

public class ScheduleFragment extends Fragment implements ScheduleFragmentContract.View {

    private TextView noListTextView;
    private RecyclerView recyclerView;
    private ScheduleFragmentRVAdapter adapter;
    private ImageButton scheduleAddButton;
    private MaterialCalendarView calendarView;
    private ScheduleFragmentPresenter scheduleFragmentPresenter = new ScheduleFragmentPresenter(this, new ScheduleFragmentRepository());

    private ArrayList<Schedule> schedules = new ArrayList<>();
    private ArrayList<Schedule> todayList = new ArrayList<>();

    public ScheduleFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);

        BusProvider.getInstance().register(this);
        scheduleFragmentPresenter.onStarted();

        noListTextView = rootView.findViewById(R.id.tv_no_list_my_schedule);

        recyclerView = rootView.findViewById(R.id.rv_schedule_schedule);
        adapter = new ScheduleFragmentRVAdapter(getActivity(), scheduleFragmentPresenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        calendarView = rootView.findViewById(R.id.cv_schedule_calendar);
        calendarView.addDecorators(
                new SaturdayDecorator(),
                new SundayDecorator(),
                new OnDayDecorator(),
                new ScheduleDecorator(new TreeSet<>(schedules), getActivity()));

        calendarView.setOnDateChangedListener((widget, date, selected) -> {
            for (Schedule schedule : schedules)
                if (date.isInRange(schedule.getStartDay(), schedule.getEndDay()))
                    todayList.add(schedule);

            adapter.list = todayList;
            adapter.notifyDataSetChanged();
            checkList();
        });

        scheduleAddButton = rootView.findViewById(R.id.button_schedule_add);
        scheduleAddButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddScheduleActivity.class);
            intent.putExtra("schedule code", "private");
            startActivity(intent);
        });

        checkList();

        return rootView;
    }

    @Override
    public void onDestroy() {
        BusProvider.getInstance().unregister(this);
        super.onDestroy();
    }

    private void checkList() {
        if (adapter.list.size() == 0) {
            noListTextView.setVisibility(View.VISIBLE);
        } else {
            noListTextView.setVisibility(View.GONE);
        }
    }

    @Subscribe
    public void getNewScheduleList(ScheduleEvent status) {
        if (status.getStatus() == ScheduleEvent.EVENT.SCHEDULE_ADD) {
            scheduleFragmentPresenter.onStarted();
        }
        Toast.makeText(getActivity(), "Event Bus", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void showMessageForDeleteSuccess() {
        Toast.makeText(getActivity(), "delete", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessageForDeleteFail(String message) {
        Toast.makeText(getActivity(), "delete fail\nmessage: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessageForGetScheduleFail(String message) {
        Toast.makeText(getActivity(), "loading fail\nmessage: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getItems(ArrayList<Schedule> testSchedule) {
        schedules = testSchedule;
    }

    @Override
    public void deleteSchedule(int position) {
        adapter.list.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, adapter.getItemCount());
    }
}
