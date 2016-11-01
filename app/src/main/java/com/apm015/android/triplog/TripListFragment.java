package com.apm015.android.triplog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.text.DateFormat;
import java.util.List;

import javax.xml.datatype.Duration;


/**
 * Created by Adam on 9/10/2016.
 */
public class TripListFragment extends Fragment {

    private RecyclerView mTripRecyclerView;
    private TripAdapter mAdapter;

    //private Button mLogButton;
    //private Button mSettingsButton;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_trip_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        switch(item.getItemId()) {
            case R.id.menu_item_new_trip:
                intent = new LogActivity().newIntent(getActivity());
                startActivity(intent);
                return true;

            case R.id.menu_account_settings:
                intent = new SettingsActivity().newIntent(getActivity());
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trip_list, container, false);

        mTripRecyclerView = (RecyclerView) view.findViewById(R.id.trip_recycler_view);
        mTripRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        /*
        mLogButton = (Button) view.findViewById(R.id.btn_tripList_log);
        mLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new LogActivity().newIntent(getActivity());
                startActivity(intent);
            }
        });

        mSettingsButton = (Button) view.findViewById(R.id.btn_tripList_settings);
        mSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText( getContext(), "Settings", Toast.LENGTH_SHORT).show();
                Intent intent = new SettingsActivity().newIntent(getActivity());
                startActivity(intent);
            }
        });
*/
        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    public void updateUI() {
        TripLog tripLog = TripLog.get(getActivity());
        List<Trip> trips = tripLog.getTrips();

        if(mAdapter == null) {
            mAdapter = new TripAdapter(trips);
            mTripRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setTrips(trips);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class TripHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Trip mTrip;

        //public TextView mTitleTextView;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private TextView mDestinationTextView;

        public TripHolder(View itemView) {
            super(itemView);
            //mTitleTextView = (TextView) itemView;
            mTitleTextView = (TextView) itemView.findViewById(R.id.tv_list_item_title_value);
            mDateTextView = (TextView) itemView.findViewById(R.id.tv_list_item_date_value);
            mDestinationTextView = (TextView) itemView.findViewById(R.id.tv_list_item_destination_value);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v  ) {
            Intent intent = new TripActivity().newIntent(getActivity(), mTrip.getId());
            startActivity(intent);
        }

        public void bindTrip(Trip trip) {
            mTrip = trip;
            mTitleTextView.setText(mTrip.getTitle());
            mDateTextView.setText(DateFormat.getDateInstance().format(mTrip.getDate()));
            mDestinationTextView.setText(mTrip.getDestination());

        }
    }

    private class TripAdapter extends RecyclerView.Adapter<TripHolder> {

        private List<Trip> mTrips;

        public TripAdapter(List<Trip> trips){
            mTrips = trips;
        }

        @Override
        public TripHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_trip, parent, false);

            return new TripHolder(view);
        }

        @Override
        public void onBindViewHolder(TripHolder holder, int position) {
            Trip trip = mTrips.get(position);
            //holder.mTitleTextView.setText(trip.getTitle());
            holder.bindTrip(trip);
        }

        @Override
        public int getItemCount() {
            return mTrips.size();
        }

        public void setTrips(List<Trip> trips) {
            mTrips = trips;
        }
    }

}
