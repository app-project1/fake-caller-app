package com.codepath.fakecallerapp;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    // Initializes Parse SDK as soon as the application starts
    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Contact.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("kAMoQEu7pNnS4rg7XJsgEcXhC6FGeVBQI7Xh675D")
                .clientKey("1YQ7BY9evXvb8uo5VuV9lG3K2WheTzgaGCrCUdtV")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}