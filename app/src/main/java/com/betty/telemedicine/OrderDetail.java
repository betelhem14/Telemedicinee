package com.betty.telemedicine;



import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderDetail extends AppCompatActivity {
    /*String[][] orderDetail = {};
    HashMap<String, String> item;
    ArrayList list;
    Button btn;
    SimpleAdapter sa;*/
    Database db;
    Button btn;
    ListView lst;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        btn = findViewById(R.id.buttonODBack);
        //lst = findViewById(R.id.ListviewOD);
        db = new Database(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderDetail.this, home.class));
            }
        });
        Cursor cr=db.getdata();
        StringBuffer buffer=new StringBuffer();
        while(cr.moveToNext()){
            buffer.append(cr.getString(0)+"\n");
            buffer.append("Date:"+cr.getString(4)+"\n");
            buffer.append("Time:"+cr.getString(5)+"\n\n");

        }
        AlertDialog.Builder builder=new AlertDialog.Builder(OrderDetail.this);
        builder.setTitle("Order Detail");
        builder.setMessage(buffer.toString());
        builder.setCancelable(true);
        builder.show();




      /*  SharedPreferences sp=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
     String doctername=sp.getString("doctername","").toString();
     ArrayList dbdata=db.getOrderData(doctername);
        orderDetail=new String[dbdata.size()][];

        for(int i=0;i<orderDetail.length;i++) {
            orderDetail[i]=new String[5];
            String arrdata = dbdata.get(i).toString();
            String[] strdata=arrdata.split(java.util.regex.Pattern.quote("$"));
            orderDetail[i][0]=strdata[0];
            orderDetail[i][1]=strdata[1];
            if (strdata[6].compareTo("hospital")==0){
                orderDetail[i][3]="del:"+strdata[4];
            }else {
                orderDetail[i][3]="del:"+strdata[4]+""+strdata[5];
            }
            orderDetail[i][2]="Rs."+strdata[6];
            orderDetail[i][4]=strdata[7];
        }
        list=new ArrayList();
        for(int i=0;i<orderDetail.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",orderDetail[i][0]);
            item.put("line2",orderDetail[i][1]);
            item.put("line3",orderDetail[i][2]);
            item.put("line4",orderDetail[i][3]);
            item.put("line5","Cons Fees:"+ orderDetail[i][4]+"/-");
            list.add(item);

        }
        sa=new SimpleAdapter(this,list,
                R.layout.multi_line,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);
*/
    }
}
