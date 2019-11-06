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

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.data.SampleBanner;
import com.example.dsm_calendar.presenter.BannerManagePresenter;

import java.util.ArrayList;

public class BannerManageAdapter extends RecyclerView.Adapter<BannerManageAdapter.BannerManageViewHolder> {

    private Context context;
    private BannerManagePresenter presenter;

    public ArrayList<SampleBanner> bannerList = new ArrayList<>();

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
        int image = bannerList.get(position).getImage();
        String content = bannerList.get(position).getContent();
        holder.bind(image, content, position);
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

        public void bind(int image, String bannerContent, int position){
            banner.setImageResource(image);
            content.setText(bannerContent);

            accept.setOnClickListener(v -> presenter.onAccept(0, position));
            delete.setOnClickListener(v -> presenter.onReject(0, position));
        }
    }
}
