package com.example.sqlite_gmail_remplazo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class EmailDatabaseHelper extends SQLiteOpenHelper {

     static final int DATABASE_VERSION = 1;
     static final String DATABASE_NAME = "EmailDatabase";

     static final String TABLE_EMAILS = "Emails";
     static final String COLUMN_ID = "id";
     static final String COLUMN_SENDER = "sender";
     static final String COLUMN_SUBJECT = "subject";
     static final String COLUMN_DATE = "date";
     static final String COLUMN_TIME = "time";
     static final String COLUMN_CONTENT = "content";
     static final String COLUMN_ICON = "icon";

    public EmailDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EMAILS_TABLE = "CREATE TABLE " + TABLE_EMAILS +
                "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_SENDER + " TEXT," +
                COLUMN_SUBJECT + " TEXT," +
                COLUMN_DATE + " TEXT," +
                COLUMN_TIME + " TEXT," +
                COLUMN_CONTENT + " TEXT," +
                COLUMN_ICON + " INTEGER" +
                ")";
        db.execSQL(CREATE_EMAILS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMAILS);
        onCreate(db);
    }

    public void addEmail(Email email) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SENDER, email.getSender());
        values.put(COLUMN_SUBJECT, email.getSubject());
        values.put(COLUMN_DATE, email.getDate());
        values.put(COLUMN_TIME, email.getTime());
        values.put(COLUMN_CONTENT, email.getContent());
        values.put(COLUMN_ICON, email.getIcon());

        db.insert(TABLE_EMAILS, null, values);
        db.close();
    }

    public List<Email> getAllEmails() {
        List<Email> emailList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_EMAILS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String sender = cursor.getString(1);
                String subject = cursor.getString(2);
                String date = cursor.getString(3);
                String time = cursor.getString(4);
                String content = cursor.getString(5);
                int icon = Integer.parseInt(cursor.getString(6));
                Email email = new Email(id, sender, subject, date, time, content, icon);
                emailList.add(email);
            } while (cursor.moveToNext());
        }
        return emailList;
    }

    public void deleteEmail(Email email) {
        SQLiteDatabase db = getWritableDatabase();
        String[] args = {String.valueOf(email.getId())};
        db.delete(TABLE_EMAILS, COLUMN_ID + "=?", args);
        db.close();
    }

}
