package com.vahapoztel.weatherproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vahapoztel.weatherproject.Data.MyApi;
import com.vahapoztel.weatherproject.Data.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    public static String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final TextView name = (TextView) findViewById(R.id.editText3);
        final TextView email = (TextView) findViewById(R.id.editText5);
        final TextView pass = (TextView) findViewById(R.id.editText4);
        Button registerBtn = (Button) findViewById(R.id.button3);
        Button loginBtn = (Button) findViewById(R.id.button4);

        final Intent intent = new Intent(RegisterActivity.this, MainLoginActivity.class);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<RegisterResponse> call = startRetrofit().sendRegister(new registerPost(name.getText().toString(),
                        email.getText().toString(),
                        pass.getText().toString()));

                call.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        Name = response.body().getData().getName();
                        startActivity(intent);
                        Toast.makeText(RegisterActivity.this, "Login Suucessfully!" , Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        Log.v("Response", t.getMessage());
                    }
                });
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
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

    public class registerPost{
        String name;
        String email;
        String password;

        public registerPost(String name, String email, String password){
            this.name = name;
            this.email = email;
            this.password = password;
        }
    }
}
