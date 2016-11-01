package com.apm015.android.triplog;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apm015.android.triplog.database.PictureUtils;

import java.io.File;
import java.net.URI;
import java.text.DateFormat;
import java.util.UUID;

import javax.xml.datatype.Duration;

/**
 * Created by Adam on 9/10/2016.
 */
public class TripFragment extends Fragment {

    private static final String ARG_TRIP_ID = "trip_id";
    private static final int REQUEST_PHOTO = 2;

    private Trip mTrip;
    private EditText mTitleField;
    private Button mDateButton;

    private TextView mType;
    private TextView mDestination;
    private TextView mDuration;
    private TextView mComment;

    private Button mDeleteButton;

    private File mPhotoFile;
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;

    public static TripFragment newInstance(UUID tripId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TRIP_ID, tripId);

        TripFragment fragment = new TripFragment();
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
        //mTrip = new Trip();

        UUID tripId = (UUID) getArguments().getSerializable(ARG_TRIP_ID);
        mTrip = TripLog.get(getActivity()).getTrip(tripId);
        mPhotoFile  = TripLog.get(getActivity()).getPhotoFile(mTrip);

    }

    @Override
    public void onPause() {
        super.onPause();

        TripLog.get(getActivity()).updateTrip(mTrip);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_trip, container, false);
        PackageManager packageManager = getActivity().getPackageManager();

        mTitleField = (EditText) v.findViewById(R.id.et_tripTitle);
        mTitleField.setText(mTrip.getTitle());

        mDateButton = (Button) v.findViewById(R.id.btn_fragment_trip_date_value);
        mDateButton.setText(DateFormat.getDateInstance().format(mTrip.getDate()));
        mDateButton.setEnabled(false);
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               }
        });

        mType = (TextView) v.findViewById(R.id.tv_fragment_trip_type_value);
        mType.setText(mTrip.getType());

        mDestination = (TextView) v.findViewById(R.id.tv_fragment_trip_destination_value);
        mDestination.setText(mTrip.getDestination());

        mDuration = (TextView) v.findViewById(R.id.tv_fragment_trip_duration_value);
        mDuration.setText(mTrip.getDuration());

        mComment = (TextView) v.findViewById(R.id.tv_fragment_trip_comment_value);
        mComment.setText(mTrip.getComment());

        mDeleteButton = (Button) v.findViewById(R.id.btn_fragment_trip_DELETE);
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TripLog.get(getActivity()).deleteTrip(mTrip);
                getActivity().finish();
            }
        });

        mPhotoButton = (ImageButton) v.findViewById(R.id.ib_trip_camera);
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        boolean canTakePhoto = mPhotoFile != null && captureImage.resolveActivity(packageManager) != null;
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
