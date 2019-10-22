package com.example.dsm_calendar.data;

import com.example.dsm_calendar.contract.SignUpContract;
import com.example.dsm_calendar.data.DTO.Student;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpRepository implements SignUpContract.Repository {

    private String URL = "http://10.156.145.132:8080/";

    public interface SignUpListener{
        void onSuccess();
        void onFail(String message);
    }

    @Override
    public void SignUp(int std_no, String ID, String password, SignUpListener listener) {
        Student student = new Student(password, ID, std_no, 0, 0);

        Call<Void> call = CalendarRetrofit.getInstance().getService().signUp(student);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200){
                    listener.onSuccess();
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
