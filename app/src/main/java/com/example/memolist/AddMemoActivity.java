package com.example.memolist;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddMemoActivity extends AppCompatActivity {

    private EditText memoTitle;
    private EditText memoDescription;
    private EditText memoDate;

    private RadioGroup priorityGroup;
    private RadioButton highPriority;
    private RadioButton mediumPriority;
    private RadioButton lowPriority;

    private Button saveButton;

    private MemoDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memo);

        memoTitle = findViewById(R.id.memoTitle);
        memoDescription = findViewById(R.id.memoDescription);
        memoDate = findViewById(R.id.memoDate);

        priorityGroup = findViewById(R.id.priorityGroup);
        highPriority = findViewById(R.id.highPriority);
        mediumPriority = findViewById(R.id.mediumPriority);
        lowPriority = findViewById(R.id.lowPriority);

        saveButton = findViewById(R.id.saveButton);

        dbHelper = new MemoDbHelper(AddMemoActivity.this);

        saveButton.setOnClickListener(v -> saveMemo());
    }

    private void saveMemo() {

        String title = memoTitle.getText().toString();
        String description = memoDescription.getText().toString();
        String date = memoDate.getText().toString();

        String priority = "";

        int selectedId = priorityGroup.getCheckedRadioButtonId();

        if(selectedId == R.id.highPriority){
            priority = "High";
        }
        else if(selectedId == R.id.mediumPriority){
            priority = "Medium";
        }
        else if(selectedId == R.id.lowPriority){
            priority = "Low";
        }

        if(title.isEmpty() || description.isEmpty()){
            Toast.makeText(this, "Title and description required", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.execSQL(
                "INSERT INTO memos (title, description, priority, date) VALUES (?, ?, ?, ?)",
                new Object[]{title, description, priority, date}
        );

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
