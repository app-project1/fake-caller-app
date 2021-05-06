package com.codepath.fakecallerapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView ivName;
    private SearchView searchBar;
    private SwipeRefreshLayout swipeContainer;
    private RecyclerView rvContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        ivName = findViewById(R.id.ivName);
        searchBar = findViewById(R.id.searchBar);
        swipeContainer = findViewById(R.id.swipeContainer);
        rvContact = findViewById(R.id.rvContact);

        queryContacts();
    }

    private void queryContacts() {
        ParseQuery<Contact> query = ParseQuery.getQuery(Contact.class);
        query.include(Contact.KEY_PHONE_NUMBER);
        query.findInBackground(new FindCallback<Contact>() {
            @Override
            public void done(List<Contact> contacts, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Issue with getting contacts", e);
                    return;
                }
                for (Contact contact: contacts){
                    Log.i(TAG, "Contact: " + contact.getContactName() + ", phone number: " + contact.getPhoneNumber());
                }
            }
        });
    }
}