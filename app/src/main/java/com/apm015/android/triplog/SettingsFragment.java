package com.apm015.android.triplog;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.UUID;

/**
 * Created by Adam on 23/10/2016.
 */
public class SettingsFragment extends Fragment {

    private Settings mSettings;

    private EditText mName;
    private EditText mId;
    private EditText mEmail;
    private Spinner mGenderType;
    private EditText mComment;

    public static SettingsFragment newInstance() {
        Bundle args = new Bundle();

        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause(){
        super.onPause();

        mSettings.setGender(mGenderType.toString());
        SettingsLog.get(getActivity()).updateSettings(mSettings);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSettings.setGender(mGenderType.toString());
        SettingsLog.get(getActivity()).updateSettings(mSettings);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        mSettings = SettingsLog.get(getActivity()).getSetting();

        mName = (EditText) v.findViewById(R.id.et_fragment_settings_name_value);
        mName.setText(mSettings.getName());
        mName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mSettings.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mId = (EditText) v.findViewById(R.id.et_fragment_settings_id_value);
        mId.setText(mSettings.getId());
        mId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mSettings.setId(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEmail = (EditText) v.findViewById(R.id.et_fragment_settings_email_value);
        mEmail.setText(mSettings.getEmail());
        mEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mSettings.setEmail(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

        mGenderType = (Spinner) v.findViewById(R.id.spin_fragment_settings_genderType_value);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.arr_genderType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mGenderType.setAdapter(adapter);
        mGenderType.setSelection(adapter.getPosition(mSettings.getGender()));

        //mySpinner.setSelection(arrayAdapter.getPosition("Category 2"));

        mComment = (EditText) v.findViewById(R.id.et_fragment_settings_comment_value);
        mComment.setText(mSettings.getComment());
        mComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mSettings.setComment(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return v;
    }


}
