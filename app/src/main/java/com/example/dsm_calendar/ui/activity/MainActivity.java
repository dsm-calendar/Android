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
import android.widget.TextView;
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
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MainContract.View {

    private MainPagerAdapter adapter;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TextView userId;
    private TextView userClass;
    private ImageView profile;

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenter(this, new MainRepository(this));

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

        userId = drawerLayout.findViewById(R.id.tv_main_userid);
        userClass = drawerLayout.findViewById(R.id.tv_main_userclass);

        View header = navigationView.getHeaderView(0);
        profile = header.findViewById(R.id.profile_image);
        profile.setBackground(new ShapeDrawable(new OvalShape()));
        profile.setClipToOutline(true);
        userId = header.findViewById(R.id.tv_main_userid);
        userClass = header.findViewById(R.id.tv_main_userclass);

        setToolBar();

        mainPresenter.onStarted();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_toolbar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.item_toolbar_mail:
                Intent mailBoxIntent = new Intent(MainActivity.this, MessageActivity.class);
                startActivity(mailBoxIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.item_navigation_setting:
                break;
            case R.id.item_navigation_event:
                Intent makeNoticeIntent = new Intent(MainActivity.this, MakeNoticeActivity.class);
                startActivity(makeNoticeIntent);
                break;
            case R.id.item_navigation_timeTable:
                Intent timeTableIntent = new Intent(MainActivity.this, TimeTableActivity.class);
                startActivity(timeTableIntent);
                break;
            case R.id.item_navigation_calendar_school:
                Toast.makeText(this, "schoolCalendar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_navigation_calendar_my:
                viewPager.setCurrentItem(0);
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
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

    public void setToolBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_list);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setHomeButtonEnabled(true);
    }

    @Override
    public void setUserInfo(String id, int classOf, int iconIndex) {

    }
}
