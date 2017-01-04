package com.vahapoztel.weatherproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.vahapoztel.weatherproject.Data.LoginResponse;
import com.vahapoztel.weatherproject.Data.MyApi;
import com.vahapoztel.weatherproject.Data.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherActivitiy extends AppCompatActivity {

    TextView lon, lat, hava, derece;
    Button havaDurumu;
    EditText sehir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_activitiy);

        lon = (TextView) findViewById(R.id.textView8);
        lat = (TextView) findViewById(R.id.textView9);
        hava = (TextView) findViewById(R.id.textView11);
        derece = (TextView) findViewById(R.id.textView13);
        havaDurumu = (Button) findViewById(R.id.button5);
        sehir = (EditText) findViewById(R.id.editText6);

        havaDurumu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<WeatherResponse> call = startRetrofit().sendWeather(MainLoginActivity.AccessToken, sehir.getText().toString());

                call.enqueue(new Callback<WeatherResponse>() {
                    @Override
                    public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                        lon.setText(response.body().getData().getLon().toString());
                        lat.setText(response.body().getData().getLat().toString());
                        hava.setText(response.body().getData().getWeather());
                        derece.setText(response.body().getData().getTemp().toString());
                    }

                    @Override
                    public void onFailure(Call<WeatherResponse> call, Throwable t) {
                        Log.v("Error: ", t.getMessage());
                    }
                });
            }
        });

    }

    MyApi startRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyApi myApi = retrofit.create(MyApi.class);
        return myApi;
    }
}
