package com.codepath.fakecallerapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import org.parceler.Parcels;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private Context context;
    private List<Contact> contacts;

    public ContactAdapter(Context context, List<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    //involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("ContactAdapter", "onCreateViewHolder");
        View contactView = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(contactView);
    }

    // Involves populating data into the item through the holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("ContactAdapter", "onBindViewHolder" + position);
        // Get the contact at the passed in position
        Contact contact = contacts.get(position);
        // Bind the movie data into the view holder
        holder.bind(contact);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvContact;
        private RelativeLayout container;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvContact = itemView.findViewById(R.id.tvContact);
            container = itemView.findViewById(R.id.container);
        }

        public void bind (Contact contact){
            // Bind the contact name to the view element
            tvContact.setText(contact.getContactName());

            // 1. Register the click listener on the whole row
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, contact.getContactName(), Toast.LENGTH_SHORT).show();
                    // 2. Navigate to a new activity on tap
                    Intent i = new Intent(context, ContactDetailActivity.class);
                    i.putExtra("contact", Parcels.wrap(contact));
                    context.startActivity(i);
                }
            });
        }


    }
}