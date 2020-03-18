package com.example.test_epic.retrofitServices;

import android.content.Context;
import android.util.Log;

import com.example.test_epic.Constent;
import com.example.test_epic.models.responseModels.getAllPostResponse;
import com.example.test_epic.models.responseModels.getMyFavsResponse;
import com.example.test_epic.models.retrofitModels.getMyFavImages;
import com.example.test_epic.models.retrofitModels.imgureGetMyImages;

import java.lang.ref.WeakReference;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.example.test_epic.activities.bottom_bar.accessToken;
import static com.example.test_epic.activities.bottom_bar.userName;
import static com.example.test_epic.models.retrofitModels.getMyFavImages.Sort.newest;

public class get_my_favs {
    private WeakReference<Context> mContext;

    public get_my_favs(Context context) {
        this.mContext = new WeakReference<>(context);
    }

    public void getMyFavs(Callback<getMyFavsResponse> callback) throws Exception {
        final Callback<getMyFavsResponse> cb = callback;

        RestAdapter restAdapter = buildRestAdapterGetImage();

        restAdapter.create(getMyFavImages.class).getFav(
                "Bearer " + accessToken,
                userName,
                0,
                newest,
                new Callback<getMyFavsResponse>() {
                    @Override
                    public void success(getMyFavsResponse ImageResponse, Response response) {
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
                        Log.d("fail", "ca a fail dans le get_my_fav");
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
