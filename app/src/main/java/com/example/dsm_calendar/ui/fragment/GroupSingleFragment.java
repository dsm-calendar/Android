package com.example.dsm_calendar.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.example.dsm_calendar.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GroupSingleFragment extends Fragment {

    private FloatingActionButton fab;
    private NestedScrollView nsv;

    public GroupSingleFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //TODO: add fab to group single fragment
        View rootView = inflater.inflate(R.layout.fragment_group_single, container, false);

        fab = rootView.findViewById(R.id.fab_group_single);
        nsv = rootView.findViewById(R.id.nestedScrollView_group_single);

        nsv.setOnScrollChangeListener((View.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if(scrollY > oldScrollY){
                fab.hide();
            } else {
                fab.show();
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
