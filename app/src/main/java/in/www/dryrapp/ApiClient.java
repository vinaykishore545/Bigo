package in.www.dryrapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import in.www.dryrapp.ui.slideshow.OrderOverviewFragment;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static  final  String BASE_URL= "http://dryvr.biz/api/";
    private static Retrofit retrofit = null;
    String st4;

    public static Retrofit getClient() {
        if (retrofit==null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }


}
