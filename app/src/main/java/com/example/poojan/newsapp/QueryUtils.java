package com.example.poojan.newsapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class QueryUtils {
    private QueryUtils(){

    }
    public static List<News> fetchNews(String murl) {
        URL url= null;
        try {
            url = new URL(murl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String jsonResponce="";
        jsonResponce=makeHttpRequest(url);
        List<News> news=extractjson(jsonResponce);
        return news;

    }

    private static List<News> extractjson(String jsonResponce) {
        if(TextUtils.isEmpty(jsonResponce))
        {
            return null;
        }
        List<News> news=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(jsonResponce);
            JSONArray newsArray = jsonObject.getJSONArray("results");
            for(int i=0;i<newsArray.length();i++)
            {
                JSONObject currentnews=newsArray.getJSONObject(i);
                String title=currentnews.getString("webTitle");
                String sectionName=currentnews.getString("sectionName");
                String url=currentnews.getString("webUrl");
                News one_news=new News(title,sectionName,url);
                news.add(one_news);


            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }
        return news;
    }

    private static String makeHttpRequest(URL url) {
        String jsonResponce="";
        if(url==null)
            return jsonResponce;
        HttpURLConnection urlConnection=null;
        InputStream inputStream=null;
        try {
            urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.setReadTimeout(1000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if(urlConnection.getResponseCode()==200)
            {
                inputStream=urlConnection.getInputStream();
                jsonResponce=readFromStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResponce;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output=new StringBuilder();
        if(inputStream!=null)
        {
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String line=bufferedReader.readLine();
            while(line!=null)
            {
                output.append(line);
                line=bufferedReader.readLine();
            }

        }
        return output.toString();
    }


}
