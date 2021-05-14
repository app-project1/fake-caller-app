package com.codepath.fakecallerapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.parceler.Parcels;

/*
This class is the detail activity that will show up when we click on each row in the contact list activity
This class should hold information of the contact: contact name, phone number, option to make the call...
 */
public class ContactDetailActivity extends AppCompatActivity {
    public static final String TAG = "ContactDetailActivity";
    private TextView tvContactName;
    private TextView tvPhoneNum;
    private ImageView ivProfilePic;
    private ImageButton btnCall;
    private ImageButton btnText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactdetail);

        tvContactName = findViewById(R.id.tvContactName);
        tvPhoneNum = findViewById(R.id.tvPhoneNum);
        ivProfilePic = findViewById(R.id.ivProfilePic);
        btnCall = findViewById(R.id.btnCall);
        btnText = findViewById(R.id.btnText);

        Contact contact = Parcels.unwrap(getIntent().getParcelableExtra("contact"));
        tvContactName.setText(contact.getContactName());
        tvPhoneNum.setText(contact.getPhoneNumber());
        /*
        + profile pic: change to parse photos from backend instead of hardcode the pic
        1 optional is: text button - maybe leave this later
         */

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick Call button");
                Intent intent = new Intent(ContactDetailActivity.this, OutgoingActivity.class);
                intent.putExtra("contact", contact);
                startActivity(intent);
            }
        });
    }
}
