package uk.co.ribot.androidboilerplate.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class NewsModel {
    @SerializedName("")
    // TODO: 05.09.2017  передать параметр, переписать презентр, уточнить у Данила 
    @Expose
private List<ItemNewList> news = null;

    public List<ItemNewList> getNews() {
        return news;
    }

    public void setNews(List<ItemNewList> news) {
        this.news = news;
    }

}

