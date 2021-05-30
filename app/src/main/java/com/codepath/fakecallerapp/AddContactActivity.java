package com.codepath.fakecallerapp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.SaveCallback;

public class AddContactActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText etName, etPhone;
    private Button btnAddContact;
    public static final String TAG = "AddContactActivity";
    private Spinner genderSpinner;
    private static final String[] paths = {"Gender", "Female", "Male"};

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.add_contact);

            etName = findViewById(R.id.etName);
            etPhone = findViewById(R.id.etPhone);
            btnAddContact = findViewById(R.id.btnAddContact);
//            genderSpinner = (Spinner)findViewById(R.id.gender);
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddContactActivity.this,
//                android.R.layout.simple_spinner_item, paths);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            genderSpinner.setAdapter(adapter);
//            genderSpinner.setOnItemSelectedListener(this);

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
                                "Please enter all fields", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.i(TAG, "New Contact: " + name + "， " + phone);
                        saveContact(name, phone);
                    }
                }
//                public void onClick(View v) {
//
//                    // get text from our edit text fields
//                    String name = etName.getText().toString();
//                    String phone = etPhone.getText().toString();
////                    String gender = null;
//
//                    // check validation: email can be left blank
//                    if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) ||
//                            (genderSpinner == null && genderSpinner.getSelectedItem().toString() == null)
//                            || (genderSpinner != null && (genderSpinner.getSelectedItem().toString()).equals("Gender"))) {
//                        Toast.makeText(AddContactActivity.this,
//                                "Please enter all fields", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Log.i(TAG, "New Contact: " + name + "， " + phone);
////                        Log.i(TAG, "New Contact: " + name + "， " + phone + ", " + gender);
////                        gender = genderSpinner.getSelectedItem().toString();
////                        saveContact(name, phone, gender);
//                        saveContact(name, phone);
////                        adapter.notifyDataSetChanged();
//                    }
//                }
            });
        }

//    private void saveContact(String name, String phone, String gender) {
//        // Configure Query
//        Contact newContact = new Contact();
//        newContact.setContactName(name);
//        try {
//            newContact.setPhoneNumber(Integer.parseInt(phone));
//        } catch(NumberFormatException e) {
//            Toast.makeText(AddContactActivity.this, "Invalid phone number!", Toast.LENGTH_SHORT).show();
//            System.out.println("Could not parse " + e);
//        }
//        newContact.setAudioFile(gender);
//        // Saving object
//        newContact.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e == null) {
//                    Log.i(TAG, "Contact added successfully");
//                    Toast.makeText(AddContactActivity.this, "Contact added successfully!", Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(AddContactActivity.this, MainActivity.class);
//                    startActivity(i);
//                } else {
//                    Log.e(TAG, "Error while adding", e);
//                    Toast.makeText(AddContactActivity.this, "Error while saving!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }

    private void saveContact(String name, String phone) {
        // Configure Query
        Contact newContact = new Contact();
        newContact.setContactName(name);
        try {
            newContact.setPhoneNumber(Integer.parseInt(phone));
        } catch(NumberFormatException e) {
            Toast.makeText(AddContactActivity.this, "Invalid phone number!", Toast.LENGTH_SHORT).show();
            System.out.println("Could not parse " + e);
        }

        // Saving object
        newContact.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.i(TAG, "Contact added successfully");
                    Toast.makeText(AddContactActivity.this, "Contact added successfully!", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        switch (position) {
            default:
            case 0:
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Please specify gender for your fake caller", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
