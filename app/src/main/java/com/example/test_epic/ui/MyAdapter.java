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

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_epic.R;
import com.example.test_epic.models.responseModels.favResponse;
import com.example.test_epic.models.responseModels.getAllPostResponse;
import com.example.test_epic.models.responseModels.getMyFavsResponse;
import com.example.test_epic.retrofitServices.fav_album;
import com.example.test_epic.retrofitServices.fav_image;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<getAllPostResponse.getAlbum> mDataset;
    private Context context;

    public MyAdapter(Context context, List<getAllPostResponse.getAlbum> myDataset) {
        this.context = context;
        this.mDataset = myDataset;
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
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(context, view);
        return myViewHolder;
    }

    public static String getBackgroundImageUrl(String imgHash) {
        return "http://i.imgur.com/" + imgHash + ".png";
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        TextView textViewName = holder.textViewName;
        TextView textViewVersion = holder.textViewVersion;
        ImageView imageView = holder.imageViewIcon;
        ImageButton buttonView = holder.favButton;

        final getAllPostResponse.getAlbum post = mDataset.get(position);

        if (post.favorite)
            holder.favButton.setColorFilter(ContextCompat.getColor(context, R.color.colorAccent));

        holder.favButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (post.is_album) {
                    try {
                        new fav_album(context, post.id).favAlbum(new UiCallback(holder, position));
                        holder.favButton.setColorFilter(ContextCompat.getColor(context, R.color.colorAccent));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        new fav_image(context, post.id).favImage(new UiCallback(holder, position));
                        holder.favButton.setColorFilter(ContextCompat.getColor(context, R.color.colorAccent));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        String imgUrl = post.is_album ? getBackgroundImageUrl(post.cover) : post.link;

        Picasso.with(context)
                .load(imgUrl)
                .placeholder(R.drawable.placeholder_charge)
                .into(imageView);

        textViewName.setText(mDataset.get(position).title);
        textViewVersion.setText(mDataset.get(position).description);
        if (post.favorite) {
            Log.d("is favorite", String.valueOf(post.favorite));
            buttonView.setBackgroundColor(Color.RED);
        } else
            buttonView.setBackgroundColor(Color.BLUE);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    private class UiCallback implements Callback<favResponse> {
        private int position;
        private MyViewHolder holder;

        public UiCallback(final MyViewHolder holder, final int position) {
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
