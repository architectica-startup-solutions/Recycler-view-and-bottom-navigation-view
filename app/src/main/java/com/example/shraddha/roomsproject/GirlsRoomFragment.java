package com.example.shraddha.roomsproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GirlsRoomFragment extends Fragment {


    List<String> mRent;
    List<String> mImages;
    List<String> mCity;
    private LayoutInflater mInflater;
    private Context context;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference, mReference;
    RecyclerView mRoomsRecyclerView2;
    RoomAdapter mRoomAdapter2;
    TextView mToolText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_girls_room, container, false);
        mRent = new ArrayList<String>();
        mImages = new ArrayList<String>();
        mCity = new ArrayList<String>();
        context = container.getContext();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mReference = mFirebaseDatabase.getReference();
        mRoomsRecyclerView2 = (RecyclerView) view.findViewById(R.id.rooms_recycler_view);

        mDatabaseReference = mFirebaseDatabase.getReference("Girls/");
        assignValues(mDatabaseReference);

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mDatabaseReference = mFirebaseDatabase.getReference("Girls/");
                assignValues(mDatabaseReference);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }

    public void assignValues(DatabaseReference databaseReference) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mImages.clear();
                mRent.clear();
                mCity.clear();

                for (final DataSnapshot childSnap : dataSnapshot.getChildren()) {
                    mImages.add(childSnap.child("Photo").getValue(String.class));
                    mCity.add(childSnap.child("City").getValue(String.class));
                    mRent.add(childSnap.child("Rent").getValue(String.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mRoomAdapter2 =new RoomAdapter(context, mImages, mCity, mRent, "Girls Rooms");
        mRoomsRecyclerView2.setAdapter(mRoomAdapter2);
        mRoomsRecyclerView2.setLayoutManager(new LinearLayoutManager(context));
        mRoomsRecyclerView2.setMotionEventSplittingEnabled(false);
    }
}