package com.example.dsm_calendar.ui.adapter;

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

import java.util.ArrayList;

public class BannerManageAdapter extends RecyclerView.Adapter<BannerManageAdapter.BannerManageViewHolder> {

    public ArrayList<SampleBanner> bannerList = new ArrayList<>();

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
        holder.bind(image, content);
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

        public BannerManageViewHolder(@NonNull View itemView) {
            super(itemView);

            banner = itemView.findViewById(R.id.iv_bannermanage_image);
            content = itemView.findViewById(R.id.tv_bannermanage_detail);
            accept = itemView.findViewById(R.id.button_bannermanage_accept);
            delete = itemView.findViewById(R.id.button_bannermanage_delete);

            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO: call presenter to reflect change
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO: call presenter to reflect change
                }
            });
        }

        public void bind(int image, String bannerContent){
            banner.setImageResource(image);
            content.setText(bannerContent);
        }
    }
}
