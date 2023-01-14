package com.betty.telemedicine;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

//import sharedPreferences.Editor;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username=sharedPreferences.getString("username", "").toString();
        Toast.makeText(getApplicationContext(),"welcome" +username,Toast.LENGTH_SHORT).show();
        CardView exit=findViewById(R.id.cardExit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
                SharedPreferences.Editor editor;
                editor = (SharedPreferences.Editor) sharedPreferences.edit();
                ((SharedPreferences.Editor) editor).clear();
                ((SharedPreferences.Editor) editor).apply();
                startActivity(new Intent(home.this,LoginActivity.class));
            }
        });
        CardView findDoctor=findViewById(R.id.cardFindDoctor);
        findDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this,findDoctorActivitiy.class));

            }
        });
        CardView detail=findViewById(R.id.cardOrderdetail);
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this,OrderDetail.class));
            }
        });
        CardView health=findViewById(R.id.cardHealthCare);
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this,healtharticle.class));
            }
        });
        CardView maps=findViewById(R.id.cardLabTest);
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this,MapsActivity.class));
            }
        });
        CardView buy=findViewById(R.id.cardBuyMedicine);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this,BuyMedicine.class));
            }
        });

    }


}