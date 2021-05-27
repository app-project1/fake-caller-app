package com.codepath.fakecallerapp;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class AddContactActivity extends AppCompatActivity {

    private EditText etName, etPhone;
    private Button btnAddContact;
    public static final String TAG = "AddContactActivity";

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.add_contact);

            etName = findViewById(R.id.etName);
            etPhone = findViewById(R.id.etPhone);
            btnAddContact = findViewById(R.id.btnAddContact);

            // adding on click listener for add contact button
            btnAddContact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // get text from our edit text fields
                    String name = etName.getText().toString();
                    String phone = etPhone.getText().toString();

                    // check validation: email can be left blank
                    if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)) {
                        Toast.makeText(AddContactActivity.this,
                                "Please enter contact name and phone number", Toast.LENGTH_SHORT).show();
                    } else {
                        addContact(name, phone);
                    }
                }
            });
        }

    private void addContact(String name, String phone) {
        // Configure Query
        Contact newContact = new Contact();
        newContact.put("contactName", name);
        try {
            newContact.put("phoneNumber", Integer.parseInt(phone));
        } catch(NumberFormatException e) {
            System.out.println("Could not parse " + e);
        }

        // Saving object
        newContact.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.i(TAG, "Contact added successfully");
                    Intent i = new Intent(AddContactActivity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Log.e(TAG, "Error while adding", e);
                    Toast.makeText(AddContactActivity.this, "Error while saving!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // in on activity result method.
        if (requestCode == 1) {
            // we are checking if the request code is 1
            if (resultCode == Activity.RESULT_OK) {
                // if the result is ok we are displaying a toast message.
                Toast.makeText(this, "Contact has been added.", Toast.LENGTH_SHORT).show();
            }
            // else we are displaying a message as contact addition has cancelled.
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Cancelled Added Contact",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

}
