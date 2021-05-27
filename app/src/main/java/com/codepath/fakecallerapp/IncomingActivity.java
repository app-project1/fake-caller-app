package com.codepath.fakecallerapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class IncomingActivity extends AppCompatActivity{
    public static final String TAG = "IncomingActivity";
    private Contact contact;
    private TextView tvName;
    private Button btnDecline;
    private Button btnAccept;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming);
        Log.i(TAG, "Incoming call created");

        tvName = findViewById(R.id.tvName);
        btnDecline = findViewById(R.id.btnDecline);
        btnAccept = findViewById(R.id.btnAccept);

        // don't show action bar here
        getSupportActionBar().hide();

        contact = getIntent().getParcelableExtra("contact");
        tvName.setText(contact.getContactName());

        // play ringtone when activity starts
        MediaPlayer mp = new MediaPlayer();
        try {
            mp.setDataSource("https://www.thesoundarchive.com/ringtones/AD-FinalCountdown_pt2.mp3");
            mp.prepare();
            mp.start();

            // if no button clicked, finish activity when ringtone finishes
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    finish();
                }
            });
        } catch (IOException e) {
            Log.e(TAG, "Error playing ringtone");
            e.printStackTrace();
        }

        // finish activity if call declined
        btnDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                mp.release();
                finish();
            }
        });

        // go to calling screen and finish this activity if call accepted
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                mp.release();
                Intent intent = new Intent(IncomingActivity.this, OutgoingActivity.class);
                intent.putExtra("contact", contact);
                startActivity(intent);
                finish();
            }
        });
    }
}
