package uk.co.ribot.androidboilerplate.ui.AvtorizFragment;


import uk.co.ribot.androidboilerplate.ui.base.MvpView;

public interface AvtorizMvpView extends MvpView {
    void showStatus(String status);

    void showError();
}
