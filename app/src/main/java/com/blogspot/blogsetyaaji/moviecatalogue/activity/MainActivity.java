package com.blogspot.blogsetyaaji.moviecatalogue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.blogspot.blogsetyaaji.moviecatalogue.R;
import com.blogspot.blogsetyaaji.moviecatalogue.fragment.FavoriteFragment;
import com.blogspot.blogsetyaaji.moviecatalogue.fragment.MovieShowFragment;
import com.blogspot.blogsetyaaji.moviecatalogue.fragment.TVShowFragment;

public class MainActivity extends AppCompatActivity {

    private Fragment pageContent = new MovieShowFragment();
    public static final String KEY_FRAGMENT = "fragment";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_movie:
                    pageContent = new MovieShowFragment();
                    openFragment(pageContent);
                    return true;
                case R.id.navigation_tv:
                    pageContent = new TVShowFragment();
                    openFragment(pageContent);
                    return true;
                case R.id.navigation_fav:
                    pageContent = new FavoriteFragment();
                    openFragment(pageContent);
                    return true;
            }

            return false;
        }
    };

    void openFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        openFragment(new MovieShowFragment());

        if (savedInstanceState == null) {
            openFragment(pageContent);
        } else {
            pageContent = getSupportFragmentManager().getFragment(savedInstanceState, KEY_FRAGMENT);
            openFragment(pageContent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getSupportFragmentManager().putFragment(outState, KEY_FRAGMENT, pageContent);
        super.onSaveInstanceState(outState);
    }

}
