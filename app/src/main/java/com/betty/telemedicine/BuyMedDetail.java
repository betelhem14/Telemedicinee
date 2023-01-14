package com.betty.telemedicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyMedDetail extends AppCompatActivity {
    TextView tv,tv2;
    EditText et;
    Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_med_detail);
        tv = findViewById(R.id.textViewPackageName);
        tv2 = findViewById(R.id.textViewTotalCost);
        et = findViewById(R.id.editTextTextMultiline);
        et.setKeyListener(null);
        btn1 = findViewById(R.id.buttonGoBackBuy);
        btn2 = findViewById(R.id.buttonAddToCart);

        Intent in = getIntent();
        tv.setText(in.getStringExtra("text1"));
        et.setText(in.getStringExtra("text2"));
        tv2.setText("Total Cost :" + in.getStringExtra("text3")+ "/-");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedDetail.this,BuyMedicine.class));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences =getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username", "").toString();
                String product = tv.getText().toString();
                float price = Float.parseFloat(in.getStringExtra("text3").toString());
                DataBase2 db = new DataBase2(BuyMedDetail.this);

                if (db.checkCart(username,product)==1){
                    Toast.makeText(BuyMedDetail.this, "Product already added", Toast.LENGTH_SHORT).show();
                }else{
                    db.addCart(username,product,price,"Medicine");
                    Toast.makeText(BuyMedDetail.this, "Medicine added to cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedDetail.this,BuyMedicine.class));
                }
            }
        });



    }
}