package com.example.dsm_calendar.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageButton;
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
import com.example.dsm_calendar.ui.adapter.ScheduleRVAdapter;
import com.example.dsm_calendar.ui.dialog.ScheduleAddDialog;
import com.example.dsm_calendar.util.DialogListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ScheduleFragment extends Fragment implements ScheduleContract.View {

    private RecyclerView recyclerView;
    private ScheduleRVAdapter adapter;
    private ImageButton scheduleAddButton;
    private CalendarView calendarView;
    private ScheduleAddDialog scheduleAddDialog;
    private SchedulePresenter schedulePresenter = new SchedulePresenter(this, new ScheduleRepository());

    private String selectedDate;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public ScheduleFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);
        recyclerView = rootView.findViewById(R.id.rv_schedule_schedule);
        adapter = new ScheduleRVAdapter(getActivity(), schedulePresenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        scheduleAddDialog = new ScheduleAddDialog(getActivity());
        scheduleAddDialog.setScheduleAddDialogListener(new DialogListener.ScheduleAddDialogListener() {
            @Override
            public void onClickConfirm(String title, String date, String content) {
                Toast.makeText(getActivity(), "title: " + title + "\n" + "Content: " + content, Toast.LENGTH_LONG).show();
                adapter.list.add(new SampleSchedule(title, date,content));
                adapter.notifyItemInserted(adapter.getItemCount()+1);
            }
        });
        scheduleAddDialog.setCanceledOnTouchOutside(true);

        scheduleAddButton = rootView.findViewById(R.id.button_schedule_add);
        scheduleAddButton.setOnClickListener( v -> schedulePresenter.onAddScheduleClicked(selectedDate));

        calendarView = rootView.findViewById(R.id.cv_schedule_calendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = (year + "-" + (month+1) + "-" + dayOfMonth);
            }
        });
//        selectedDate = sdf.format(calendarView.getDate());

        schedulePresenter.onStarted();

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void showScheduleAddDialog(String date) {
        scheduleAddDialog.setDate(date);
        scheduleAddDialog.show();
    }

    @Override
    public void showMessageForItemClicked() {
        Toast.makeText(getActivity(), "ItemClicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessageForDeleteSchedule() {
        Toast.makeText(getActivity(), "delete", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addItems(ArrayList<SampleSchedule> testSchedule) {
        adapter.list = testSchedule;
    }
}
