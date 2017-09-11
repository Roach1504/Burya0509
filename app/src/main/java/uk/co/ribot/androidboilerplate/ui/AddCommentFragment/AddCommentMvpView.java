package uk.co.ribot.androidboilerplate.ui.AddCommentFragment;


import uk.co.ribot.androidboilerplate.ui.base.MvpView;

public interface AddCommentMvpView extends MvpView {
    void showStatusAddComents(String status);

    void showError();
}
