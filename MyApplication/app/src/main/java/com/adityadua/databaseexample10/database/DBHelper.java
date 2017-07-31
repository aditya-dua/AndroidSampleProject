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
    // insert into Tablea ('name','class') values('Aditya','10');

    // insert into studnet ('id','name') values (1,'ABC');
    // ContentValues => Key & Value Pair
    // Key => id Value => 1
    // cv.put("id",1);
    // cv.put("name","ABC");
    // insert : cv & table
    // insert into table ('id','name') values (1,'ABC');
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
//authorName of Bookid =1

    public String getValue(String table,String column,String where){

        Cursor result = db.query(false,table,new String[]{column},where,null,null,null,null,null);

        String value="";

        try{
            if(result.moveToFirst()){
                value= result.getString(0);
            }else{
                return null;
            }
        }finally {
            result.close();
        }
        return value;
    }

    // deletion ::
// delete tablea where studentid=1;
    public void deleteRecords(String table,String whereclause,String[] whereArgs){
        try{
            db.beginTransaction();
            db.delete(table,whereclause,whereArgs);
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }

    // Update the Table ::

    public int updateRecord(String table,ContentValues values,String whereClause,String [] whereArgs){
        int updatedRows=0;
        try{
            db.beginTransaction();
            updatedRows=db.update(table,values,whereClause,whereArgs);
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }

        return  updatedRows;
    }

    public List<BookData> getAllBooks(){
        List<BookData> books= new LinkedList<BookData>();

/// select * from Books;
        String query = "select * from "+Constants.BOOK_RECORD;

        Cursor cursor = db.rawQuery(query,null);

        BookData book =null;
        if(cursor.moveToFirst()){
            do{
                book= new BookData();
               // int boodid_row_no=cursor.getColumnIndex("BookId");
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


