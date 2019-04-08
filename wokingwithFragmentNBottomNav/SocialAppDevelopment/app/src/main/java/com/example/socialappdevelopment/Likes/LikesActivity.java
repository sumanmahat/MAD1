package com.example.socialappdevelopment.Likes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.socialappdevelopment.Home.HomeActivity;
import com.example.socialappdevelopment.R;
import com.example.socialappdevelopment.Util.BottomNavViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class LikesActivity extends AppCompatActivity {
    public static final int ACTIVITY_NUM= 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_likes);
        setupBottomNavView();
    }


    private void setupBottomNavView(){
        BottomNavigationViewEx bottomNavigationViewEx= findViewById(R.id.BottomNavViewBar);
        BottomNavViewHelper.setupBottomNavView(bottomNavigationViewEx);
        BottomNavViewHelper.enableNavigation(LikesActivity.this,bottomNavigationViewEx);
        Menu menu= bottomNavigationViewEx.getMenu();
        MenuItem menuItem= menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}
