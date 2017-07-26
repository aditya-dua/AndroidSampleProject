package com.adityadua.databaseexample10.utils;

import android.content.Context;

import com.adityadua.databaseexample10.database.DBHelper;

/**
 * Created by AdityaDua on 25/07/17.
 */

public class CommonUtilities {

    public static DBHelper getDBObject(Context mContext){
        DBHelper dbHelper = DBHelper.getInstance(mContext);
        return  dbHelper;
    }
}
