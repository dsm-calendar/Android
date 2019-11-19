package com.example.dsm_calendar.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.dsm_calendar.R;
import com.example.dsm_calendar.data.DTO.Event;
import com.example.dsm_calendar.ui.activity.BannerDetailActivity;
import com.example.dsm_calendar.ui.activity.MainActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainBannerAdapter extends PagerAdapter {

    Context context;
    LayoutInflater inflater;
    public ArrayList<Event> bannerList = new ArrayList<>();

    public MainBannerAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return bannerList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.main_vp_banner_image_view, null);
        ImageView imageView = view.findViewById(R.id.vp_adapter);
        Glide.with(context).load(bannerList.get(position).getEventPoster()).into(imageView);
        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(context, BannerDetailActivity.class);
            Bitmap bmp = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 50, baos);
            context.startActivity(intent);
        });

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}
