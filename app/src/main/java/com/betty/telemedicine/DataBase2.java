package com.betty.telemedicine;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBase2 extends SQLiteOpenHelper {
    public static final String DBNAME = "Buy.db";
    public DataBase2(BuyMedDetail context)
    {
        super(context, "Buy.db", null, 1);
    }
    public DataBase2(cartBuyMedicine context)
    {
        super(context, "Buy.db", null, 1);
    }
    public DataBase2(BuyMedicineBook context)
    {
        super(context, "Buy.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table cart(username TEXT , product TEXT,price FLOAT,otype TEXT)");
        MyDB.execSQL("create Table orderplace(username TEXT ,fullname TEXT,address TEXT,contact TEXT,pincode INT, date TEXT,time TEXT,price FLOAT,otype TEXT)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists cart");
        MyDB.execSQL("drop Table if exists orderplace");
    }
    public void addCart(String username, String product, float price, String otype){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("product",product);
        cv.put("price",price);
        cv.put("otype",otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart",null,cv);
        db.close();
    }
    public int checkCart(String username, String product){
        int result =0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from cart where username = ? and product = ?",str);
        if (c.moveToFirst()){
            result=1;
        }
        db.close();
        return result;
    }

    public void removeCart(String username, String otype){
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        SQLiteDatabase db = getWritableDatabase();
        db.delete("cart","username = ? and otype = ?",str);
        db.close();
    }

    public ArrayList getCartData(String username, String otype) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        Cursor c = db.rawQuery("select * from cart where username = ? and otype = ?", str);
        if (c.moveToFirst()) {
            do {
                String product = c.getString(1);
                String price = c.getString(2);
                arr.add(product + "$" + price);
            } while (c.moveToNext());
        }
        db.close();
        return arr;

    }

    public void addOrder(String username,String fullname,String address,String contact,int pincode,String date,String time,float price,String otype){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("fullname",fullname);
        cv.put("address",address);
        cv.put("contact",contact);
        cv.put("pincode",pincode);
        cv.put("date",date);
        cv.put("time",time);
        cv.put("price",price);
        cv.put("otype",otype);

        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderplace",null,cv);
        db.close();
    }

}

