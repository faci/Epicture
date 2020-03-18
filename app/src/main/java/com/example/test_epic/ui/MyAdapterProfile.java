package com.example.test_epic.ui;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.test_epic.R;
import com.example.test_epic.models.responseModels.ImageResponse;
import com.example.test_epic.models.responseModels.favResponse;
import com.example.test_epic.models.responseModels.getMyFavsResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MyAdapterProfile extends RecyclerView.Adapter<MyAdapterProfile.MyViewHolder>{
    private List<ImageResponse.UploadedImage> mDatasetFeed;
    private List<getMyFavsResponse.getAlbum> mDatasetFav;
    private Context context;

    public MyAdapterProfile(Context context, List<ImageResponse.UploadedImage> myDatasetFeed, List<getMyFavsResponse.getAlbum> myDatasetFav) {
        this.context = context;
        this.mDatasetFeed = myDatasetFeed;
        this.mDatasetFav = myDatasetFav;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewVersion;
        ImageView imageViewIcon;
        ImageButton favButton;

        public MyViewHolder(Context context, View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.textViewVersion = (TextView) itemView.findViewById(R.id.textViewVersion);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
            this.favButton = (ImageButton) itemView.findViewById(R.id.fav_post);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapterProfile.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);

        MyAdapterProfile.MyViewHolder myViewHolder = new MyAdapterProfile.MyViewHolder(context, view);
        return myViewHolder;
    }

    public static String getBackgroundImageUrl(String imgHash) {
        return "http://i.imgur.com/" + imgHash + ".png";
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyAdapterProfile.MyViewHolder holder, final int position) {
        if (mDatasetFeed == null) {
            setMyDataFav(holder, position);
        } else {
            setMyDataFeed(holder, position);
        }
    }

    private void setMyDataFeed(final MyAdapterProfile.MyViewHolder holder, final int position) {
        TextView textViewName = holder.textViewName;
        TextView textViewVersion = holder.textViewVersion;
        ImageView imageView = holder.imageViewIcon;
        ImageButton buttonView = holder.favButton;

        final ImageResponse.UploadedImage post = this.mDatasetFeed.get(position);

        String imgUrl = post.link;

        Picasso.with(context)
                .load(imgUrl)
                .placeholder(R.drawable.placeholder_charge)
                .into(imageView);

        textViewName.setText(mDatasetFeed.get(position).title);
        textViewVersion.setText(mDatasetFeed.get(position).description);
        buttonView.setVisibility(View.GONE);
    }

    private void setMyDataFav(final MyAdapterProfile.MyViewHolder holder, final int position) {
        TextView textViewName = holder.textViewName;
        TextView textViewVersion = holder.textViewVersion;
        ImageView imageView = holder.imageViewIcon;
        ImageButton buttonView = holder.favButton;

        final getMyFavsResponse.getAlbum post = mDatasetFav.get(position);

        String imgUrl = post.is_album ? getBackgroundImageUrl(post.cover) : post.link;

        Picasso.with(context)
                .load(imgUrl)
                .placeholder(R.drawable.placeholder_charge)
                .into(imageView);

        textViewName.setText(mDatasetFav.get(position).title);
        textViewVersion.setText(mDatasetFav.get(position).description);
        buttonView.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        if (mDatasetFeed == null)
            return mDatasetFav.size();
        else
            return mDatasetFeed.size();
    }

    private class UiCallback implements Callback<favResponse> {
        private int position;
        private MyAdapterProfile.MyViewHolder holder;

        public UiCallback(final MyAdapterProfile.MyViewHolder holder, final int position) {
            this.position = position;
            this.holder = holder;
        }

        @Override
        public void success(favResponse imageResponse, Response response) {
            Log.d("image faved", "OKKK");
            holder.favButton.setBackgroundColor(Color.RED);
        }

        @Override
        public void failure(RetrofitError error) {
            //Assume we have no connection, since error is null
            if (error == null) {
                //Log.d("no internet", "no internet");
                Toast.makeText(context, R.string.no_internet, Toast.LENGTH_LONG);
            }
        }
    }
}
