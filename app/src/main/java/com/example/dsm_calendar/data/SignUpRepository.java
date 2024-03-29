package com.example.dsm_calendar.data;

import com.example.dsm_calendar.contract.SignUpContract;
import com.example.dsm_calendar.data.DTO.Student;
import com.example.dsm_calendar.data.Singleton.CalendarRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpRepository implements SignUpContract.Repository {

    public interface SignUpListener{
        void onSuccess();
        void onFail(String message);
    }

    @Override
    public void SignUp(int std_no, String ID, String password, SignUpListener listener) {
        Student student = new Student(password, ID, std_no, 0, 0, 0);

        Call<Void> call = CalendarRetrofit.getInstance().getService().signUp(student);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200){
                    listener.onSuccess();
                } else if (response.code() == 404){
                    listener.onFail("이미 존재하는 id 입니다");
                } else if (response.code() == 500) {
                    listener.onFail("server error");
                } else {
                    listener.onFail(Integer.toString(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onFail(t.getMessage());
            }
        });
    }
}
