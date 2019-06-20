package com.example.shraddha.roomsproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomItemViewHolder>  {

    List<String> mRent;
    List<String> mImages;
    List<String> mCity;
    private LayoutInflater mInflater;
    private Context context;
    String mtype;


    public class RoomItemViewHolder extends RecyclerView.ViewHolder {

        public final ImageView roomImage;
        final RoomAdapter mAdapter;
        public final TextView city;
        public final TextView rent;
        public final TextView type;
        public final LinearLayout roomDetails;
        public final RelativeLayout descriptionLayout;

        public RoomItemViewHolder(View itemView, RoomAdapter adapter) {
            super(itemView);
            roomImage = (ImageView) itemView.findViewById(R.id.roomImage);
            mAdapter = adapter;
            city = (TextView) itemView.findViewById(R.id.city) ;
            rent = (TextView) itemView.findViewById(R.id.rent);
            type = (TextView) itemView.findViewById(R.id.type);
            roomDetails = (LinearLayout) itemView.findViewById(R.id.roomDetails);
            descriptionLayout = (RelativeLayout)itemView.findViewById(R.id.descriptionLayout);
        }
    }

    public RoomAdapter(Context context,List<String> mImages,List<String> mCity,List<String> mRent,String mtype) {
        mInflater = LayoutInflater.from(context);
        this.mImages = mImages;
        this.context = context;
        this.mCity = mCity;
        this.mRent=mRent;
        this.mtype=mtype;
    }

    @NonNull
    @Override
    public RoomAdapter.RoomItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.roomcard, parent, false);
        return new RoomAdapter.RoomItemViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomAdapter.RoomItemViewHolder holder, int position) {

        final String mCurentImage = mImages.get(position);
        final String mCurrentCity = mCity.get(position) ;
        final String mCurrentRent = mRent.get(position);

        Picasso.get().load(mCurentImage).into(holder.roomImage);
        holder.city.setText("City: "+mCurrentCity);
        holder.rent.setText("Rent: "+mCurrentRent);
        holder.type.setText(mtype);
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }
}
