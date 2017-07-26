package com.adityadua.databaseexample10;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.adityadua.databaseexample10.database.DBHelper;
import com.adityadua.databaseexample10.utils.CommonUtilities;
import com.adityadua.databaseexample10.utils.Constants;

/**
 * Created by AdityaDua on 26/07/17.
 */

public class BookDetail extends AppCompatActivity {

    TextView bookTitle,authorName;
    EditText title_edt,author_edt;
    Button deleteBook,updateBook;

    DBHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        bookTitle = (TextView)findViewById(R.id.title);
        authorName = (TextView)findViewById(R.id.author);


        title_edt=(EditText)findViewById(R.id.titleEdit);
        author_edt = (EditText)findViewById(R.id.authorEdit);

        Intent i = getIntent();
        String id= i.getStringExtra(Constants.BOOK_ID);

        db= CommonUtilities.getDBObject(this);
        final String where = Constants.BOOK_ID+" = '"+id+"'";

        String bookName = db.getValue(Constants.BOOK_RECORD,Constants.BOOK_NAME,where);
        String author_name=db.getValue(Constants.BOOK_RECORD,Constants.BOOK_AUTHOR,where);

        bookTitle.setText(bookName);
        authorName.setText(author_name);

        deleteBook=(Button)findViewById(R.id.delete);
        deleteBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Delete performing",Toast.LENGTH_LONG).show();

                db.deleteRecords(Constants.BOOK_RECORD,where,null);
                Toast.makeText(getApplicationContext(),"Delete Done!",Toast.LENGTH_LONG).show();

                finish();
            }
        });

        updateBook = (Button) findViewById(R.id.update);
        updateBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Update performing",Toast.LENGTH_LONG).show();
                ContentValues values = new ContentValues();
                values.put(Constants.BOOK_NAME,title_edt.getText().toString());
                values.put(Constants.BOOK_AUTHOR,author_edt.getText().toString());

                db.updateRecord(Constants.BOOK_RECORD,values,where,null);
                Toast.makeText(getApplicationContext(),"Update Done !",Toast.LENGTH_LONG).show();

                finish();
            }
        });
    }
}
