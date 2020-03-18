package com.example.test_epic.models.retrofitModels;

import com.example.test_epic.models.responseModels.getImageResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;

public interface imgureGetMyImages {
    String server = "https://api.imgur.com";

    /**
     * @param auth        #Type of authorization for upload
     * @param username    username for upload
     * @param page    image's page for upload
     * @param cb          Callback used for success/failures
     */
    @GET("/3/account/{me}/images/{page}")
    void postImage(
            @Header("Authorization") String auth,
            @Path("me") String username,
            @Path("page") String page,
            Callback<getImageResponse> cb
    );
}
