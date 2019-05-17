package com.example.todoapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.todoapp.fragments.AboutUsFragment;
import com.example.todoapp.fragments.HomeFragment;
import com.example.todoapp.fragments.SectionPagerAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout drawerLayout;
    private NavigationView nav;
    private TextView tvTitle;
    private NoteViewModel noteViewModel;
    private SectionPagerAdapter sectionPagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        nav = findViewById(R.id.nav);
        tvTitle = findViewById(R.id.tvTitle);

        nav.findViewById(R.id.mnuHome).setOnClickListener(this);
        nav.findViewById(R.id.mnuAddTodo).setOnClickListener(this);
        nav.findViewById(R.id.mnuDelete).setOnClickListener(this);
        nav.findViewById(R.id.mnuAboutUs).setOnClickListener(this);
        nav.findViewById(R.id.mnuShare).setOnClickListener(this);

//        sectionPagerAdapter= new SectionPagerAdapter(getSupportFragmentManager());
//        viewPager= findViewById(R.id.container1);
//        setUpViewPager(viewPager);

//        TabLayout tabLayout= findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);


        showFragment(new HomeFragment(), "Home");

        noteViewModel= ViewModelProviders.of(this).get(NoteViewModel.class);

    }

//    public void setUpViewPager(ViewPager viewPager){
//        SectionPagerAdapter adapter= new SectionPagerAdapter(getSupportFragmentManager());
//        adapter.addFragment(new HomeFragment(),"Home");
//        adapter.addFragment(new AboutUsFragment(),"About");
//        viewPager.setAdapter(adapter);
//    }

    private void showFragment(Fragment fragment, String title) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        tvTitle.setText(title);
        drawerLayout.closeDrawer(Gravity.START);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mnuHome:
                showFragment(new HomeFragment(), "Home");
                break;
            case R.id.mnuAddTodo:
                startActivity(new Intent(this, AddNoteActivity.class));
                break;

            case R.id.mnuDelete:
                noteViewModel.deleteAllNotes();
                return;
            case R.id.mnuAboutUs:
                showFragment(new AboutUsFragment(), "About Us");
                break;
            case R.id.mnuShare:
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Share Now");
                String app_url = " https://play.google.com/store/apps/details?id=com.example.todoapp";
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, app_url);
                startActivity(Intent.createChooser(shareIntent, "Share via"));
                break;

        }
    }

    public void openDrawer(View view) {
        drawerLayout.openDrawer(Gravity.START);
    }
}