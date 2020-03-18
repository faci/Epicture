package com.example.test_epic.retrofitServices;

import android.content.Context;
import android.util.Log;

import com.example.test_epic.uplaodImageHelper.NotificationHelper;
import com.example.test_epic.models.responseModels.ImageResponse;
import com.example.test_epic.models.retrofitModels.imgureUploadModel;
import com.example.test_epic.models.uploadImage;
import com.example.test_epic.Constent;

import java.lang.ref.WeakReference;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

import static com.example.test_epic.activities.bottom_bar.accessToken;
import static com.example.test_epic.activities.bottom_bar.userName;

public class upload_post {

    private WeakReference<Context> mContext;

    public upload_post(Context context) {
        this.mContext = new WeakReference<>(context);
    }

    public void uploadPost(uploadImage upload, Callback<ImageResponse> callback) {
        final Callback<ImageResponse> cb = callback;
        final NotificationHelper notificationHelper = new NotificationHelper(mContext.get());

        notificationHelper.createUploadingNotification();
        RestAdapter restAdapter = buildRestAdapter();

        restAdapter.create(imgureUploadModel.class).postImage(
                "Bearer " + accessToken,
                upload.title,
                upload.description,
                upload.albumId,
                userName,
                new TypedFile("image/*", upload.image),
                new Callback<ImageResponse>() {
                    @Override
                    public void success(ImageResponse imageResponse, Response response) {
                        if (cb != null) cb.success(imageResponse, response);
                        if (response == null) {
                            /*
                             Notify image was NOT uploaded successfully
                            */
                            Log.d("success", "ca a reussi mais pas upload");
                            notificationHelper.createFailedUploadNotification();
                            return;
                        }
                        /*
                        Notify image was uploaded successfully
                        */
                        if (imageResponse.success) {
                            Log.d("image returned", String.valueOf(imageResponse.data));
                            Log.d("success", "ca a reussit mais alors ca fait rien");
                            notificationHelper.createUploadedNotification(imageResponse);
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        error.printStackTrace();
                        System.err.println(error.getResponse());
                        Log.d("fail", "ca a fail");
                        if (cb != null) cb.failure(error);
                        notificationHelper.createFailedUploadNotification();
                    }
                });
    }

    private RestAdapter buildRestAdapter() {
        RestAdapter imgurAdapter = new RestAdapter.Builder()
                .setEndpoint(imgureUploadModel.server)
                .build();

        /*
        Set rest adapter logging if we're already logging
        */
        if (Constent.LOGGING)
            imgurAdapter.setLogLevel(RestAdapter.LogLevel.FULL);
        return imgurAdapter;
    }
}
