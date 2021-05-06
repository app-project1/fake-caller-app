package com.codepath.fakecallerapp;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

@ParseClassName("Contact")
public class Contact extends ParseObject {

    public static final String KEY_CONTACT_NAME = "contactName";
    public static final String KEY_PHONE_NUMBER = "phoneNumber";
    public static final String KEY_AUDIO_FILE = "audioFile";
    public static final String KEY_DURATION = "duration";

    public String getContactName() {
        return getString(KEY_CONTACT_NAME);
    }

    public void setContactName(String contactName) {
        put(KEY_CONTACT_NAME, contactName);
    }

    public Number getPhoneNumber() {
        return getNumber(String.valueOf(KEY_PHONE_NUMBER));
    }

    public void setPhoneNumber(Number phoneNumber) {
        put(String.valueOf(KEY_PHONE_NUMBER), phoneNumber);
    }

    public ParseFile getAudioFile() {
        return getParseFile(KEY_AUDIO_FILE);
    }

    public void setAudioFile(ParseFile parseFile) {
        put(KEY_AUDIO_FILE, parseFile);
    }

    public String getDuration() {
        return getString(KEY_DURATION);
    }

    public void setDuration(String duration) {
        put(KEY_DURATION, duration);
    }
}