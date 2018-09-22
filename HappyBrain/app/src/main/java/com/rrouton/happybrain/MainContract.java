package com.rrouton.happybrain;

import com.rrouton.happybrain.models.flickr.Photo;

import java.util.List;

public interface MainContract {

    interface View extends BaseView<Presenter> {
        void setPhotos(List<Photo> photos);
    }

    interface Presenter extends BasePresenter{
        void loadPhotos();
    }
}
