package com.example.vumanh.flightcalendar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by VuManh on 8/21/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    static final String DATABASE = "database.db";
    static final int VERSION = 1;
    static final String TABLE = "users";
    static final String TABLE2 = "flights";
    static final String TABLE3 = "contacts";

    static final String U_USERNAME = "_username";
    static final String U_PASSWORD = "_password";
    static final String F_id = "_id";
    static final String F_flightName = "_flightName";
    static final String F_departure = "_departure";
    static final String F_destination = "_destination";
    static final String F_departDate = "_departDate";
    static final String F_departTime = "_departTime";
    static final String C_name = "_name ";
    static final String C_mobile = "_mobile";
    static final String C_address = "_address";


    public DBHelper(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE + " ( " + U_USERNAME + " text PRIMARY KEY, " + U_PASSWORD + " text )");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE2 + " ( " + F_id + " INTEGER PRIMARY KEY AUTOINCREMENT , " + F_flightName + " text ,"
                + F_departure + " text ," + F_destination + " text ," + F_departDate + " text ," + F_departTime + " text )");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE3 + " ( " + C_name + " text PRIMARY KEY, " + C_mobile + " text ," + C_address + " text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int OldVersion, int NewVersion) {
        sqLiteDatabase.execSQL("Drop Table " + TABLE);
        sqLiteDatabase.execSQL("Drop Table " + TABLE2);
        sqLiteDatabase.execSQL("Drop Table " + TABLE3);
        onCreate(sqLiteDatabase);
    }
}
