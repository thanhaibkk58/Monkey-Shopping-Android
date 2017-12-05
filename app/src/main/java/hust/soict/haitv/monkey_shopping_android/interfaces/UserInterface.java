package hust.soict.haitv.monkey_shopping_android.interfaces;

import hust.soict.haitv.monkey_shopping_android.models.Auth;
import hust.soict.haitv.monkey_shopping_android.models.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by haitv on 30-11-2017.
 */

public interface UserInterface {
    @POST("api/signup")
    @FormUrlEncoded
    Call<Auth> signup(@Field("email") String email,
                      @Field("firstname") String firstname,
                      @Field("lastname") String lastname,
                      @Field("password") String password);

    @POST("api/login")
    @FormUrlEncoded
    Call<Auth> login(@Field("email") String email,
                     @Field("password") String password);

    @GET("api/me")
    Call<User> verify(@Header("x-access-token") String token);
}
