package com.codepath.fakecallerapp;

import android.util.Log;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

@ParseClassName("Contact")
public class Contact extends ParseObject {

    public static final String KEY_CONTACT_NAME = "contactName";
    public static final String KEY_PHONE_NUMBER = "phoneNumber";
    public static final String KEY_AUDIO_FILE = "audioFile";
//    public static ParseFile audio;

    public String getContactName() {
        return getString(KEY_CONTACT_NAME);
    }

    public void setContactName(String contactName) {
        put(KEY_CONTACT_NAME, contactName);
    }

    public String getPhoneNumber() {
        return String.valueOf(getNumber(KEY_PHONE_NUMBER));
    }

    public void setPhoneNumber(Number phoneNumber) {
        put(String.valueOf(KEY_PHONE_NUMBER), phoneNumber);
    }

//    public void setAudioFile(String gender) {
//        AudioFile audioFile = new AudioFile();
//        ParseFile audio = audioFile.inflateAudioFile(gender);
//        put(KEY_AUDIO_FILE, audio);
//    }

    public void setAudioFile(ParseFile audio) { put(KEY_AUDIO_FILE, audio);  }

    public ParseFile getAudioFile() {
        return getParseFile(KEY_AUDIO_FILE);
    }
}