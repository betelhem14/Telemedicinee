package com.betty.telemedicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailActivity extends AppCompatActivity {

private String[][]doctor_detail1={
        {"Doctor Name: Ali Hussen","Hospital Adress:Harer General Hospital","Exp:5yrs","Mobile_num:09_23_83_87_24","600"},
        {"Doctor Name: Kdst Solomon","Hospital Adress:DireDawa General Hospital ","Exp:3yrs","Mobile_num:09_54_54_87_24","900"},
        {"Doctor Name: Aby Abebe ","Hospital Adress: Zewditu hospital ","Exp:6yrs","Mobile_num:09_65_83_07_24","300"},
        {"Doctor Name: Jemal Kedir ","Hospital Adress:Yeka General Hospital  ","Exp:6yrs","Mobile_num:09_33_83_80_24","500"},
        {"Doctor Name: Abel Gashaw ","Hospital Adress:Harer General Hospital","Exp:2","Mobile_num:09_21_83_67_24","800"}

};
    private String[][]doctor_detail2={
            {"Doctor Name: Kemal Hussen","Hospital Adress:Harer General Hospital","Exp:5yrs","Mobile_num:09_23_83_87_24","600"},
            {"Doctor Name: Rahel ylma","Hospital Adress:DireDawa General Hospital ","Exp:3yrs","Mobile_num:09_54_54_87_24","900"},
            {"Doctor Name: Gerawork Abebe ","Hospital Adress: Zewditu hospital ","Exp:6yrs","Mobile_num:09_65_83_07_24","300"},
            {"Doctor Name: Bilal Kedir ","Hospital Adress:Yeka General Hospital  ","Exp:6yrs","Mobile_num:09_33_83_80_24","500"},
            {"Doctor Name: Abel Haile ","Hospital Adress:Harer General Hospital","Exp:2","Mobile_num:09_21_83_67_24","800"}

    };
    private String[][]doctor_detail3={
            {"Doctor Name: Yonas knde","Hospital Adress:Harer General Hospital","Exp:5yrs","Mobile_num:09_23_83_87_24","600"},
            {"Doctor Name: kebron mekasha","Hospital Adress:DireDawa General Hospital ","Exp:3yrs","Mobile_num:09_54_54_87_24","900"},
            {"Doctor Name: Lelisa Abebe ","Hospital Adress: Zewditu hospital ","Exp:6yrs","Mobile_num:09_65_83_07_24","300"},
            {"Doctor Name: Milion Kedir ","Hospital Adress:Yeka General Hospital  ","Exp:6yrs","Mobile_num:09_33_83_80_24","500"},
            {"Doctor Name: Abnet Gashaw ","Hospital Adress:Harer General Hospital","Exp:2","Mobile_num:09_21_83_67_24","800"}

    };
    private String[][]doctor_detail4={
            {"Doctor Name: Dereje Kaleb ","Hospital Adress:Harer General Hospital","Exp:5yrs","Mobile_num:09_23_83_87_24","600"},
            {"Doctor Name: Samrawit Solomon","Hospital Adress:DireDawa General Hospital ","Exp:3yrs","Mobile_num:09_54_54_87_24","900"},
            {"Doctor Name: Endale Worku ","Hospital Adress: Zewditu hospital ","Exp:6yrs","Mobile_num:09_65_83_07_24","300"},
            {"Doctor Name: Jember Kedir ","Hospital Adress:Yeka General Hospital  ","Exp:6yrs","Mobile_num:09_33_83_80_24","500"},
            {"Doctor Name: Samson Hirpa ","Hospital Adress:Harer General Hospital","Exp:2","Mobile_num:09_21_83_67_24","800"}

    };
    private String[][]doctor_detail5={
            {"Doctor Name: Semira Hussen","Hospital Adress:Harer General Hospital","Exp:5yrs","Mobile_num:09_23_83_87_24","600"},
            {"Doctor Name: Tadese Bekalu","Hospital Adress:DireDawa General Hospital ","Exp:3yrs","Mobile_num:09_54_54_87_24","900"},
            {"Doctor Name: Elias Abebe ","Hospital Adress: Zewditu hospital ","Exp:6yrs","Mobile_num:09_65_83_07_24","300"},
            {"Doctor Name: Ermias Hailu ","Hospital Adress:Yeka General Hospital  ","Exp:6yrs","Mobile_num:09_33_83_80_24","500"},
            {"Doctor Name: Leykun Begashaw ","Hospital Adress:Harer General Hospital","Exp:2","Mobile_num:09_21_83_67_24","800"}

    };
    TextView tv;
    Button btn;
    String[][] doctor_details={};
    ArrayList list;
    SimpleAdapter sa;
    HashMap<String,String> item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);
        tv=findViewById(R.id.healthcarre);
        btn=findViewById(R.id.buttonhaBack);
        Intent it=getIntent();
        String title=it.getStringExtra("title");
        tv.setText(title);
        if(title.compareTo("Famliy physician")==0)
            doctor_details=doctor_detail1;
        else
        if(title.compareTo("Dietcian ")==0)
            doctor_details=doctor_detail2;
        else
        if(title.compareTo("Surgeon")==0)
            doctor_details=doctor_detail3;
        else
        if(title.compareTo("Dentist ")==0)
            doctor_details=doctor_detail4;
        else
            doctor_details=doctor_detail5;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailActivity.this,findDoctorActivitiy.class));
            }
        });
        list=new ArrayList();
        for(int i=0;i<doctor_details.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5","Cons Fees:"+ doctor_details[i][4]+"/-");
            list.add(item);

        }
        sa=new SimpleAdapter(this,list,
                R.layout.multi_line,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView lst=findViewById(R.id.ListviewDD);
        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(DoctorDetailActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);

            }
        });
    }
}


