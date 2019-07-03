package com.blogspot.blogsetyaaji.moviecatalogue.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.blogspot.blogsetyaaji.moviecatalogue.Fragment.MovieShowFragment;
import com.blogspot.blogsetyaaji.moviecatalogue.Fragment.TVShowFragment;
import com.blogspot.blogsetyaaji.moviecatalogue.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragement(new MovieShowFragment(), getResources().getString(R.string.movie));
        viewPagerAdapter.addFragement(new TVShowFragment(), getResources().getString(R.string.tv));

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(viewPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }
}