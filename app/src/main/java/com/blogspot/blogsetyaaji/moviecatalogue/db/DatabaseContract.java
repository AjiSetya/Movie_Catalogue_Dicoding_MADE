package com.blogspot.blogsetyaaji.moviecatalogue.db;

import android.provider.BaseColumns;

public class DatabaseContract {
    static String TABLE_MOVIE = "tb_movie";
    static String TABLE_TV = "tb_tvshow";
    
    static final class MovieColumns implements BaseColumns {
        static String TITLE = "title";
        static String OVERVIEW = "overview";
        static String DATE = "date";
        static String POSTER = "poster";
    }

    static final class TvColumns implements BaseColumns {
        static String TITLE = "title";
        static String OVERVIEW = "overview";
        static String DATE = "date";
        static String POSTER = "poster";
    }
}
