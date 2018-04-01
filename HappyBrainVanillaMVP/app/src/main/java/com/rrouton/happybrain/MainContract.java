package com.rrouton.happybrain;

public interface MainContract {

    interface View extends BaseView<Presenter> {

        void setTestText(String text);
    }

    interface Presenter extends BasePresenter{

        void testButtonClicked();
    }
}
