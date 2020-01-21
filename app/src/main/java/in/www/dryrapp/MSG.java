
package in.www.dryrapp;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MSG {

    @SerializedName("response")
    @Expose
    private Integer response;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("message")
    @Expose
    private String message;


    public Integer getResponse() {
        return response;
    }

    public void setResponse(Integer response) {
        this.response = response;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}