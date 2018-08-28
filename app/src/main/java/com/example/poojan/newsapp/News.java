package com.example.poojan.newsapp;

public class News {
    private String title;
    private String description;
   // private long time;
    private String url;

    public News(String title,String description,String url)
    {
        this.title=title;
        this.description=description;
        this.url=url;
    }
    public String getTitle()
    {
        return title;
    }
    public String getdescription(){
        return description;
    }
    public String geturl()
    {
        return url;
    }

}
