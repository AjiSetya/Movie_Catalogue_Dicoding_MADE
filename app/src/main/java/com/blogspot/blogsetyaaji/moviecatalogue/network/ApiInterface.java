package com.blogspot.blogsetyaaji.moviecatalogue.network;

import com.blogspot.blogsetyaaji.moviecatalogue.model.movie.ResponseMovie;
import com.blogspot.blogsetyaaji.moviecatalogue.model.tv.ResponseTv;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("discover/movie")
    Call<ResponseMovie> getMovies(@Query("api_key") String apiKey);

    @GET("discover/tv")
    Call<ResponseTv> getTvShow(@Query("api_key") String apiKey);
}
