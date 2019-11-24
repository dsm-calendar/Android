package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.GroupScheduleRepository;
import com.example.dsm_calendar.data.DTO.Schedule;

import java.util.ArrayList;

public interface GroupScheduleContract {
    interface View{
        void showMessageForDeleteSuccess();
        void showMessageForDeleteFail(String message);
        void showMessageForGetScheduleFail(String message);
        void getList(ArrayList<Schedule> schedules);
        void deleteSchedule(int position);
    }

    interface Presenter{
        void onStarted(int roomId);
        void onItemDeleteClicked(int scheduleId, int index);
    }

    interface Repository{
        void getSchedule(int roomId, GroupScheduleRepository.GetScheduleListener listener);
        void deleteSchedule(GroupScheduleRepository.DeleteScheduleListener listener);
    }
}
