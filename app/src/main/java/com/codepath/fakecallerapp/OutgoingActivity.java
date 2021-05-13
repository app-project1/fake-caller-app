package com.codepath.fakecallerapp;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseFile;

import java.io.IOException;

public class OutgoingActivity extends AppCompatActivity {
    public static final String TAG = "OutgoingActivity";

    private Contact contact;
    private TextView name;
    private int durationInMillis;
    private TextView time;
    private CountDownTimer timer;
    private Button btnEndCall;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outgoing);
        name = findViewById(R.id.tvName);
        time = findViewById(R.id.tvTime);
        btnEndCall = findViewById(R.id.rButton);

        // don't show action bar here
        getSupportActionBar().hide();

        contact = getIntent().getParcelableExtra("contact");
        name.setText(contact.getContactName());
        time.setText("Calling...");

        playFile(contact.getAudioFile());
    }

    public void playFile(ParseFile file) {
        MediaPlayer mp = new MediaPlayer();
        try {
            // pass in the file URL to play
            mp.setDataSource(getApplicationContext(), Uri.parse(file.getUrl()));
            mp.prepare();

            durationInMillis = Integer.parseInt((String.valueOf(mp.getDuration())));
            Log.i(TAG, "duration: " + durationInMillis);
            timer = new CountDownTimer(durationInMillis, 1) {
                @Override
                public void onTick(long millisUntilFinished) {
                    // current length of call in seconds
                    long position = (durationInMillis - millisUntilFinished) / 1000;
                    Log.i(TAG, "position: " + position);
                    // count up the time, instead of down
                    time.setText(String.format("%02d:%02d", position / 60, position % 60));
                }

                @Override
                public void onFinish() {
                    mp.stop();
                    time.setText("Call ended");
                    finish();
                }
            };

            // start audio
            mp.start();
            // start timer
            timer.start();

            // to end call early
            btnEndCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    timer.cancel();
                    mp.stop();
                    time.setText("Call ended");
                    finish();
                }
            });
        } catch (IOException e) {
            Log.e(TAG, "Error playing file");
            e.printStackTrace();
        }
    }
}
