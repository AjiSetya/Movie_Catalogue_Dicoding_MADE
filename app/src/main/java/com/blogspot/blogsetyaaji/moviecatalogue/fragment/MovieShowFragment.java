package com.blogspot.blogsetyaaji.moviecatalogue.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.blogspot.blogsetyaaji.moviecatalogue.R;
import com.blogspot.blogsetyaaji.moviecatalogue.activity.DetailActivity;
import com.blogspot.blogsetyaaji.moviecatalogue.adapter.MovieAdapter;
import com.blogspot.blogsetyaaji.moviecatalogue.model.movie.MovieItem;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieShowFragment extends Fragment {

    private MovieAdapter movieAdapter;
    private RecyclerView rvMovie;
    private ProgressBar pgMovie;


    public MovieShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        return getView() != null ? getView() :
                inflater.inflate(R.layout.fragment_movie_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MovieViewModel movieViewModel = ViewModelProviders.of(MovieShowFragment.this)
                .get(MovieViewModel.class);
        movieViewModel.getListMovies().observe(this, getMovie);

        rvMovie = view.findViewById(R.id.rv_movie);
        pgMovie = view.findViewById(R.id.pg_movie);


        movieAdapter = new MovieAdapter(getActivity());
        movieAdapter.notifyDataSetChanged();

        movieViewModel.setListMovies();
        showLoading(true);
        showRecyclerList();
    }

    private Observer<List<MovieItem>> getMovie = new Observer<List<MovieItem>>() {
        @Override
        public void onChanged(@Nullable List<MovieItem> movieItems) {
            if (movieItems != null) {
                movieAdapter.setListData(movieItems);
                showLoading(false);
            }
        }
    };

    private void showRecyclerList() {
        rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMovie.setAdapter(movieAdapter);

        movieAdapter.setOnItemClickCallback(new MovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(MovieItem data) {
                showSelectedMovie(data);
            }
        });
    }

    private void showSelectedMovie(MovieItem movie) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.KEY_DETAIL_DATA, movie);
        intent.putExtra(DetailActivity.KEY_JENIS_DATA, "movie");
        startActivity(intent);
    }

    private void showLoading(Boolean state) {
        if (state) {
            pgMovie.setVisibility(View.VISIBLE);
        } else {
            pgMovie.setVisibility(View.GONE);
        }
    }
}
