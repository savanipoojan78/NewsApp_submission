package com.example.poojan.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<News>{

    public NewsAdapter(@NonNull Context context, List<News> news) {
        super(context,0, news);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.news_list_item,parent,false);
        }
        News news=getItem(position);
        TextView title=(TextView)listItemView.findViewById(R.id.title);
        TextView description=(TextView)listItemView.findViewById(R.id.detail);
        assert news != null;
        title.setText(news.getTitle());
        description.setText(news.getdescription());
        return listItemView;
    }
}
