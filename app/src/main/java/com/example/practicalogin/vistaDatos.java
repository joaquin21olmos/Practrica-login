package com.example.practicalogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class vistaDatos extends AppCompatActivity {

    TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_datos);

        username = findViewById(R.id.username);
        Intent intent = getIntent();


        if(intent.getExtras() != null){
            String passedUSername = intent.getStringExtra("data");
            username.setText("Bienvenido: " + passedUSername);

        }
    }
}