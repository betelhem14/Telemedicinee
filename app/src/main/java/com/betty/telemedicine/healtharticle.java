package com.betty.telemedicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class healtharticle extends AppCompatActivity {
private String[][] health={{"Walking Daily","","","Click more Detail"},

        {"Home Care of COVID-19","","","Click more detail"},
        {"Stop Smoking","","","Click more detail"},
        {"Healthy Gut","","","Click more detail"}


};
private int[] image={R.drawable.health1,
            R.drawable.health2,
            R.drawable.health3,
            R.drawable.health5
    };
HashMap<String,String> item;
ArrayList list;
SimpleAdapter sa;
Button btnback;
ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healtharticle);
        lst=findViewById(R.id.ListviewHD);
        btnback=findViewById(R.id.buttonOHBack);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(healtharticle.this,home.class));


            }
        });
        list=new ArrayList();
        for(int i=0;i<health.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",health[i][0]);
            item.put("line2",health[i][1]);
            item.put("line3",
                    health[i][2]);
            item.put("line5",health[i][3]);
            list.add(item);

        }


        sa=new SimpleAdapter(this,list,
                R.layout.multi_line,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(healtharticle.this,healtharticledetail.class);

                it.putExtra("text1",health[i][0]);
                it.putExtra("text2",image[i]);

                startActivity(it);

            }
        });


    }
}