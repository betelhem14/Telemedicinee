package com.betty.telemedicine;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class cartBuyMedicine extends AppCompatActivity {
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    TextView tv;
    ListView lst;
    private DatePickerDialog date;
    private TimePickerDialog time;
    private Button dBtn,tBtn,btnCheckout,btnBack;
    private String[][] packages = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_buy_medicine);
        dBtn = findViewById(R.id.buttonCartDate);
        btnCheckout = findViewById(R.id.buttonCheckout);
        btnBack = findViewById(R.id.buttonCartBack);
        tv = findViewById(R.id.textViewCartTotal);
        lst = findViewById(R.id.listViewCart);

        SharedPreferences sharedPreferences =getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username=sharedPreferences.getString("username", "").toString();
        DataBase2 db = new DataBase2(cartBuyMedicine.this);
        float total = 0;
        ArrayList dbData = db.getCartData(username,"Medicine");

        packages = new String[dbData.size()][];
        for (int i=0; i<dbData.size();i++){
            packages[i] = new String[5];
        }
        for (int i=0; i<dbData.size();i++){
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            packages[i][0] = strData[0];
            packages[i][4] = "Cost :"+strData[1]+"/-";
            total = total+Float.parseFloat(strData[1]);
        }
        tv.setText("Total Cost :"+total);
        list = new ArrayList();
        for (int i=0; i<packages.length; i++){
            item = new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5",packages[i][4]);
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,R.layout.multi_line,
                new String[] {"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );
        lst.setAdapter(sa);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(cartBuyMedicine.this,BuyMedicine.class);
                in.putExtra("price",tv.getText());
                in.putExtra("date",dBtn.getText());
                startActivity(in);
            }
        });
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(cartBuyMedicine.this,BuyMedicineBook.class);
                it.putExtra("price",tv.getText());
                it.putExtra("date",dBtn.getText());
                startActivity(it);

            }
        });

        initDatePicker();
        dBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date.show();
            }
        });


    }
    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 =i2+1;
                dBtn.setText(i2+"/"+i1+"/"+i);

            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        //int style = AlertDialog.THEME_HOLD_DARK;
        date = new DatePickerDialog(this, AlertDialog.THEME_HOLO_DARK,dateSetListener,year,month,day);
        date.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);

    }
}
