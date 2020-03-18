package com.example.test_epic.retrofitServices;

import android.content.Context;
import android.util.Log;

import com.example.test_epic.Constent;
import com.example.test_epic.models.responseModels.getImageResponse;
import com.example.test_epic.models.retrofitModels.imgureGetMyImages;

import java.lang.ref.WeakReference;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.example.test_epic.activities.bottom_bar.accessToken;
import static com.example.test_epic.activities.bottom_bar.userName;

public class get_my_post {
    private WeakReference<Context> mContext;

    public get_my_post(Context context) {
        this.mContext = new WeakReference<>(context);
    }

    public void getMyPosts(Callback<getImageResponse> callback) throws Exception {
        final Callback<getImageResponse> cb = callback;

        RestAdapter restAdapter = buildRestAdapterGetImage();

        Log.d("access token", accessToken);

        restAdapter.create(imgureGetMyImages.class).postImage(
                "Bearer " + accessToken,
                userName,
                "0",
                new Callback<getImageResponse>() {
                    @Override
                    public void success(getImageResponse ImageResponse, Response response) {
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
                            Log.d("image returned", String.valueOf(ImageResponse.data));
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
