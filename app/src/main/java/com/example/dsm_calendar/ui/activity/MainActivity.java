package com.example.dsm_calendar.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.dsm_calendar.ui.dialog.LogoutDialog;
import com.example.dsm_calendar.ui.dialog.SetProfileDialog;
import com.example.dsm_calendar.util.DialogListener;
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

    private SetProfileDialog setProfileDialog;
    private LogoutDialog logoutDialog;

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
        userId = header.findViewById(R.id.tv_main_userid);
        userClass = header.findViewById(R.id.tv_main_userclass);

        setProfileDialog = new SetProfileDialog(this);
        setProfileDialog.setSetProfileDialogListener(this::setProfileImage);
        logoutDialog = new LogoutDialog(this);
        logoutDialog.setLogoutDialogListener(new DialogListener.LogoutDialogListener() {
            @Override
            public void onConfirmClicked() {
                mainPresenter.onClickLogout();
            }
        });

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
                setProfileDialog.show();
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
                logoutDialog.show();
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
        userId.setText(id);
        userClass.setText(Integer.toString(classOf));
        setProfileImage(iconIndex);
    }

    @Override
    public void onFailGetUserInfo() {
        Toast.makeText(this, "유저 정보를 불러오는데 실패했습니다.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void logout() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    void setProfileImage(int iconIndex){
        switch (iconIndex){
            case 0:
                profile.setImageResource(R.drawable.ic_sprout);
                break;
            case 1:
                profile.setImageResource(R.drawable.ic_person_w);
                break;
            case 2:
                profile.setImageResource(R.drawable.ic_person_m);
                break;
            case 3:
                profile.setImageResource(R.drawable.ic_school);
                break;
        }
        mainPresenter.onProfileChanged(iconIndex);
    }
}
