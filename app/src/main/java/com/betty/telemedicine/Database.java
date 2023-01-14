package com.betty.telemedicine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, "Telemedicine.db", null, 1);
    }



/*public class Database extends SQLiteOpenHelper {


   // public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
       // super(context, name, factory, version);
   public Database(Context context) {
       super(context, "Telemedicine.db", null, 1);
  // }

//}*/

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qr1 = "create table users(username TEXT, email TEXT, password TEXT)";
        db.execSQL(qr1);
        String qr2 = "create table book(doctername TEXT,hospital TEXT,mobile TEXT,fees TEXT,date TEXT,time TEXT)";
        db.execSQL(qr2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Telemedicine");
    }

    public void register(String username, String email, String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, cv);
        db.close();
    }


    public int login(String username1, String password1) {
        int result = 0;
        String str[] = new String[2];
        str[0] = username1;
        str[1] = password1;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where username = ? and password = ?", str);
        if (c.moveToFirst()) {
            result = 1;
        }
        return result;
    }

    public Boolean addBook(String doctername, String hospital, String mobile, String fees, String date, String time) {
        ContentValues cv = new ContentValues();

        cv.put("doctername", doctername);
        cv.put("hospital", hospital);
        cv.put("mobile", mobile);
        cv.put("fees", fees);
        cv.put("date", date);
        cv.put("time", time);
        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert("book", null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }


    public ArrayList getOrderData(String doctername) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[1];
        str[0] = doctername;
        Cursor c = db.rawQuery("select * from book where doctername=?", str);
        if (c.moveToFirst()) {
            do {
                arr.add(c.getString(1) + "$" + c.getString(2) + "$" + c.getString(3) + "$" + c.getString(4) + "$" + c.getString(5));
            } while (c.moveToFirst());
        }
        db.close();

        return arr;

    }

    public Boolean checkAppointment(String doctername, String hospital, String mobile, String date, String time) {
        // int result=0;
        String str[] = new String[5];

        str[0] = doctername;
        str[1] = hospital;
        str[2] = mobile;
        str[3] = date;
        str[4] = time;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery("select *from book where doctername=? and hospital=? and mobile =? and date=? and time=?", str);

        if (c.getCount() > 0) {

            return true;


        } else {
            return false;
        }

    }

    public Cursor getdata() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from book ", null);
        return c;
    }


    }
