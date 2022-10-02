package com.example.contactmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ShowContactActivity extends AppCompatActivity {
    long contactId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);

        TextView firstName = (TextView) findViewById(R.id.firstName);
        TextView lastName = (TextView) findViewById(R.id.lastName);
        TextView email = (TextView) findViewById(R.id.email);
        TextView phone = (TextView) findViewById(R.id.phone);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            contactId = extras.getLong("id");
            DatabaseManager dbManager = new DatabaseManager(this);
            Contact contact = dbManager.getContact(contactId);
            if(contact!= null){
                firstName.setText("First name: " + contact.getFirstName());
                lastName.setText("Last name: " + contact.getLastName());
                email.setText("Email: " + contact.getEmail());
                phone.setText("Phone: " + contact.getPhone());
            } else {
                Log.d("db", "contact null");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_contact:
                deleteContact();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void deleteContact() {
        new AlertDialog.Builder(this)
                .setTitle("Please confirm")
                .setMessage("Are you sure you want to delete this contact?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        DatabaseManager dbManager = new DatabaseManager(getApplicationContext());
                        dbManager.deleteContact(contactId);
                        dialog.dismiss();
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();

    }
}