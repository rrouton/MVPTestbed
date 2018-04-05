package com.rrouton.happybrain;

import com.rrouton.happybrain.api.FlickrApi;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
    }

    @Override
    public void getPhotos() {
        flickerApi.getPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(photoList -> mainView.setPhotos(photoList));
    }
}
