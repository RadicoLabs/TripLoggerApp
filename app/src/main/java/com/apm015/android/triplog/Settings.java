package com.apm015.android.triplog;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Date;
import java.util.StringTokenizer;
import java.util.UUID;

/**
 * Created by Adam on 24/10/2016.
 */
public class Settings {

    private UUID mUUID;
    private String mName;
    private String mId;
    private String mEmail;
    private String mComment;

    private String mGender;

    public Settings() {
        this(UUID.randomUUID());
    }

    public Settings(UUID id) {
        mUUID = id;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public void setUUID(UUID UUID) {
        mUUID = UUID;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getGender() {
        Log.d("GET GENDER", "Get Gender: " + mGender);
        return mGender;
    }

    public void setGender(String gender) {

        mGender = gender;
        Log.d("SET GENDER", "Set Gender: " + mGender);
    }
}
