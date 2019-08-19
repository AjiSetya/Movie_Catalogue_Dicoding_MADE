package com.blogspot.blogsetyaaji.moviecatalogue.fragment;

import com.blogspot.blogsetyaaji.moviecatalogue.model.movie.MovieItem;

import java.util.ArrayList;

public interface LoadMoviesCallback {
    void preExecute();

    void postExecute(ArrayList<MovieItem> movieItems);
}
