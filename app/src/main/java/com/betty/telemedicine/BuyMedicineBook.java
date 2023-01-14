package com.betty.telemedicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuyMedicineBook extends AppCompatActivity {
    EditText edName,edAddress,edContact,edPincode;
    Button btnBooking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);

        edName = findViewById(R.id.editTextFullName);
        edAddress = findViewById(R.id.editTextAddress);
        edContact = findViewById(R.id.editTextContact);
        edPincode = findViewById(R.id.editTextPincode);
        btnBooking = findViewById(R.id.buttonBooking);

        Intent it = getIntent();
        String[] price = it.getStringExtra("price").split(java.util.regex.Pattern.quote("$"));
        String date = it.getStringExtra("date");


        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "");

                DataBase2 db = new DataBase2(BuyMedicineBook.this);
                db.addOrder("Betelhem",edName.getText().toString(),edAddress.getText().toString(),edContact.getText().toString(),Integer.parseInt( edPincode.getText().toString()),"10-2-2023","",100,"Medicine");
                db.removeCart("Betelhem","Medicine");
                Toast.makeText(BuyMedicineBook.this, "Your Booking is done Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(BuyMedicineBook.this,home.class));
            }
        });

    }
}
