package com.example.dsm_calendar.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.data.SampleSchedule;
import com.example.dsm_calendar.ui.adapter.ScheduleRVAdapter;
import com.example.dsm_calendar.ui.dialog.ScheduleAddDialog;

import java.util.ArrayList;

public class ScheduleFragment extends Fragment {

    private RecyclerView recyclerView;
    private ScheduleRVAdapter adapter;
    private ArrayList<SampleSchedule> list = new ArrayList<>();
    private ImageButton scheduleAddButton;
    private ScheduleAddDialog scheduleAddDialog;

    public ScheduleFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        list.add(new SampleSchedule("sample 1", "2019-03-04", "blablablabla"));
        list.add(new SampleSchedule("sample 2", "2019-04-23", "today i have to go to school i want to go home fuck"));
        list.add(new SampleSchedule("sample 3", "2019-05-05", "holiday"));

        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);
        recyclerView = rootView.findViewById(R.id.rv_schedule_schedule);
        adapter = new ScheduleRVAdapter(getActivity(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        scheduleAddDialog = new ScheduleAddDialog(getActivity(), offButtonListener, checkButtonListener);
        scheduleAddButton = rootView.findViewById(R.id.button_schedule_add);
        scheduleAddButton.setOnClickListener( v -> {
            scheduleAddDialog.show();
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private View.OnClickListener offButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            scheduleAddDialog.dismiss();
        }
    };

    private View.OnClickListener checkButtonListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), "check", Toast.LENGTH_SHORT).show();
        }
    };
}
