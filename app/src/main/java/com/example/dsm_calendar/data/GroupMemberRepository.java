package com.example.dsm_calendar.data;

import com.example.dsm_calendar.contract.GroupMemberContract;
import com.example.dsm_calendar.data.DTO.Student;

import java.util.ArrayList;

public class GroupMemberRepository implements GroupMemberContract.Repository {

    public interface GetMemberListListener{
        void onSuccess(ArrayList<Student> students);
        void onFail();
    }

    public interface InviteMemberListener{
        void onSuccess();
        void onFail(String message);
    }

    @Override
    public void getMemberList(GetMemberListListener listener) {
        ArrayList<Student> students = new ArrayList<>();

        students.add(new Student("김동휘", "2학년 2반 3번", 1111, 0, 0));
        students.add(new Student("신윤성", "2학년 2반 ??번", 1111, 0, 0));
        students.add(new Student("최승민", "2학년 2반 18번", 1111, 0, 0));
        students.add(new Student("권하경", "2학년 2반 ??번", 1111, 0, 0));
        students.add(new Student("정재훈", "2학년 2반 17번", 1111, 0, 0));
        students.add(new Student("강찬", "2학년 2반 1번", 1111, 0, 0));
        students.add(new Student("강찬", "2학년 2반 1번", 1111, 0, 0));
        students.add(new Student("강찬", "2학년 2반 1번", 1111, 0, 0));
        students.add(new Student("강찬", "2학년 2반 1번", 1111, 0, 0));
        students.add(new Student("강찬", "2학년 2반 1번", 1111, 0, 0));
        students.add(new Student("강찬", "2학년 2반 1번", 1111, 0, 0));
        students.add(new Student("강찬", "2학년 2반 1번", 1111, 0, 0));
        students.add(new Student("강찬", "2학년 2반 1번", 1111, 0, 0));

        listener.onSuccess(students);
    }

    @Override
    public void inviteMember(InviteMemberListener listener) {
        listener.onSuccess();
    }
}
