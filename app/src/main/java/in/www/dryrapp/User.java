package in.www.dryrapp;

import com.google.gson.annotations.SerializedName;
public class User {
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("fcmId")
    private String fcmId;
    @SerializedName("type")
    private String type;
    public User(String name, String email, String mobile, String password, String fcmId, String type) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.password =password;
        this.fcmId=fcmId;
        this.type=type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) { this.password = password;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getFcmId() {
        return fcmId;
    }
    public void setFcmId(String fcmId) {
        this.fcmId = fcmId;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}


