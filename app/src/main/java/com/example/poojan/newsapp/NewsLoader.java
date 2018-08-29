package com.example.poojan.newsapp;


import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    private String murl;
    public NewsLoader(Context context, String newsUrl) {
        super(context);
        murl=newsUrl;

    }
    @Override
    protected void onStartLoading() {
        forceLoad();
    }


    @Override
    public List<News> loadInBackground() {
        if (murl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<News> earthquakes = QueryUtils.fetchNews(murl);
        return earthquakes;
    }
}
