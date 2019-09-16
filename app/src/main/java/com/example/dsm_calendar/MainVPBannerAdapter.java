package com.example.dsm_calendar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class MainVPBannerAdapter extends PagerAdapter {

    Context context;
    ArrayList<Drawable> obj;

    public MainVPBannerAdapter(Context context, ArrayList<Drawable> obj){
        this.context = context;
        this.obj = obj;
    }

    @Override
    public int getCount() {
        return obj.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = null;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.main_vp_banner_adapter, container, false);
        ImageView imageView = view.findViewById(R.id.vp_adapter);
        imageView.setImageDrawable(obj.get(position));
        container.addView(view);

        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
}
