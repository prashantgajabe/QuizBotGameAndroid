package com.prashant.quizbotgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NewbieData extends SQLiteOpenHelper {
    private static final String Database_Name = "FirstRun.db";
    private static final int Database_Version = 1;
    public NewbieData(Context context) {
        super(context, Database_Name, null, Database_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String FIRST_RUN_DATA = "CREATE TABLE IF NOT EXISTS Firstrun (ID INTEGER PRIMARY KEY AUTOINCREMENT, RUN TEXT, VERSION INTEGER DEFAULT 1);";
        db.execSQL(FIRST_RUN_DATA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void setVersion(int newVersion) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("UPDATE Question SET VERSION = "+ newVersion +" WHERE ID = 1;");
    }
    public int getVersion(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cu = db.rawQuery("SELECT VERSION FROM Firstrun", null);
        if(cu.getCount()==0){
            return 0;
        } else{
            if(cu.moveToFirst()){
                return Integer.parseInt(cu.getString(cu.getColumnIndex("VERSION")));
            }
        }
        return 0;
    };

    public int getFirst(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cu = db.rawQuery("SELECT RUN FROM Firstrun", null);
        if(cu.getCount()==0){
            return 0;
        } else{
            if(cu.moveToFirst()){
                return Integer.parseInt(cu.getString(cu.getColumnIndex("RUN")));
            }
        }
        return 0;
    }
    public void setFirst(){
            SQLiteDatabase db2 = this.getWritableDatabase();
            db2.execSQL("DELETE FROM Firstrun");
            ContentValues cv = new ContentValues();
            cv.put("RUN", "1");
            db2.insert("Firstrun", null, cv);
            db2.close();
    }
}
