package com.dicoding.mylogin.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.dicoding.mylogin.model.User;
import com.dicoding.mylogin.response.ResponseLogin;
import com.dicoding.mylogin.rest.ApiClient;
import com.dicoding.mylogin.rest.ApiInterface;
import com.dicoding.mylogin.session.SessionManager;
import com.dicoding.mylogin.view.LoginActivity;
import com.dicoding.mylogin.view.MainActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoAppsViewModel extends ViewModel {
    private ApiInterface apiService;
    private Activity activity;
    private SessionManager sessionManager;

    private static final String TAG = GoAppsViewModel.class.getSimpleName();

    public GoAppsViewModel(Activity activity) {
        this.activity = activity;
    }

    public void login(String username, String password) {
        sessionManager = new SessionManager(activity);
        apiService = ApiClient.getClient().create(ApiInterface.class);

        apiService.login(username, password).enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                assert response.body() != null;
                if (response.body().getStatus().equals("success")) {
                    User userLoggedIn = response.body().getUser();
                    sessionManager.createLoginSession(userLoggedIn);

                    Intent intent = new Intent(activity, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                    activity.startActivity(intent);
                    activity.finish();
                    showMessage(response.body().getMessage());
                } else {
                    Log.d(TAG, "onResponse: " + response.body().getMessage());
                    showMessage(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
                showMessage("Gagal terhubung ke server!");
            }
        });
    }

    public void register(String username, String password, String email, String phoneNumber, String fullname) {
        apiService = ApiClient.getClient().create(ApiInterface.class);

        apiService.signup(username, password, email, phoneNumber, fullname).enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.body().getStatus().equals("success")) {
                    Intent intent = new Intent(activity, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    activity.startActivity(intent);
                    activity.finish();
                    showMessage(response.body().getMessage());
                    Log.e(TAG, "onResponse: " + response.body().getMessage());
                } else {
                    Log.d(TAG, "onResponse: " + response.body().getMessage());
                    showMessage(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                showMessage("Gagal terhubung ke server!");
            }
        });
    }

    private void showMessage(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }
}
