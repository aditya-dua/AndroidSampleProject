package com.adityadua.databaseexample10.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.adityadua.databaseexample10.model.BookData;
import com.adityadua.databaseexample10.utils.Constants;

import java.util.LinkedList;
import java.util.List;

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
    // 10 coulmns in db & you want to insert in 5 columns
    // String [] coulmn={"1","2"};
    public long insertContentVals(String tabName, ContentValues content){

        long id=0;

        try{
            db.beginTransaction();
            id=db.insert(tabName,null,content);
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
        return id;
    }

    // select studentName,studentclass from Student where studentid=1;

    // 10 rows of student
    // 1 Adi 10 => cursor
    // 2 ABC 12
    // 3 DEF 10
    // 4 RTY 9
    // MainActivity
    public Cursor getTableRecords(String tableName,String [] coulmn,String where,String orderBy){
        Cursor cursor = db.query(false,tableName,coulmn,where,null,null,null,orderBy,null);
        //cursor.moveToFirst();

      //  while(cursor.moveToNext()) {
       //     cursor.getString(1);
        //    cursor.getInt(0);
        //    cursor.getString(2);
       // }
        return  cursor;
    }

    public int getFullCount(String table,String where){
        Cursor cursor = db.query(false,table,null,where,null,null,null,null,null);

        int no =0;
        try{
            if(cursor !=null){

                cursor.moveToFirst();
                no=cursor.getCount();
                cursor.close();
            }
        }finally {
            cursor.close();
        }

        return no;
    }

    public List<BookData> getAllBooks(){
        List<BookData> books= new LinkedList<BookData>();


        String query = "select * from "+Constants.BOOK_RECORD;

        Cursor cursor = db.rawQuery(query,null);

        BookData book =null;
        if(cursor.moveToFirst()){
            do{
                book= new BookData();
                book.setId((cursor.getString(0)).toString());
                book.setBookId(cursor.getString(1));
                book.setBookName(cursor.getString(2));
                book.setAuthorName(cursor.getString(3));
                books.add(book);

            }while (cursor.moveToNext());
        }
        return  books;
    }


}


