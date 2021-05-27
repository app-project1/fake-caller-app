package com.codepath.fakecallerapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

import org.parceler.Parcels;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private Context context;
    private List<Contact> contacts;

    public ContactAdapter(Context context, List<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    // Involves inflating a layout from XML and returning the holder
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

    // Clean all elements of the recycler
    public void clear() {
        contacts.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Contact> list) {
        contacts.addAll(list);
        notifyDataSetChanged();
    }

    // only display filtered items when using search bar
    public void filterList(List<Contact> filteredList) {
        contacts = filteredList;
        notifyDataSetChanged();
    }

    // Define view holder class
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvContact;
        private RelativeLayout container;
        private ImageView ivSmallProfile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContact = itemView.findViewById(R.id.tvContact);
            container = itemView.findViewById(R.id.container);
            ivSmallProfile = itemView.findViewById(R.id.ivSmallProfile);
        }

        public void bind(Contact contact) {
            // Bind the contact name to the view element
            tvContact.setText(contact.getContactName());

            /*
            Use Glide to load an image into the ImageView
            (currently set to a default profile image, can change to load from Parse Backend later)
            and crop the image into a circle
            */
            Glide.with(itemView.getContext())
                    .load("https://www.pngkey.com/png/detail/115-1150152_default-profile-picture-avatar-png-green.png")
                    .placeholder(R.drawable.profilepic_default)
                    .fitCenter()
                    .transform(new CircleCrop())
                    .into(ivSmallProfile);

            // Register the click listener on the whole row
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Navigate to a new activity on tap
                    Intent i = new Intent(context, ContactDetailActivity.class);
                    i.putExtra("contact", Parcels.wrap(contact));
                    context.startActivity(i);
                }
            });
        }
    }
}