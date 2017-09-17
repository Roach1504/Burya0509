package uk.co.ribot.androidboilerplate.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Comment {

    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("text")
    @Expose
    private String text;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
