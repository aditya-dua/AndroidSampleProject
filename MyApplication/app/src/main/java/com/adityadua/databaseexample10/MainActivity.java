package com.adityadua.databaseexample10;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.adityadua.databaseexample10.database.DBHelper;
import com.adityadua.databaseexample10.model.BookData;
import com.adityadua.databaseexample10.utils.CommonUtilities;
import com.adityadua.databaseexample10.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<BookData> dataList;
    DBHelper dbHelper;

    String [] book_names={"My Experiments with Truth",
                        "The Monk who sold his ferrari",
                        "Time Machine",
                        "Two states",
                        "you can win",
                        "The girl with the dragon tatoo"};

    String [] author_names={
            "M K Gandhi","Robin Sharma","Aditya Dua","Chetan Bhagat",
            "Shiv Khera","Stieg Larsson"
    };

    String [] ids={
            "1234","1235","2345","5432","1230","6767"
    };

    ListView list;
    ArrayAdapter<String> myAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper= CommonUtilities.getDBObject(this);
        list =(ListView) findViewById(R.id.list);

        int count=dbHelper.getFullCount(Constants.BOOK_RECORD,null);
        if(count ==0){
            insertBookRecord();
        }

        dataList = dbHelper.getAllBooks();

        List<String> listTitle=new ArrayList<String>();

        for(int i=0;i<dataList.size();i++){
            listTitle.add(i,dataList.get(i).getBookName());
        }
        myAdaptor = new ArrayAdapter<String>(this,R.layout.row_layout,R.id.listText,listTitle);
        myAdaptor.notifyDataSetChanged();
        list.setAdapter(myAdaptor);
    }
    public void insertBookRecord(){

        for(int i=0;i<book_names.length;i++){
            ContentValues cv = new ContentValues();
            cv.put(Constants.BOOK_ID,ids[i]);
            cv.put(Constants.BOOK_NAME,book_names[i]);
            cv.put(Constants.BOOK_AUTHOR,author_names[i]);

            dbHelper.insertContentVals(Constants.BOOK_RECORD,cv);
        }
    }
}
