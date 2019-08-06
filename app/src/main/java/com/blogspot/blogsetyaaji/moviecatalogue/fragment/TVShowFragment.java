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
import com.blogspot.blogsetyaaji.moviecatalogue.adapter.TvAdapter;
import com.blogspot.blogsetyaaji.moviecatalogue.model.tv.TvItem;

import java.util.List;

public class TVShowFragment extends Fragment {

    private TvAdapter tvAdapter;
    private RecyclerView rvTv;
    private ProgressBar pgTv;

    public TVShowFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TvViewModel tvViewModel = ViewModelProviders.of(TVShowFragment.this)
                .get(TvViewModel.class);
        tvViewModel.getListTv().observe(this, getTv);

        rvTv = view.findViewById(R.id.rv_tv);
        pgTv = view.findViewById(R.id.pg_tv);

        tvAdapter = new TvAdapter(getActivity());
        tvAdapter.notifyDataSetChanged();

        tvViewModel.setListTv();
        showLoading(true);
        showRecyclerList();
    }

    private Observer<List<TvItem>> getTv = new Observer<List<TvItem>>() {
        @Override
        public void onChanged(@Nullable List<TvItem> tvItems) {
            if (tvItems != null) {
                tvAdapter.setListData(tvItems);
                showLoading(false);
            }
        }
    };

    private void showRecyclerList() {
        rvTv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvTv.setAdapter(tvAdapter);

        tvAdapter.setOnItemClickCallback(new TvAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(TvItem data) {
                showSelectedTv(data);
            }
        });
    }

    private void showSelectedTv(TvItem data) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.KEY_DETAIL_DATA, data);
        intent.putExtra(DetailActivity.KEY_JENIS_DATA, "tv");
        startActivity(intent);
    }

    private void showLoading(Boolean state) {
        if (state) {
            pgTv.setVisibility(View.VISIBLE);
        } else {
            pgTv.setVisibility(View.GONE);
        }
    }
}
