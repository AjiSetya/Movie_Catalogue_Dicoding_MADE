package com.blogspot.blogsetyaaji.moviecatalogue.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blogspot.blogsetyaaji.moviecatalogue.R;
import com.blogspot.blogsetyaaji.moviecatalogue.activity.ViewPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {


    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return getView() != null ? getView() :
                inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        Toolbar toolbar = view.findViewById(R.id.toolbar);
//        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragement(new FavoriteMovieFragment(), getResources().getString(R.string.movie));
        viewPagerAdapter.addFragement(new FavoriteTvFragment(), getResources().getString(R.string.tv));

        ViewPager viewPager = view.findViewById(R.id.pager_favorite);
        viewPager.setAdapter(viewPagerAdapter);

        TabLayout tabs = view.findViewById(R.id.tab_favorite);
        tabs.setupWithViewPager(viewPager);
    }
}
