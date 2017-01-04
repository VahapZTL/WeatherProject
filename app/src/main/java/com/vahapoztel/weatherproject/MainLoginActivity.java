package com.vahapoztel.weatherproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vahapoztel.weatherproject.Data.LoginResponse;
import com.vahapoztel.weatherproject.Data.MyApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainLoginActivity extends Activity {

    public static String AccessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        final TextView email = (TextView) findViewById(R.id.editText);
        final TextView pass = (TextView) findViewById(R.id.editText2);
        Button loginBtn = (Button) findViewById(R.id.button);
        Button registerBtn = (Button) findViewById(R.id.button2);

        final Intent intent = new Intent(MainLoginActivity.this, RegisterActivity.class);
        final Intent intent2 = new Intent(MainLoginActivity.this, WeatherActivitiy.class);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<LoginResponse> call = startRetrofit().sendLogin(new loginPost(email.getText().toString()
                        ,pass.getText().toString()));

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        AccessToken = response.body().getData();
                        startActivity(intent2);
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.v("Error: ", t.getMessage());
                    }
                });
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
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

    public class loginPost{
        String email;
        String password;

        public loginPost(String email, String password){
            this.email = email;
            this.password = password;
        }
    }
}
