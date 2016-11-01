package com.apm015.android.triplog;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Adam on 9/10/2016.
 */

/*
Trip Object.
Holds
*/


public class Trip {
    private UUID mId;
    private String mTitle;
    private Date mDate;

    private String mType;
    private String mDestination;
    private String mDuration;
    private String mComment;

    public Trip() {
        this(UUID.randomUUID());
    }

    public Trip(UUID id) {
        mId = id;
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    public String getDestination() {
        return mDestination;
    }

    public void setDestination(String destination) {
        mDestination = destination;
    }

    public String getDuration() {
        return mDuration;
    }

    public void setDuration(String duration) {
        mDuration = duration;
    }

    public String getPhotoFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }
}
