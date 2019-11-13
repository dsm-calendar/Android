package com.example.dsm_calendar.ui.activity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.contract.GroupScheduleContract;
import com.example.dsm_calendar.data.GroupScheduleRepository;
import com.example.dsm_calendar.data.Schedule;
import com.example.dsm_calendar.presenter.GroupSchedulePresenter;
import com.example.dsm_calendar.ui.adapter.GroupScheduleRVAdapter;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;

public class GroupScheduleActivity extends AppCompatActivity implements GroupScheduleContract.View {

    private ImageButton backButton;
    private MaterialCalendarView calendarView;
    private TextView noListTextView;
    private RecyclerView recyclerView;
    private ImageButton addButton;
    private GroupScheduleRVAdapter adapter;

    private GroupSchedulePresenter presenter = new GroupSchedulePresenter(this, new GroupScheduleRepository());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_group_schedule);

        backButton = findViewById(R.id.button_group_schedule_back);
        calendarView = findViewById(R.id.cv_group_calendar);
        noListTextView = findViewById(R.id.tv_no_list_group_schedule);
        recyclerView = findViewById(R.id.rv_group_schedule);
        addButton = findViewById(R.id.button_group_schedule_add);

        adapter = new GroupScheduleRVAdapter(presenter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        //TODO: add decorator to calendar
    }

    private void checkList(){
        //TODO: add adapter and set visibility;
    }

    @Override
    public void showMessageForDeleteSuccess() {
        Toast.makeText(this, "delete Success", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageForDeleteFail(String message) {
        Toast.makeText(this, "delete Fail\nmessage: " + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageForGetScheduleFail(String message) {
        Toast.makeText(this, "loading Fail\nmessage: " + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void getList(ArrayList<Schedule> schedules) {
        adapter.schedules = schedules;
    }

    @Override
    public void deleteSchedule(int position) {
        adapter.schedules.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, adapter.getItemCount());
    }
}
