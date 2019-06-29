package com.blogspot.blogsetyaaji.moviecatalogue.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.blogsetyaaji.moviecatalogue.Model.Movie;
import com.blogspot.blogsetyaaji.moviecatalogue.R;

public class DetailMovieActivity extends AppCompatActivity {
    public static String KEY_DETAIL_DATA = "detail_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        TextView txtDetailTitle = findViewById(R.id.txt_detail_title);
        TextView txtDetailYear = findViewById(R.id.txt_detail_year);
        TextView txtDetailDescription = findViewById(R.id.txt_detail_description);
        ImageView imgDetailPoster = findViewById(R.id.img_detail_poster);

        Movie movie = getIntent().getParcelableExtra(KEY_DETAIL_DATA);
        txtDetailTitle.setText(movie.getTitle());
        txtDetailYear.setText(movie.getYear());
        txtDetailDescription.setText(movie.getDescription());
        imgDetailPoster.setImageResource(movie.getPoster());
    }
}
