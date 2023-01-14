package com.betty.telemedicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class healtharticledetail extends AppCompatActivity {
TextView txt;
ImageView img;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healtharticledetail);

        btn=findViewById(R.id.buttonhaBack);
            txt=findViewById(R.id.healthcarre);
            img=findViewById(R.id.imagehealth1);
        Intent intent=getIntent();
        txt.setText(intent.getStringExtra("text1"));
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            int resId=bundle.getInt("text2");
            img.setImageResource(resId);
        }
btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(healtharticledetail.this,healtharticle.class));
    }
});
    }
}