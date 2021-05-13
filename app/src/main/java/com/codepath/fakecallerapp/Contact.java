package com.codepath.fakecallerapp;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

@ParseClassName("Contact")
public class Contact extends ParseObject {

    public static final String KEY_CONTACT_NAME = "contactName";
    public static final String KEY_PHONE_NUMBER = "phoneNumber";
    public static final String KEY_AUDIO_FILE = "audioFile";
    /*
     I took out the duration variable since we can get it directly from the audio file. -JL
     */

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

    public ParseFile getAudioFile() {
        return getParseFile(KEY_AUDIO_FILE);
    }

    public void setAudioFile(ParseFile parseFile) {
        put(KEY_AUDIO_FILE, parseFile);
    }
}