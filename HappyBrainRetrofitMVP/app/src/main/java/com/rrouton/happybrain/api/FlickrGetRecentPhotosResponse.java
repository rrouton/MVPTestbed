package com.rrouton.happybrain.api;

import com.rrouton.happybrain.models.flickr.Photos;

public class FlickrGetRecentPhotosResponse extends ApiResponse {

    private Photos photos;

    public Photos getPhotos() {
        return this.photos;
    }
}
