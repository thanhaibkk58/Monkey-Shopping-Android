package hust.soict.haitv.monkey_shopping_android.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by haitv on 29-11-2017.
 */

public class SharedPrefs {
    private static final String FILE_NAME = "data";
    private static final String ACCESS_TOKEN = "access_token";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPrefs(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setAccessToken(String token){
        editor.putString(ACCESS_TOKEN, token);
        editor.commit();
    }

    public String getAccessToken(){
        return sharedPreferences.getString(ACCESS_TOKEN, null);
    }

    public void setEmail(String email){
        editor.putString(EMAIL, email);
        editor.commit();
    }

    public String getEmail(){
        return sharedPreferences.getString(EMAIL, null);
    }

    public void setPassword(String password){
        editor.putString(PASSWORD, password);
        editor.commit();
    }

    public String getPassword(){
        return sharedPreferences.getString(PASSWORD, null);
    }
}
