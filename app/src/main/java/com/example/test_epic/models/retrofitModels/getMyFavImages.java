package com.example.test_epic.models.retrofitModels;

import com.example.test_epic.models.responseModels.getAllPostResponse;
import com.example.test_epic.models.responseModels.getMyFavsResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;

public interface getMyFavImages {
    String server = "https://api.imgur.com";

    /**
     * @param auth          #Type of authorization for upload
     * @param username      #username for get Fav
     * @param page          #Page for get Fav
     * @param favoritesSort #favoriteSort for get Fav
     * @param cb          Callback used for success/failures
     */
    @GET("/3/account/{me}/favorites/{page}/{favoritesSort}")
    void getFav(
            @Header("Authorization") String auth,
            @Path("me") String username,
            @Path("page") int page,
            @Path("favoritesSort") Sort favoritesSort,
            Callback<getMyFavsResponse> cb
    );


    enum Sort {
        oldest,
        newest
    }
}
