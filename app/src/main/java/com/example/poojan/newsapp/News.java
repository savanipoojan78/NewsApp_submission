package com.example.poojan.newsapp;

public class News {
    private String title;
    private String section_name;
   // private long time;
    private String url;

    public News(String title,String s_name,String url)
    {
        this.title=title;
        this.section_name=s_name;
        this.url=url;
    }
    public String getTitle()
    {
        return title;
    }
    public String getdescription(){
        return section_name;
    }
    public String geturl()
    {
        return url;
    }

}
