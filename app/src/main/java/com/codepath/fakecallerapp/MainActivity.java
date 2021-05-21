package com.codepath.fakecallerapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    protected SearchView searchView;
    private FloatingActionButton btnEmergency;

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
        btnEmergency = findViewById(R.id.btnEmergency);

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

        btnEmergency.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                dialPhoneNumber("911"); // set default emergency number
            }

        });
    }

    // link to built-in phone
    private void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        searchView = (SearchView) menu.findItem(R.id.search_bar).getActionView();
        // Set up search bar functionality
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
        return true;
    }

    // filters contacts that match the search bar query
    private void filter(String newText) {
        List<Contact> filteredList = new ArrayList<>();
        for (Contact contact : allContacts) {
            if (contact.getContactName().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(contact);
            }
        }
        contactAdapter.filterList(filteredList);
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