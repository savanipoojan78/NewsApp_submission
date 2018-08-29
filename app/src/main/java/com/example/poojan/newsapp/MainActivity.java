package com.example.poojan.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>>{
    private static final String NEWS_URL="https://content.guardianapis.com/technology?api-key=test";
    private NewsAdapter mNews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNews = new NewsAdapter(this, new ArrayList<News>());
        ListView listView = (ListView) findViewById(R.id.news_list_item);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        listView.setAdapter(mNews);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News currentEarthquake = mNews.getItem(position);
                Uri earthquakeUri = Uri.parse(currentEarthquake.geturl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                startActivity(websiteIntent);
            }
        });

    }

    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new NewsLoader(this, NEWS_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> news) {
        if (news != null && !news.isEmpty()) {
            mNews.addAll(news);
            //updateUi(earthquakes);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {
    mNews.clear();
    }
}
