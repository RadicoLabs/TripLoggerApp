package com.apm015.android.triplog;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;


import com.apm015.android.triplog.database.PictureUtils;

import java.io.File;
import java.text.DateFormat;
import java.util.UUID;

/**
 * Created by Adam on 24/10/2016.
 */
public class LogFragment extends Fragment{

    private static final int REQUEST_PHOTO = 2;
    private static final String ARG_TRIP_ID = "trip_id";

    private Trip mTrip;

    private EditText mTitle;
    private Button mDate;
    private Spinner mTripType;
    private EditText mDestination;
    private EditText mDuration;
    private EditText mComment;

    private Button mSaveButton;
    private Button mCancelButton;

    private File mPhotoFile;
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;


    public static LogFragment newInstance() {
        Bundle args = new Bundle();

        LogFragment fragment = new LogFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_PHOTO) {
            updatePhotoView();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onPause() {
        super.onPause();

        TripLog.get(getActivity()).updateTrip(mTrip);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_log, container, false);
        PackageManager packageManager = getActivity().getPackageManager();

        mTrip = new Trip();

        mTitle = (EditText) v.findViewById(R.id.et_fragment_log_title_value);
        mTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTrip.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPhotoButton = (ImageButton) v.findViewById(R.id.ib_trip_camera);
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        boolean canTakePhoto = mPhotoFile != null && captureImage.resolveActivity(packageManager) != null;
        Log.d("LOGFRAG", String.valueOf(canTakePhoto));
        mPhotoButton.setEnabled(canTakePhoto);

        if(canTakePhoto) {
            Uri uri = Uri.fromFile(mPhotoFile);
            captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }

        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(captureImage, REQUEST_PHOTO);
            }
        });

        mPhotoView = (ImageView) v.findViewById(R.id.iv_trip_photo);
        updatePhotoView();


        mDate = (Button) v.findViewById(R.id.btn_fragment_log_date_value);
        mDate.setEnabled(false);
        mDate.setText(DateFormat.getDateInstance().format(mTrip.getDate()));

        mTripType = (Spinner) v.findViewById(R.id.tv_fragment_log_tripType_value);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.arr_tripType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTripType.setAdapter(adapter);

        mDestination = (EditText) v.findViewById(R.id.et_fragment_log_destination_value);
        mDestination.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTrip.setDestination(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDuration = (EditText) v.findViewById(R.id.et_fragment_log_duration_value);
        mDuration.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTrip.setDuration(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mComment = (EditText) v.findViewById(R.id.et_fragment_log_comment_value);
        mComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTrip.setComment(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSaveButton = (Button) v.findViewById(R.id.btn_fragment_log_date_SAVE);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTrip.setType(mTripType.getSelectedItem().toString());

                TripLog.get(getActivity()).addTrip(mTrip);
                getActivity().finish();
            }
        });

        mCancelButton = (Button) v.findViewById(R.id.btn_fragment_log_CANCEL);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return v;
    }

    private void updatePhotoView() {
        if(mPhotoFile == null || !mPhotoFile.exists()) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), getActivity());
            mPhotoView.setImageBitmap(bitmap);
        }
    }

}
