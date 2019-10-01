package com.example.dsm_calendar.ui.activity;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.contract.MainContract;
import com.example.dsm_calendar.data.MainRepository;
import com.example.dsm_calendar.presenter.MainPresenter;
import com.example.dsm_calendar.ui.adapter.MainPagerAdapter;
import com.example.dsm_calendar.ui.dialog.AuthCodeDialog;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MainContract.View{

    private MainPagerAdapter adapter;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private AuthCodeDialog authCodeDialog;

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private MainPresenter mainPresenter = new MainPresenter(this, new MainRepository());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.vp_main_main);
        tabLayout = findViewById(R.id.tl_main_main);

        adapter = new MainPagerAdapter(getSupportFragmentManager(), tabLayout);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);

        tabLayout.setupWithViewPager(viewPager);

        toolbar = findViewById(R.id.tb_main_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nv_main_main);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        ImageView profile = header.findViewById(R.id.profile_image);
        profile.setBackground(new ShapeDrawable(new OvalShape()));
        profile.setClipToOutline(true);

        authCodeDialog = new AuthCodeDialog(this, offButtonListener, checkButtonListener);
        authCodeDialog.setCanceledOnTouchOutside(true);

        setToolBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_toolbar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.item_toolbar_mail:
                mainPresenter.onClickMailbox();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.item_navigation_setting:
                mainPresenter.onClickSetting();
                break;
            case R.id.item_navigation_code:
                mainPresenter.onClickAuthCode();
                break;
            case R.id.item_navigation_event:
                mainPresenter.onClickRequireEvent();
                break;
            case R.id.item_navigation_timeTable:
                mainPresenter.onClickTimeTable();
                break;
            case R.id.item_navigation_calendar_school:
                mainPresenter.onClickSchoolCalendar();
                break;
            case R.id.item_navigation_calendar_group:
                Toast.makeText(this, "group calendar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_navigation_calendar_my:
                mainPresenter.onClickMyCalendar();
                break;
            case R.id.item_navigation_logout:
                mainPresenter.onClickLogout();
                break;
        }
        drawerLayout.closeDrawers();
        return false;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

    public void setToolBar(){
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_list);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setHomeButtonEnabled(true);
    }

    private View.OnClickListener offButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            authCodeDialog.dismiss();
        }
    };

    private View.OnClickListener checkButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "check", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void startMailBoxActivity() {
        Intent intent = new Intent(MainActivity.this, MessageActivity.class);
        startActivity(intent);
    }

    @Override
    public void showAuthDialog() {
        authCodeDialog.show();
    }

    @Override
    public void startTimeTableActivity() {
        Intent intent = new Intent(MainActivity.this, TimeTableActivity.class);
        startActivity(intent);
    }

    @Override
    public void moveToMyCalendar() {
        viewPager.setCurrentItem(0);
    }
}
