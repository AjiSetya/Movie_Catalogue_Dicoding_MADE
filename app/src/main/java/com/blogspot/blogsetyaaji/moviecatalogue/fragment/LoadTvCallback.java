package com.blogspot.blogsetyaaji.moviecatalogue.fragment;

import com.blogspot.blogsetyaaji.moviecatalogue.model.movie.MovieItem;
import com.blogspot.blogsetyaaji.moviecatalogue.model.tv.TvItem;

import java.util.ArrayList;

public interface LoadTvCallback {
    void preExecute();

    void postExecute(ArrayList<TvItem> tvItems);
}
