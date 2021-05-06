package com.codepath.fakecallerapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/*
This class is also Contact List Activity
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView ivName;
    private SearchView searchBar;
    private SwipeRefreshLayout swipeContainer;
    private RecyclerView rvContacts;
    private List<Contact> contacts;

    public MainActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        ivName = findViewById(R.id.ivName);
        searchBar = findViewById(R.id.searchBar);
        swipeContainer = findViewById(R.id.swipeContainer);
        rvContacts = findViewById(R.id.rvContacts);
        contacts = new ArrayList<>();

        // Create an adapter
        ContactAdapter contactAdapter = new ContactAdapter(this, contacts);
        // Set the adapter on the recycler view
        rvContacts.setAdapter(contactAdapter);
        // Set a layout manager on the recycler view
        rvContacts.setLayoutManager(new LinearLayoutManager(this));

        // query database from backend
        queryContacts();
    }

    private void queryContacts() {
        ParseQuery<Contact> query = ParseQuery.getQuery(Contact.class);
        query.include(Contact.KEY_PHONE_NUMBER);
        query.setLimit(20);
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