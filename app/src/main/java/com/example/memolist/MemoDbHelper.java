package com.example.memolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MemoDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "memo.db";
    private static final int DATABASE_VERSION = 1;

    public MemoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE memos (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "title TEXT," +
                        "description TEXT," +
                        "priority TEXT," +
                        "date TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS memos");
        onCreate(db);
    }
}