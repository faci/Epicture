package com.example.test_epic.models.retrofitModels;

import com.example.test_epic.models.responseModels.favResponse;

import retrofit.Callback;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;

public interface favAlbum {
    String server = "https://api.imgur.com";

    /**
     * @param auth        #Type of authorization for upload
     * @param albumHash       #Title of image
     * @param cb          Callback used for success/failures
     */
    @POST("/3/album/{albumHash}/favorite")
    void fav(
            @Header("Authorization") String auth,
            @Path("albumHash") String albumHash,
            Callback<favResponse> cb
    );
}
