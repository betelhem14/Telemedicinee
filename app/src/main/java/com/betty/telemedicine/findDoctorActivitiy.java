package com.betty.telemedicine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
//import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

//import sharedPreferences.Editor;

public class findDoctorActivitiy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor_activitiy);
        CardView exit=findViewById(R.id.cardFDBack);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(findDoctorActivitiy.this,home.class));
            }
        });
        CardView familiyphysician=findViewById(R.id.cardFamiliyphysician);
        familiyphysician.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent it=new Intent(findDoctorActivitiy.this,DoctorDetailActivity.class);
                it.putExtra("title","Family physician");
                startActivity(it);
            }
        });
        CardView dietician=findViewById(R.id.cardFDditetian);
        dietician.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent it=new Intent(findDoctorActivitiy.this,DoctorDetailActivity.class);
                it.putExtra("title","Dietcian");
                startActivity(it);
            }
        });
        CardView dentist=findViewById(R.id.cardFDDentist);
        dentist.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent it=new Intent(findDoctorActivitiy.this,DoctorDetailActivity.class);
                it.putExtra("title","Surgeon");

                startActivity(it);
            }
        });
        CardView surgeon=findViewById(R.id.cardFDSurgion);
        surgeon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent it=new Intent(findDoctorActivitiy.this,DoctorDetailActivity.class);
                it.putExtra("title","Dentist");
                startActivity(it);
            }
        });
        CardView cardiologist=findViewById(R.id.cardFDCardio);
        cardiologist.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent it=new Intent(findDoctorActivitiy.this,DoctorDetailActivity.class);
                it.putExtra("title","Cardiologst");
                startActivity(it);
            }
        });
    }
}