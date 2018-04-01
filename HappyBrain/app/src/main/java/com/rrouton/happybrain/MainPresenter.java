package com.rrouton.happybrain;

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View mainView;

    private boolean clicked = false;

    public MainPresenter(MainContract.View mainView) {
        this.mainView = mainView;
        mainView.setPresenter(this);
    }

    @Override
    public void start() {
        mainView.setTestText("Waiting for you to click it");
    }

    @Override
    public void testButtonClicked() {
        if (!clicked) {
            mainView.setTestText("I done clicked it");
        } else {
            mainView.setTestText("I done UN-clicked it");
        }
        clicked = !clicked;
    }
}
