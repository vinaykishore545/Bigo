package in.www.dryrapp;

import java.util.List;

import in.www.dryrapp.ui.slideshow.OrderOverviewFragment;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface Api {
      @FormUrlEncoded
    @POST("signin/register")
    Call<List<MSG>> createUser(
              @Field("name") String name,
              @Field("email") String email,
              @Field("mobile") String mobile,
              @Field("password") String password,
              @Field("fcmId") String fcmId,
              @Field("type") String type

      );

      @FormUrlEncoded
      @POST("signin")
   Call<List<Login>>creatLogin(
              @Field("mobile") String mobile,
              @Field("password") String password
      );
    @GET("personalusecategories")
    Call<List<Personal>> getPersonal();

    @GET("businesscategories")
    Call<List<Bussiness>> getBussiness();


    @FormUrlEncoded
    @POST("order/bookings")
    Call<List<Example>>creatExample(
            @Field("user_id") String id
    );


    @FormUrlEncoded
    @POST("order")
    Call<List<Orderview>>creatOrder(
                  @Field("user_id") String id,
                   @Field("pickup") String pickcar,
                    @Field("drop") String drop,
                    @Field("service_id") String service_id,
                    @Field("date") String date,
                    @Field("time") String time,
                    @Field("duration") String duration,
                    @Field("car_name") String car_name,
                    @Field("car_number") String car_number,
                    @Field("car_type") String car_type,
                    @Field("car_location") String car_location,
                    @Field("price") String price
    );


   /* @POST
    Call<List<DriverId>>creatDriver(@Url String url,
             @Field("user_id") String id
    );*/

}
