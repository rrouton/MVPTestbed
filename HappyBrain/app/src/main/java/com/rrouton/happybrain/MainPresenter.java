package com.rrouton.happybrain;

import com.rrouton.happybrain.api.FlickrApi;

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View mainView;

    private FlickrApi flickerApi;

    public MainPresenter(MainContract.View mainView) {
        this.mainView = mainView;
        mainView.setPresenter(this);
    }

    @Override
    public void start() {
        flickerApi = new FlickrApi();
        flickerApi.getPhotos(photos -> mainView.setPhotos(photos));
    }
}
