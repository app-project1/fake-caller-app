package com.codepath.fakecallerapp;

import android.util.Log;

import com.parse.GetCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

@ParseClassName("FakeAudio")
public class AudioFile extends Contact{
    ParseFile audio;

    public AudioFile(){}

    public ParseFile inflateAudioFile(String gender) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("FakeAudio");
        if (gender.equals("Female")) {
            // query female audio
            query.whereEqualTo("objectId", "PJHwt7OGWl");
            query.getFirstInBackground(new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject contact, ParseException e) {
                    if (e == null) {
                        audio = contact.getParseFile("audioFile");
                    } else {
                        Log.d("AudioFile", "Error getting female audio file");
                    }
                }
            });
        }

        if (gender.equals("Male")) {
            // query male audio
            query.whereEqualTo("objectId", "EwrSistsE9");
            query.getFirstInBackground(new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject contact, ParseException e) {
                    if (e == null) {
                        audio = contact.getParseFile("audioFile");
                    } else {
                        Log.d("AudioFile", "Error getting male audio file");
                    }
                }
            });
        }
        return audio;
    }
}
