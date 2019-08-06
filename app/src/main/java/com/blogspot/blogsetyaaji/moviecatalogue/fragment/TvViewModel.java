package com.blogspot.blogsetyaaji.moviecatalogue.fragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.blogspot.blogsetyaaji.moviecatalogue.model.tv.ResponseTv;
import com.blogspot.blogsetyaaji.moviecatalogue.model.tv.TvItem;
import com.blogspot.blogsetyaaji.moviecatalogue.network.ApiClient;
import com.blogspot.blogsetyaaji.moviecatalogue.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvViewModel extends ViewModel {
    private static final String API_KEY = "5616d8192ad5a4eda23fc61bc5324daf";
    private MutableLiveData<List<TvItem>> listTvs = new MutableLiveData<>();

    MutableLiveData<List<TvItem>> getListTv() {
        return listTvs;
    }

    void setListTv() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseTv> tvCall = apiInterface.getTvShow(API_KEY);
        tvCall.enqueue(new Callback<ResponseTv>() {
            @Override
            public void onResponse(@NonNull Call<ResponseTv> call, @NonNull Response<ResponseTv> response) {
                if (response.body() != null) {
                    listTvs.postValue(response.body().getResults());
                    Log.d("onResponseTv ", response.body().toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseTv> call, @NonNull Throwable t) {
                Log.d("onFailureTv ", t.getMessage());
            }
        });
    }
}
