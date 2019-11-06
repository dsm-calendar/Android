package com.example.dsm_calendar.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.data.DTO.Notice;
import com.example.dsm_calendar.presenter.NoticePresenter;

import java.util.ArrayList;

public class NoticeRVAdapter extends RecyclerView.Adapter<NoticeRVAdapter.NoticeViewHolder> {

    public ArrayList<Notice> noticeList = new ArrayList<>();
    private Context context;

    private NoticePresenter noticePresenter;

    public NoticeRVAdapter(Context context, NoticePresenter noticePresenter){
        this.context = context;
        this.noticePresenter = noticePresenter;
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_noticeactivity, parent, false);
        NoticeViewHolder noticeViewHolder = new NoticeViewHolder(view);
        return noticeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {
        holder.bind(noticeList.get(position));
        holder.itemView.setOnClickListener(v -> noticePresenter.onClickItem());
    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    public class NoticeViewHolder extends RecyclerView.ViewHolder {
        TextView titleText;
        TextView contentText;
        ImageButton delete;

        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.tv_noticeactivity_title);
            contentText = itemView.findViewById(R.id.tv_noticedetail_content);
            delete = itemView.findViewById(R.id.button_noticeactivity_delete);
        }

        public void bind(Notice notice){
            this.titleText.setText(notice.getTitle());
        }
    }
}
