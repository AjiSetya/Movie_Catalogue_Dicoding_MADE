package com.blogspot.blogsetyaaji.moviecatalogue.Fragment;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blogspot.blogsetyaaji.moviecatalogue.Adapter.MovieAdapter;
import com.blogspot.blogsetyaaji.moviecatalogue.Model.Movie;
import com.blogspot.blogsetyaaji.moviecatalogue.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieShowFragment extends Fragment {

    private String[] titleMovie;
    private String[] yearMovie;
    private String[] descriptionMovie;
    private TypedArray posterMovie;
    private ArrayList<Movie> movieArrayList;
    private MovieAdapter movieAdapter;


    public MovieShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_show, container, false);
    }

    private void addItem() {
        movieArrayList = new ArrayList<>();
        for (int a = 0; a < titleMovie.length; a++) {
            Movie movie = new Movie();
            movie.setTitle(titleMovie[a]);
            movie.setYear(yearMovie[a]);
            movie.setDescription(descriptionMovie[a]);
            movie.setPoster(posterMovie.getResourceId(a, -1));
            movieArrayList.add(movie);
        }

        movieAdapter.setListData(movieArrayList);
    }

    private void prepareData() {
        titleMovie = getActivity().getResources().getStringArray(R.array.title_movie);
        descriptionMovie = getActivity().getResources().getStringArray(R.array.description_movie);
        yearMovie = getActivity().getResources().getStringArray(R.array.year_movie);
        posterMovie = getActivity().getResources().obtainTypedArray(R.array.poster_movie);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieAdapter = new MovieAdapter(getActivity());

        prepareData();
        addItem();

        RecyclerView rvMovie = view.findViewById(R.id.rv_movie);
        rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMovie.setAdapter(movieAdapter);

    }
}
