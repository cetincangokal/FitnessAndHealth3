package com.example.fitnessandhealth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class FitnessActivity extends AppCompatActivity {
    private AppCompatButton serbestAgirlik,vucutAgirligi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness);

        serbestAgirlik = findViewById(R.id.serbest_Btn);
        vucutAgirligi = findViewById(R.id.vücut_Btn);

        serbestAgirlik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FitnessActivity.this, serbestActivity.class));
            }
        });
        vucutAgirligi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FitnessActivity.this, vucutActivity.class));
            }
        });

    }

}