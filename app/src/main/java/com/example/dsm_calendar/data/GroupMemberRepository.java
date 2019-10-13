package com.example.dsm_calendar.data;

import com.example.dsm_calendar.contract.GroupMemberContract;

import java.util.ArrayList;

public class GroupMemberRepository implements GroupMemberContract.Repository {

    public interface GetMemberListListener{
        void onSuccess(ArrayList<SampleStudent> students);
        void onFail();
    }

    @Override
    public void getMemberList(GetMemberListListener listener) {
        ArrayList<SampleStudent> students = new ArrayList<>();

        students.add(new SampleStudent("김동휘", "2학년 2반 3번", 1));
        students.add(new SampleStudent("신윤성", "2학년 2반 ??번", 1));
        students.add(new SampleStudent("최승민", "2학년 2반 18번", 1));
        students.add(new SampleStudent("권하경", "2학년 2반 ??번", 1));
        students.add(new SampleStudent("정재훈", "2학년 2반 17번", 1));
        students.add(new SampleStudent("강찬", "2학년 2반 1번", 1));
        students.add(new SampleStudent("강찬", "2학년 2반 1번", 1));
        students.add(new SampleStudent("강찬", "2학년 2반 1번", 1));
        students.add(new SampleStudent("강찬", "2학년 2반 1번", 1));
        students.add(new SampleStudent("강찬", "2학년 2반 1번", 1));
        students.add(new SampleStudent("강찬", "2학년 2반 1번", 1));
        students.add(new SampleStudent("강찬", "2학년 2반 1번", 1));
        students.add(new SampleStudent("강찬", "2학년 2반 1번", 1));

        listener.onSuccess(students);
    }
}
