package hust.soict.haitv.monkey_shopping_android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import hust.soict.haitv.monkey_shopping_android.R;
import hust.soict.haitv.monkey_shopping_android.interfaces.UserInterface;
import hust.soict.haitv.monkey_shopping_android.models.User;
import hust.soict.haitv.monkey_shopping_android.network.RetrofitClient;
import hust.soict.haitv.monkey_shopping_android.utils.SharedPrefs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    SharedPrefs sharedPrefs;
    UserInterface userInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPrefs = new SharedPrefs(getApplicationContext());
        Log.e(TAG, "TOKEN: " + sharedPrefs.getAccessToken());
        String token = sharedPrefs.getAccessToken();

        userInterface = RetrofitClient.getClient().create(UserInterface.class);
        checkLogin(token);
    }

    private void checkLogin(String token) {
        userInterface.verify(token).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    Toast.makeText(MainActivity.this, "Login", Toast.LENGTH_LONG).show();
                } else {
                    startActivity(new Intent(MainActivity.this, AuthActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
}
