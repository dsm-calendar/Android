package com.example.dsm_calendar.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

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

import com.example.dsm_calendar.ui.adapter.MainPagerAdapter;
import com.example.dsm_calendar.R;
import com.example.dsm_calendar.ui.dialog.AuthCodeDialog;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    AuthCodeDialog authCodeDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.main_viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);

        toolbar = findViewById(R.id.main_toolbar);

        TabLayout tabLayout = findViewById(R.id.main_tabBar);
        tabLayout.setupWithViewPager(viewPager);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.main_navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        ImageView profile = header.findViewById(R.id.profile_image);
        profile.setBackground(new ShapeDrawable(new OvalShape()));
        profile.setClipToOutline(true);

        authCodeDialog = new AuthCodeDialog(this, offButtonListener, checkButtonListener);

        setToolBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_toolbar_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.mail:
                Intent intent = new Intent(MainActivity.this, MessageActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.item_setting:
                Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_code:
                authCodeDialog.show();
                break;
            case R.id.item_event:
                Toast.makeText(this, "event", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_timetable:
                Toast.makeText(this, "timetable", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_school_calendar:
                Toast.makeText(this, "school calendar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_group_calendar:
                Toast.makeText(this, "group calendar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_my_calendar:
                Toast.makeText(this, "my calendar", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_logout:
                Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
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
}