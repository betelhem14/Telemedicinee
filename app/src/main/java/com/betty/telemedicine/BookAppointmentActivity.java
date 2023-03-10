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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4;
    TextView tv;
    Button btnbook,btnback;
    Database db;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton,timeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);


        setContentView(R.layout.activity_book_appointment);
        tv=findViewById(R.id.bookapptitile);
        ed1=findViewById(R.id.Appfullname);
        ed2=findViewById(R.id.Appadress);
        ed3=findViewById(R.id.Appcontactnum);
        ed4=findViewById(R.id.Appfees);
        dateButton=findViewById(R.id.buttonAppDate);
        timeButton=findViewById(R.id.buttonApptime);
        btnback=findViewById(R.id.buttonAppback);
        btnbook=findViewById(R.id.buttonRegister3);
        db=new Database(this);

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it=getIntent();
        String title=it.getStringExtra("text1");
        String fullname=it.getStringExtra("text2");
        String address=it.getStringExtra("text3");
        String contact=it.getStringExtra("text4");
        String fees=it.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("cons Fees:" +fees+"/-");
        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent (BookAppointmentActivity.this,findDoctorActivitiy.class));
            }
        });
        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String doctername = ed1.getText().toString();
                String hospital = ed2.getText().toString();
                String mobile = ed3.getText().toString();
                String fees = ed4.getText().toString();
                String date = dateButton.getText().toString();
               String time = timeButton.getText().toString();

                // db=new Database(getApplicationContext(),"TeleMedicine",null,1);
                //  SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
//if(db.checkAppointment(dname,hospital,mobile,date,time)==1)
                Boolean check = db.checkAppointment(doctername, hospital, mobile,date, time);
                if (check == false) {
                   //Toast.makeText(getApplicationContext(), "Appointment already booked", Toast.LENGTH_LONG).show();
                Boolean insert=db.addBook(doctername,hospital,mobile,fees,date,time);
                if (insert==true){
                    Toast.makeText(BookAppointmentActivity.this,"Appointment  booked", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(BookAppointmentActivity.this,"Appointment failed ", Toast.LENGTH_LONG).show();
                }

                } else{
                    Toast.makeText( BookAppointmentActivity.this,"Appointment already booked", Toast.LENGTH_LONG).show();
                }

                    //db.addBook(dname, hospital, mobile, fee, date, time);
                //Toast.makeText(BookAppointmentActivity.this, "Appointment record", Toast.LENGTH_SHORT).show();
            }

        });
    }
    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1++;
                dateButton.setText(i2+"/"+"i1"+"/"+i);

            }
        };
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);

        int style= AlertDialog.THEME_HOLO_DARK;
        datePickerDialog=new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
    }

    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timeButton.setText(i+":"+i1);
            }
        };
        Calendar cal=Calendar.getInstance();
        int hrs=cal.get(Calendar.HOUR);
        int mins=cal.get(Calendar.MINUTE);
        int style=AlertDialog.THEME_HOLO_DARK;
        timePickerDialog =new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);
    }
}
