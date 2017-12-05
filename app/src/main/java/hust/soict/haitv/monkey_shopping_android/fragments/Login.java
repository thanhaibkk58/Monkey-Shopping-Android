package hust.soict.haitv.monkey_shopping_android.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hust.soict.haitv.monkey_shopping_android.R;
import hust.soict.haitv.monkey_shopping_android.activities.MainActivity;
import hust.soict.haitv.monkey_shopping_android.interfaces.UserInterface;
import hust.soict.haitv.monkey_shopping_android.models.Auth;
import hust.soict.haitv.monkey_shopping_android.network.RetrofitClient;
import hust.soict.haitv.monkey_shopping_android.utils.NotificationDialog;
import hust.soict.haitv.monkey_shopping_android.utils.SharedPrefs;
import hust.soict.haitv.monkey_shopping_android.utils.StatusDialog;
import hust.soict.haitv.monkey_shopping_android.utils.StatusProgressDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by haitv on 01-12-2017.
 */

public class Login extends Fragment {
    private static final String TAG = Login.class.getSimpleName();

    EditText edt_email, edt_password;
    Button btn_login;
    AppCompatCheckBox checkBox;

    StatusDialog mStatusDialog;

    UserInterface userInterface;
    SharedPrefs sharedPrefs;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auth_login, container, false);

        edt_email = view.findViewById(R.id.edt_auth_login_email);
        edt_password = view.findViewById(R.id.edt_auth_login_password);

        btn_login = view.findViewById(R.id.btn_auth_login);
        checkBox = view.findViewById(R.id.checkbox_login);

        mStatusDialog = new StatusDialog("Signing in...", getActivity());
        sharedPrefs = new SharedPrefs(getActivity());
        userInterface = RetrofitClient.getClient().create(UserInterface.class);

        edt_email.setText(sharedPrefs.getEmail());

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edt_email.getText().toString().trim().toLowerCase();
                String password = edt_password.getText().toString().trim();

                if (email.equals("") || password.equals("")){
                    new NotificationDialog("Data field not allowed to be empty!", getActivity());
                    return;
                }

                String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(email);
                if (!matcher.matches()) {
                    new NotificationDialog("Email not valid!", getActivity());
                    return;
                }

                if (password.length() < 6){
                    new NotificationDialog("Password too short!", getActivity());
                    return;
                }

                mStatusDialog.showStatusDialog();

                login(email, password);
            }
        });

        return view;
    }

    private void login(String email, String password){
        userInterface.login(email, password).enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                mStatusDialog.dismissStatusDialog();
                int statusCode = response.code();
                Log.e(TAG, "onResponse: " + statusCode);
                if (statusCode == 200) {
                    mStatusDialog.dismissStatusDialog();
                    sharedPrefs.setAccessToken(response.body().getToken());
                    if (checkBox.isChecked()) {
                        sharedPrefs.setEmail(response.body().getUser().getEmail());
                    } else {
                        sharedPrefs.setEmail("");
                    }
                    startActivity(new Intent(getContext(), MainActivity.class));
                    ((Activity)getContext()).finish();
                } else {
                    new StatusProgressDialog(statusCode, getActivity());
                }
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                new StatusProgressDialog(0, getActivity());
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
}
