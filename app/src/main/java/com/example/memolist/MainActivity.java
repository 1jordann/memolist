package com.example.memolist;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView memoRecyclerView;
    private MemoAdapter memoAdapter;
    private ArrayList<Memo> memoList;
    private MemoDbHelper dbHelper;
    private Button addMemoButton;
    private Button sortButton;
    private Button highFilter;
    private Button mediumFilter;
    private Button lowFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memoRecyclerView = findViewById(R.id.memoRecyclerView);
        addMemoButton = findViewById(R.id.addMemoButton);
        sortButton = findViewById(R.id.sortButton);

        dbHelper = new MemoDbHelper(this);
        memoList = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM memos", null);

        while (cursor.moveToNext()) {

            Memo memo = new Memo(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
            );

            memoList.add(memo);
        }

        cursor.close();

        memoAdapter = new MemoAdapter(memoList);

        memoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        memoRecyclerView.setAdapter(memoAdapter);

        addMemoButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddMemoActivity.class);
            startActivity(intent);
        });

        sortButton.setOnClickListener(v -> {

            memoList.clear();

            SQLiteDatabase db2 = dbHelper.getReadableDatabase();

            Cursor cursor2 = db2.rawQuery("SELECT * FROM memos ORDER BY title", null);

            while (cursor2.moveToNext()) {

                Memo memo = new Memo(
                        cursor2.getInt(0),
                        cursor2.getString(1),
                        cursor2.getString(2),
                        cursor2.getString(3),
                        cursor2.getString(4)
                );

                memoList.add(memo);
            }

            cursor2.close();

            memoAdapter.notifyDataSetChanged();

        });

        highFilter.setOnClickListener(v -> {

            memoList.clear();

            SQLiteDatabase db3 = dbHelper.getReadableDatabase();

            Cursor cursor3 = db.rawQuery(
                    "SELECT * FROM memos WHERE priority='High'",
                    null
            );

            while(cursor.moveToNext()){

                Memo memo = new Memo(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );

                memoList.add(memo);
            }

            cursor.close();

            memoAdapter.notifyDataSetChanged();

        });

        mediumFilter.setOnClickListener(v -> {

            memoList.clear();

            SQLiteDatabase db4 = dbHelper.getReadableDatabase();

            Cursor cursor4 = db.rawQuery(
                    "SELECT * FROM memos WHERE priority='Medium'",
                    null
            );

            while(cursor.moveToNext()){

                Memo memo = new Memo(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );

                memoList.add(memo);
            }

            cursor.close();

            memoAdapter.notifyDataSetChanged();

        });

        lowFilter.setOnClickListener(v -> {

            memoList.clear();

            SQLiteDatabase db5 = dbHelper.getReadableDatabase();

            Cursor cursor5 = db.rawQuery(
                    "SELECT * FROM memos WHERE priority='Low'",
                    null
            );

            while(cursor.moveToNext()){

                Memo memo = new Memo(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );

                memoList.add(memo);
            }

            cursor.close();

            memoAdapter.notifyDataSetChanged();

        });

    }
}