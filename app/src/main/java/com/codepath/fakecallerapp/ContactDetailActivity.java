package com.codepath.fakecallerapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.parceler.Parcels;

import java.io.IOException;

/*
This class is the detail activity that will show up when we click on each row in the contact list activity
This class should hold information of the contact: contact name, phone number, option to make the call...
 */
public class ContactDetailActivity extends AppCompatActivity {
    public static final String TAG = "ContactDetailActivity";
    private TextView tvContactName;
    private TextView tvPhoneNum;
    private ImageView ivProfilePic;
    private View actionView;
    private ImageButton btnCall;
    private ImageButton btnText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactdetail);

        tvContactName = findViewById(R.id.tvContactName);
        tvPhoneNum = findViewById(R.id.tvPhoneNum);
        ivProfilePic = findViewById(R.id.ivProfilePic);
        actionView = findViewById(R.id.actionView);
        btnCall = findViewById(R.id.btnCall);
        btnText = findViewById(R.id.btnText);

        Contact contact = Parcels.unwrap(getIntent().getParcelableExtra("contact"));
        tvContactName.setText(contact.getContactName());
        tvPhoneNum.setText(contact.getPhoneNumber());
        /*
        Notes:
        if you check the activity_contactdetail.xml, there are 4 main things we want to add and 1
        optional thing:
        4 required things should be added are:
        + contactName (Already included),
        + phone number (Already included),
        + profile pic (the type of it in activity_contactdetail.xml is a background imageview, because
        when I open the real phone app in android the profile pic is kinda a background with contact
        name appearing in front of the background, so I downloaded a default profile picture and set it
        in the activity_contactdetail.xml but somehow it doesn't show up)
        + button call: setOnClickListener (have not finished this part)
        1 optional is: text button - maybe leave this later
         */

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick Call button");

                // make the audio play on click the button
                contact.getAudioFile();
            }
        });


    }
/*
FOLLOW SOME CODE ONLINE BUT FAIL LOL FEEL FREE TO RECYCLE, CONTINUE WRITING ON IT OR DELETE IT
 */
//    public void playFile(View v){
//        MediaPlayer mp = new MediaPlayer();
//        try {
//            mp.setDataSource();
//            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    mp.start();
//                }
//            });
//            mp.prepareAsync();
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//
//    }

}
