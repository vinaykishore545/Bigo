package in.www.dryrapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DriverId {
    @SerializedName("response")
    @Expose
    private Integer response;
    @SerializedName("order")
    @Expose
    private Order order;

    public Integer getResponse() {
        return response;
    }

    public void setResponse(Integer response) {
        this.response = response;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
