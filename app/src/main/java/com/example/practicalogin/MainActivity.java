package com.example.practicalogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText username, pass;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.correoText);
        pass = findViewById(R.id.ContraText);
        btnLogin = findViewById(R.id.bEntrar);
        
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(pass.getText().toString())){
                    Toast.makeText(MainActivity.this, "Usuario o clave vacio", Toast.LENGTH_LONG).show();
                }
                else{
                    // login
                    login();
                }
            }
        });

    }

    public void login(){
        loginRequest loginRequest = new loginRequest();
        loginRequest.setUsername(username.getText().toString());
        loginRequest.setPassword(pass.getText().toString());

        Call<loginResponse> loginResponseCall = apiClient.getUserService().userlogin(loginRequest);
        loginResponseCall.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {

                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "inicio de sesion correcto", Toast.LENGTH_LONG).show();
                    loginResponse loginResponse = response.body();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            startActivity(new Intent(MainActivity.this,vistaDatos.class).putExtra("data",loginResponse.getMail()));
                        }
                    }, 700);
                }
                else{
                    Toast.makeText(MainActivity.this, "inicio de sesion incorrecto", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Throwable"+ t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

}