package hust.soict.haitv.monkey_shopping_android.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by haitv on 30-11-2017.
 */

public class RetrofitClient {
    public static final String BASE_URL = "http://192.168.1.160:3000/";
//    public static final String BASE_URL = "https://monkey-shopping.herokuapp.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
