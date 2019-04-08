package com.example.socialappdevelopment.Home;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.socialappdevelopment.R;
import com.example.socialappdevelopment.Util.BottomNavViewHelper;
import com.example.socialappdevelopment.Util.SectionPagerAdapter;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class HomeActivity extends AppCompatActivity {
    public static final int ACTIVITY_NUM= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupBottomNavView();
        setupViewPager();
    }


    private void setupViewPager(){
        SectionPagerAdapter adapter= new SectionPagerAdapter(getSupportFragmentManager());
        adapter.addfragment(new CameraFragment());
        adapter.addfragment(new HomeFragment());
        adapter.addfragment(new MessageFragment());
        ViewPager viewPager= (ViewPager) findViewById(R.id.viewpager_container);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout= findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_house);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_send);
    }


    private void setupBottomNavView(){
        BottomNavigationViewEx bottomNavigationViewEx= findViewById(R.id.BottomNavViewBar);
        BottomNavViewHelper.setupBottomNavView(bottomNavigationViewEx);
        BottomNavViewHelper.enableNavigation(HomeActivity.this,bottomNavigationViewEx);
        Menu menu= bottomNavigationViewEx.getMenu();
        MenuItem menuItem= menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }


}
