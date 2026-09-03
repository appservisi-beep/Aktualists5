package com.appservisi.aktalist.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.appservisi.aktalist.model.ModelKayit;

import org.json.JSONException;

import java.util.ArrayList;

public class DataBase {
    static ArrayList<ModelKayit> modelKayits=new ArrayList<>();
    static final String DATABASE_NAME = "datam.db";
    static final String TABLE_NAME = "kayitlar";
    static final int DATABASE_VERSION = 1;
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table "+TABLE_NAME+"( ID integer primary key autoincrement,baslik  text,marka text,resim text,gelenid text,resimsirasi text); ";
    private static final String TAG = "DataBase:";

    // Variable to hold the database instance
    public static SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private static DataBaseHelper dbHelper;
    public  DataBase(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method to open the Database
    public  DataBase open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    // Method to close the Database
    public void close()
    {
        db.close();
    }

    // method returns an Instance of the Database
    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    // method to insert a record in Table
    public  String insertEntry(String baslik,String marka,String resim,String gelenid,String resimsirasi)
    {

        try {


            ContentValues newValues = new ContentValues();
            // Assign values for each column.
            newValues.put("baslik", baslik);
            newValues.put("marka", marka);
            newValues.put("resim", resim);
            newValues.put("gelenid", gelenid);
            newValues.put("resimsirasi", resimsirasi);


            // Insert the row into your table
            db = dbHelper.getWritableDatabase();
            long result=db.insert(TABLE_NAME, null, newValues);
            Log.i("Row Insert Result ", String.valueOf(result));
          //  toast("User Info Saved! Total Row Count is "+getRowCount());
            db.close();

        }catch(Exception ex) {
        }
        return "ok";
    }

    // method to get all Rows Saved in Table
    public  ArrayList<ModelKayit> getRows() throws JSONException {

        modelKayits.clear();
        ModelKayit modelKayit;
        db=dbHelper.getReadableDatabase();
        Cursor projCursor = db.query(TABLE_NAME, null, null,null, null, null, "ID DESC",null);
        while (projCursor.moveToNext()) {

            modelKayit=new ModelKayit();
            modelKayit.setKayitid(projCursor.getString(projCursor.getColumnIndex("ID")));
            modelKayit.setResimbaslik(projCursor.getString(projCursor.getColumnIndex("baslik")));
            modelKayit.setMarka(projCursor.getString(projCursor.getColumnIndex("marka")));
            modelKayit.setResim(projCursor.getString(projCursor.getColumnIndex("resim")));
            modelKayit.setGelenid(projCursor.getString(projCursor.getColumnIndex("gelenid")));
            modelKayit.setResimsirasi(projCursor.getInt(projCursor.getColumnIndex("resimsirasi")));
//            user.setUserphone(projCursor.getString(projCursor.getColumnIndex("user_phone")));
//            user.setUseremail(projCursor.getString(projCursor.getColumnIndex("user_email")));
            modelKayits.add(modelKayit);
        }
        projCursor.close();
        return modelKayits;
    }


    public  ArrayList<ModelKayit> getRowTek(String gelenid) throws JSONException {

        modelKayits.clear();
        ModelKayit modelKayit;
        db=dbHelper.getWritableDatabase();
        Cursor projCursor = db.rawQuery("select * from kayitlar where gelenid="+gelenid,null);
        while (projCursor.moveToNext()) {

            modelKayit=new ModelKayit();
            modelKayit.setKayitid(projCursor.getString(projCursor.getColumnIndex("ID")));
            modelKayit.setResimbaslik(projCursor.getString(projCursor.getColumnIndex("baslik")));
            modelKayit.setMarka(projCursor.getString(projCursor.getColumnIndex("marka")));
            modelKayit.setResim(projCursor.getString(projCursor.getColumnIndex("resim")));
            modelKayit.setGelenid(projCursor.getString(projCursor.getColumnIndex("gelenid")));
            modelKayit.setResimsirasi(projCursor.getInt(projCursor.getColumnIndex("resimsirasi")));
//            user.setUserphone(projCursor.getString(projCursor.getColumnIndex("user_phone")));
//            user.setUseremail(projCursor.getString(projCursor.getColumnIndex("user_email")));
            modelKayits.add(modelKayit);
        }
        projCursor.close();
        return modelKayits;
    }

    // method to delete a Record in Tbale using Primary Key Here it is ID
    public static int deleteEntry(String ID)
    {
        String where="ID=?";
        int numberOFEntriesDeleted= db.delete(TABLE_NAME, where, new String[]{ID}) ;
       // toast("Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted);
        return numberOFEntriesDeleted;
    }

    // method to get Count of Toatal Rows in Table
    public static int getRowCount()
    {
        db=dbHelper.getReadableDatabase();
        Cursor cursor=db.query(TABLE_NAME, null, null, null, null, null, null);
        //toast("Row Count is "+cursor.getCount());
        db.close();
        return cursor.getCount();
    }

    // method to Truncate/ Remove All Rows in Table
    public static void truncateTable()
    {
        db=dbHelper.getReadableDatabase();
        db.delete(TABLE_NAME, "1", null);
        db.close();
       // toast("Table Data Truncated!");
    }

    // method to Update an Existing Row in Table
    public static void  updateEntry(String ID,String link,String resim)
    {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("link", link);
        updatedValues.put("resim", resim);

        String where="ID = ?";
        db=dbHelper.getReadableDatabase();
        db.update(TABLE_NAME,updatedValues, where, new String[]{ID});
        db.close();
       // toast("Row Updated!");
    }

}
