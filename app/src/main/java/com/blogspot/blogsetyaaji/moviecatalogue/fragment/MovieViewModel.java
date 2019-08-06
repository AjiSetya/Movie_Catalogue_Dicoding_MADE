package com.blogspot.blogsetyaaji.moviecatalogue.fragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.blogspot.blogsetyaaji.moviecatalogue.model.movie.ResponseMovie;
import com.blogspot.blogsetyaaji.moviecatalogue.model.movie.MovieItem;
import com.blogspot.blogsetyaaji.moviecatalogue.network.ApiClient;
import com.blogspot.blogsetyaaji.moviecatalogue.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {
    private static final String API_KEY = "5616d8192ad5a4eda23fc61bc5324daf";
    private MutableLiveData<List<MovieItem>> listMovies = new MutableLiveData<>();

    MutableLiveData<List<MovieItem>> getListMovies() {
        return listMovies;
    }

    void setListMovies() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseMovie> movieCall = apiInterface.getMovies(API_KEY);
        movieCall.enqueue(new Callback<ResponseMovie>() {
            @Override
            public void onResponse(@NonNull Call<ResponseMovie> call, @NonNull Response<ResponseMovie> response) {
                if (response.body() != null) {
                    listMovies.postValue(response.body().getResults());
                    Log.d("onResponseMovie ", response.body().toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseMovie> call, @NonNull Throwable t) {
                Log.d("onFailureMovie ", t.getMessage());
            }
        });
    }
}
