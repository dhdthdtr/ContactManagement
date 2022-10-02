package com.example.contactmanagement;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        Button bt_add = (Button) findViewById(R.id.btnAdd);
        Button bt_cancel = (Button) findViewById(R.id.btnCancel);

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                addContact();
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }

    public void cancel() {
        finish();
    }

    public void addContact(){
        DatabaseManager dbManager = new DatabaseManager(this);
        String firstName = ((TextView) findViewById(R.id.et_firstName)).getText().toString();
        String lastName = ((TextView) findViewById(R.id.et_lastName)).getText().toString();
        String phone = ((TextView) findViewById(R.id.et_phone)).getText().toString();
        String email = ((TextView) findViewById(R.id.et_email)).getText().toString();
        Contact contact = new Contact(firstName, lastName, phone, email);
        dbManager.addContact(contact);
        finish();
    }
}