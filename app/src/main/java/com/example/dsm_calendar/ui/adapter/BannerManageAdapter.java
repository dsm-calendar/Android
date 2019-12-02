package com.example.dsm_calendar.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dsm_calendar.R;
import com.example.dsm_calendar.data.DTO.Event;
import com.example.dsm_calendar.data.SampleBanner;
import com.example.dsm_calendar.presenter.BannerManagePresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerManageAdapter extends RecyclerView.Adapter<BannerManageAdapter.BannerManageViewHolder> {

    private Context context;
    private BannerManagePresenter presenter;

    public ArrayList<Event> bannerList = new ArrayList<>();

    public BannerManageAdapter(Context context, BannerManagePresenter presenter){
        this.context = context;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public BannerManageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bannermanage, parent, false);
        BannerManageViewHolder holder = new BannerManageViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BannerManageViewHolder holder, int position) {
        Event event = bannerList.get(position);
        holder.bind(event, position);
    }

    @Override
    public int getItemCount() {
        return bannerList.size();
    }

    class BannerManageViewHolder extends RecyclerView.ViewHolder {

        ImageView banner;
        TextView content;
        ImageButton accept;
        ImageButton delete;

        BannerManageViewHolder(@NonNull View itemView) {
            super(itemView);

            banner = itemView.findViewById(R.id.iv_bannermanage_image);
            content = itemView.findViewById(R.id.tv_bannermanage_detail);
            accept = itemView.findViewById(R.id.button_bannermanage_accept);
            delete = itemView.findViewById(R.id.button_bannermanage_delete);
        }

        public void bind(Event event, int position){
            Picasso.with(context).load(event.getEventPoster()).placeholder(R.drawable.sample_car).into(banner);
            content.setText(event.getEventDetail());

            accept.setOnClickListener(v -> presenter.onManageButtonClicked(event, true, position));
            delete.setOnClickListener(v -> presenter.onManageButtonClicked(event, false, position));
        }
    }
}
