package com.rrouton.happybrain.api;

import com.rrouton.happybrain.models.flickr.Photos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickrService {

    @GET("/services/rest/?method=flickr.photos.getRecent")
    Call<FlickrGetRecentPhotosResponse> getRecentPhotos(@Query("per_page") int perPage, @Query("page") int page);
}


