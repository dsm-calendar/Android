package com.example.dsm_calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MainVPBannerAdapter extends PagerAdapter {

    Context context;
    LayoutInflater inflater;
    Integer[] obj;

    public MainVPBannerAdapter(Context context, Integer[] obj){
        this.context = context;
        this.obj = obj;
    }

    @Override
    public int getCount() {
        return obj.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.main_vp_banner_adapter, null);
        ImageView imageView = view.findViewById(R.id.vp_adapter);
        imageView.setImageResource(obj[position]);

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
