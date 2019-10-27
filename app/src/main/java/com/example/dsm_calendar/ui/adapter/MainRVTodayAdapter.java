package com.example.dsm_calendar.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.presenter.MainFragmentPresenter;

import java.util.ArrayList;

public class MainRVTodayAdapter extends RecyclerView.Adapter<MainRVTodayAdapter.MainTodayViewHolder> {

    public ArrayList<String> today = new ArrayList<>();
    private MainFragmentPresenter presenter;
    private Context context;

    public MainRVTodayAdapter(Context context, MainFragmentPresenter presenter){
        this.context = context;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public MainTodayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_main, parent, false);
        MainTodayViewHolder holder = new MainTodayViewHolder(item);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainTodayViewHolder holder, int position) {
        holder.bind(today.get(position));
    }

    @Override
    public int getItemCount() {
        return today.size();
    }

    class MainTodayViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        MainTodayViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.main_rv_item_tv);
        }

        public void bind(String title){
            tv.setText(title);
            itemView.setOnClickListener( v -> presenter.onClickScheduleItem());
        }
    }
}
