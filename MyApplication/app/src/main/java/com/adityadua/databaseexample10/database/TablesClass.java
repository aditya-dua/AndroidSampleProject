package com.adityadua.databaseexample10.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.adityadua.databaseexample10.utils.Constants;

/**
 * Created by AdityaDua on 24/07/17.
 */

public class TablesClass extends SQLiteOpenHelper {

    Context context;

    public TablesClass(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {

        super(context, Constants.DATABASE_NAME, factory, Constants.DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table="CREATE TABLE "+ Constants.BOOK_RECORD+" ("+
                Constants.ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                Constants.BOOK_ID+" TEXT,"+
                Constants.BOOK_NAME+" TEXT,"+
                Constants.BOOK_AUTHOR+" TEXT);";

        String create_table_new ="CREATE TABLE "+ Constants.BOOK_RECORD+" ("+
                Constants.ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                Constants.BOOK_ID+" TEXT,"+
                Constants.BOOK_NAME+" TEXT," +
                "BookPrice INTEGER,"+
                Constants.BOOK_AUTHOR+" TEXT);";

        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        context.deleteDatabase(Constants.DATABASE_NAME);
        onCreate(db);

    }
}
