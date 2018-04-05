package com.rrouton.happybrain.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickrService {

    @GET("/services/rest/?method=flickr.photos.getRecent")
    Observable<FlickrGetRecentPhotosResponse> getRecentPhotos(@Query("per_page") int perPage, @Query("page") int page);
}


