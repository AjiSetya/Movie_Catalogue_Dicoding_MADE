package com.blogspot.blogsetyaaji.moviecatalogue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.blogsetyaaji.moviecatalogue.Model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {

    private ArrayList<Movie> movies;
    private Context context;

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public MovieAdapter(Context context) {
        this.context = context;
        movies = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        }

        ViewHolder viewHolder = new ViewHolder(convertView);
        Movie movie = (Movie) getItem(position);
        viewHolder.bind(movie);
        return convertView;
    }

    private class ViewHolder {
        private TextView txtTitle, txtYear;
        private ImageView imgPoster;

        ViewHolder(View view) {
            txtTitle = view.findViewById(R.id.txt_title);
            txtYear = view.findViewById(R.id.txt_year);
            imgPoster = view.findViewById(R.id.img_poster);
        }

        void bind(Movie movie) {
            txtTitle.setText(movie.getTitle());
            txtYear.setText(movie.getYear());
            imgPoster.setImageResource(movie.getPoster());
        }
    }
}
