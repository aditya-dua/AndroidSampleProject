package com.adityadua.databaseexample10.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.adityadua.databaseexample10.utils.Constants;

/**
 * Created by AdityaDua on 24/07/17.
 * This class will open the DB & write and read from DB
 * Also it will check if DB is open or NOT.
 * Imagine if two classes want to access the DB and make some modifictaions
 * in the DB
 *
 * should this be allowed....
 * Java came get Instance method
 * 1. to create the db connection :: open() method
 * 2. to close the connection
 * 3. to check if db is open or not
 * 4. to insert values in the db
 * 5. to read values from the db
 * 6. to delete values
 * 7. to update the values
 * 8. a contrcutor of this class to intitalize the object
 *
 */

public class DBHelper {

    private SQLiteDatabase db;
    private final Context context;
    private final TablesClass dbHelper;

    public static DBHelper db_helper=null;

    public DBHelper(Context context) {
        this.context = context;
        dbHelper = new TablesClass(context, Constants.DATABASE_NAME,null,Constants.DATABASE_VERSION);
    }
    // DBHelper dbh;
    //dbh = DBHelper.getInstance(getActivityContext());
    public static  DBHelper getInstance(Context context){
        try{
            if(db_helper==null){
                db_helper=new DBHelper(context);
                db_helper.open();
            }
        }catch (IllegalStateException e){
            e.printStackTrace();
        }

        return db_helper;
    }
    public void open(){
        try {
            db = dbHelper.getWritableDatabase();
        }catch (Exception e){
            Log.i("open database error",e.toString());
            db = dbHelper.getReadableDatabase();

        }
    }
    public void close(){
        if(db.isOpen()){
            db.close();
        }
    }
    public boolean dbOpenCheck(){
        return db.isOpen();
    }
    // you want to insert values in DB...
    // 1. you will check if db is open or not ::dbOpenCheck()
    // 2. true => dbhelp.getInstance();
    // 3. false =>


}


