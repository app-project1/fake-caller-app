package com.codepath.fakecallerapp;

import android.util.Log;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

@ParseClassName("Contact")
public class Contact extends ParseObject {

    public static final String KEY_CONTACT_NAME = "contactName";
    public static final String KEY_PHONE_NUMBER = "phoneNumber";
    public static final String KEY_AUDIO_FILE = "audioFile";
//    public static ParseFile[] audioFiles = new ParseFile[2];
//    AudioFile aF = new AudioFile();

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

//    public void setAudioFile(String sex) {
//        audioFiles = aF.audio;
//        if (sex.equalsIgnoreCase("Female")){
//            put(KEY_AUDIO_FILE, audioFiles[0]);
//        } else if (sex.equalsIgnoreCase("Male")){
//            put(KEY_AUDIO_FILE, audioFiles[1]);
//        } else {
//            Log.d("Contact", "Not valid sex input");
//        }
//    }

    public ParseFile getAudioFile() {
        return getParseFile(KEY_AUDIO_FILE);
    }
}