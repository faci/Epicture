package com.example.test_epic.models.retrofitModels;

import com.example.test_epic.models.responseModels.getAllPostResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;

public interface getAllPosts {
    String server = "https://api.imgur.com";

    /**
     * @param auth        #Type of authorization for upload
     * @param section    section for get
     * @param sort       sort page for get
     * @param page       image's page for get
     * @param cb          Callback used for success/failures
     */
    @GET("/3/gallery/{section}/{sort}/{page}")
    void postImage(
            @Header("Authorization") String auth,
            @Path("section") Section section,
            @Path("sort") Sort sort,
            @Path("page") int page,
            Callback<getAllPostResponse> cb
    );

    enum Section {
        hot,
        top,
        user
    }

    enum Sort {
        viral,
        top,
        time,
        rising
    }
}
