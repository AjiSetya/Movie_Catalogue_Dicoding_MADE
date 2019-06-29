package com.blogspot.blogsetyaaji.moviecatalogue.Activity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.blogspot.blogsetyaaji.moviecatalogue.Model.Movie;
import com.blogspot.blogsetyaaji.moviecatalogue.MovieAdapter;
import com.blogspot.blogsetyaaji.moviecatalogue.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String[] titleMovie;
    private String[] yearMovie;
    private String[] descriptionMovie;
    private TypedArray posterMovie;
    private MovieAdapter movieAdapter;
    private ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieAdapter = new MovieAdapter(this);

        ListView lvMovie = findViewById(R.id.lv_movie);
        lvMovie.setAdapter(movieAdapter);

        prepare();
        addItem();

        lvMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailMovieActivity.class);
                intent.putExtra(DetailMovieActivity.KEY_DETAIL_DATA, movies.get(position));
                startActivity(intent);
            }
        });
    }

    private void prepare() {
        titleMovie = getResources().getStringArray(R.array.title_movie);
        yearMovie = getResources().getStringArray(R.array.year_movie);
        descriptionMovie = getResources().getStringArray(R.array.description_movie);
        posterMovie = getResources().obtainTypedArray(R.array.poster_movie);
    }

    private void addItem() {
        movies = new ArrayList<>();
        for (int i = 0; i < titleMovie.length; i++) {
            Movie movie = new Movie();
            movie.setPoster(posterMovie.getResourceId(i, -1));
            movie.setTitle(titleMovie[i]);
            movie.setYear(yearMovie[i]);
            movie.setDescription(descriptionMovie[i]);
            movies.add(movie);
        }
        movieAdapter.setMovies(movies);
    }
}
