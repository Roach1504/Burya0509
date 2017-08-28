package uk.co.ribot.androidboilerplate.data.model.inUtilization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Talk {

    @SerializedName("talk")
    @Expose
    private Talk_ talk;

    public Talk_ getTalk() {
        return talk;
    }

    public void setTalk(Talk_ talk) {
        this.talk = talk;
    }

}
