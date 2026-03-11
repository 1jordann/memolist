package com.example.memolist;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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
    private Button searchButton;
    private EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memoRecyclerView = findViewById(R.id.memoRecyclerView);
        addMemoButton = findViewById(R.id.addMemoButton);
        sortButton = findViewById(R.id.sortButton);
        highFilter = findViewById(R.id.highFilter);
        mediumFilter = findViewById(R.id.mediumFilter);
        lowFilter = findViewById(R.id.lowFilter);
        searchBar = findViewById(R.id.searchBar);
        searchButton = findViewById(R.id.searchButton);

        dbHelper = new MemoDbHelper(this);
        memoList = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM memos", null);

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

            while(cursor2.moveToNext()){
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

            Cursor cursor3 = db3.rawQuery(
                    "SELECT * FROM memos WHERE priority='High'",
                    null
            );

            while(cursor3.moveToNext()){
                Memo memo = new Memo(
                        cursor3.getInt(0),
                        cursor3.getString(1),
                        cursor3.getString(2),
                        cursor3.getString(3),
                        cursor3.getString(4)
                );
                memoList.add(memo);
            }

            cursor3.close();
            memoAdapter.notifyDataSetChanged();
        });

        mediumFilter.setOnClickListener(v -> {

            memoList.clear();

            SQLiteDatabase db4 = dbHelper.getReadableDatabase();

            Cursor cursor4 = db4.rawQuery(
                    "SELECT * FROM memos WHERE priority='Medium'",
                    null
            );

            while(cursor4.moveToNext()){
                Memo memo = new Memo(
                        cursor4.getInt(0),
                        cursor4.getString(1),
                        cursor4.getString(2),
                        cursor4.getString(3),
                        cursor4.getString(4)
                );
                memoList.add(memo);
            }

            cursor4.close();
            memoAdapter.notifyDataSetChanged();
        });

        lowFilter.setOnClickListener(v -> {

            memoList.clear();

            SQLiteDatabase db5 = dbHelper.getReadableDatabase();

            Cursor cursor5 = db5.rawQuery(
                    "SELECT * FROM memos WHERE priority='Low'",
                    null
            );

            while(cursor5.moveToNext()){
                Memo memo = new Memo(
                        cursor5.getInt(0),
                        cursor5.getString(1),
                        cursor5.getString(2),
                        cursor5.getString(3),
                        cursor5.getString(4)
                );
                memoList.add(memo);
            }

            cursor5.close();
            memoAdapter.notifyDataSetChanged();
        });

        searchButton.setOnClickListener(v -> {

            memoList.clear();

            SQLiteDatabase db6 = dbHelper.getReadableDatabase();

            String searchText = searchBar.getText().toString();

            Cursor cursor6 = db6.rawQuery(
                    "SELECT * FROM memos WHERE title LIKE ? OR description LIKE ?",
                    new String[]{"%" + searchText + "%", "%" + searchText + "%"}
            );

            while(cursor6.moveToNext()){
                Memo memo = new Memo(
                        cursor6.getInt(0),
                        cursor6.getString(1),
                        cursor6.getString(2),
                        cursor6.getString(3),
                        cursor6.getString(4)
                );
                memoList.add(memo);
            }

            cursor6.close();
            memoAdapter.notifyDataSetChanged();
        });

    }
}