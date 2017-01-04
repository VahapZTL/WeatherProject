package com.vahapoztel.weatherproject.Data;


import com.vahapoztel.weatherproject.MainLoginActivity;
import com.vahapoztel.weatherproject.RegisterActivity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyApi {
    public static String BASE_URL = "https://weatherappbeykent.herokuapp.com/";

    @POST ("/api/login")
    Call<LoginResponse> sendLogin(@Body MainLoginActivity.loginPost a);

    @POST("/api/register")
    Call<RegisterResponse> sendRegister(@Body RegisterActivity.registerPost r);

    @GET("/api/weather/{sehir}")
    Call<WeatherResponse> sendWeather(@Header("Authorization") String token, @Path("sehir") String sehir);
}
