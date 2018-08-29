package com.example.poojan.newsapp;

import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import java.util.List;

class NewsLoader extends AsyncTaskLoader<List<News>> {
    private String murl;
    public NewsLoader(MainActivity mainActivity, String newsUrl) {
        super(mainActivity);
        murl=newsUrl;

    }
    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
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
