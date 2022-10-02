package com.example.contactmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.listView);
        dbManager = new DatabaseManager(this);

        Cursor cursor = dbManager.getContactCursor();
        startManagingCursor(cursor);

        ListAdapter adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.two_line_list_item,
                cursor,
                new String[]{DatabaseManager.FIRST_NAME_FIELD, DatabaseManager.LAST_NAME_FIELD},
                new int[]{android.R.id.text1, android.R.id.text2},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ShowContactActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_contact:
                Intent intent = new Intent(this, AddContactActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}