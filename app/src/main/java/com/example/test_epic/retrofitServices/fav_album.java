package com.example.test_epic.retrofitServices;

import android.content.Context;
import android.util.Log;

import com.example.test_epic.Constent;
import com.example.test_epic.models.responseModels.favResponse;
import com.example.test_epic.models.retrofitModels.favAlbum;
import com.example.test_epic.models.retrofitModels.imgureGetMyImages;

import java.lang.ref.WeakReference;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.example.test_epic.activities.bottom_bar.accessToken;

public class fav_album {
    private WeakReference<Context> mContext;
    private String albumHash;

    public fav_album(Context context, String albumHash) {
        this.mContext = new WeakReference<>(context);
        this.albumHash = albumHash;
    }

    public void favAlbum(Callback<favResponse> callback) throws Exception {
        final Callback<favResponse> cb = callback;

        RestAdapter restAdapter = buildRestAdapterGetImage();

        Log.d("access token", accessToken);

        restAdapter.create(favAlbum.class).fav(
                "Bearer " + accessToken,
                albumHash,
                new Callback<favResponse>() {
                    @Override
                    public void success(favResponse ImageResponse, Response response) {
                        if (cb != null) cb.success(ImageResponse, response);
                        if (response == null) {
                            /*
                             Notify image was NOT uploaded successfully
                            */
                            Log.d("success", "ca a reussi mais pas upload");
                            return;
                        }
                        /*
                        Notify image was uploaded successfully
                        */
                        if (ImageResponse.success) {
                            //Log.d("posts returned", String.valueOf(ImageResponse.data));
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        error.printStackTrace();
                        System.err.println(error.getResponse());
                        Log.d("fail", "ca a fail dans le get_my_post");
                        if (cb != null) cb.failure(error);
                    }
                });
    }

    private RestAdapter buildRestAdapterGetImage() {
        RestAdapter imgurAdapter = new RestAdapter.Builder()
                .setEndpoint(imgureGetMyImages.server)
                .build();

        /*
        Set rest adapter logging if we're already logging
        */
        if (Constent.LOGGING)
            imgurAdapter.setLogLevel(RestAdapter.LogLevel.FULL);
        return imgurAdapter;
    }
}
