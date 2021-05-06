package com.codepath.fakecallerapp;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.appcompat.app.ActionBar;
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

    public static final String TAG = "MainActivity";
    private SwipeRefreshLayout swipeContainer;
    private RecyclerView rvContacts;
    protected List<Contact> allContacts;
    protected ContactAdapter contactAdapter;

    public MainActivity() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        // Customize action bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_title);

        rvContacts = findViewById(R.id.rvContacts);
        allContacts = new ArrayList<>();
        swipeContainer = findViewById(R.id.swipeContainer);

        // Initialize an adapter
        contactAdapter = new ContactAdapter(this, allContacts);
        // Set the adapter on the recycler view
        rvContacts.setAdapter(contactAdapter);
        // Set a layout manager on the recycler view
        rvContacts.setLayoutManager(new LinearLayoutManager(this));

        // query database from backend
        queryContacts();

        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG, "Refreshing contacts...");
                // Clear out old items before appending in the new ones
                contactAdapter.clear();
                // Get and add new items to adapter
                queryContacts();
                // Now we call setRefreshing(false) to signal refresh has finished
                swipeContainer.setRefreshing(false);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return true;
    }

    private void queryContacts() {
        ParseQuery<Contact> query = ParseQuery.getQuery(Contact.class);
        query.include(Contact.KEY_CONTACT_NAME);
        query.addAscendingOrder(Contact.KEY_CONTACT_NAME);
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
                allContacts.addAll(contacts);
                contactAdapter.notifyDataSetChanged();
            }
        });
    }
}