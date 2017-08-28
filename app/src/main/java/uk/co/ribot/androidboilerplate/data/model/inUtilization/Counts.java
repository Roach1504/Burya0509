package uk.co.ribot.androidboilerplate.data.model.inUtilization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Counts {

    @SerializedName("this")
    @Expose
    private Integer _this;
    @SerializedName("total")
    @Expose
    private Integer total;

    public Integer getThis() {
        return _this;
    }

    public void setThis(Integer _this) {
        this._this = _this;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
