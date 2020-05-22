package com.dicoding.mylogin.rest;

import com.dicoding.mylogin.response.ResponseLogin;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseLogin> login(
        @Field("username") String username,
        @Field("password") String password
    );

    @FormUrlEncoded
    @POST("signup.php")
    Call<ResponseLogin> signup(
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email,
            @Field("phone_number") String phoneNumber,
            @Field("fullname") String fullname
    );
}
