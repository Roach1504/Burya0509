package uk.co.ribot.androidboilerplate.ui.SendMessageFragment;

import uk.co.ribot.androidboilerplate.ui.base.MvpView;


public interface SendMessageMvpView extends MvpView {
    void showStatusSendMessage(String status);

    void showError();
}
