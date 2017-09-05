package uk.co.ribot.androidboilerplate.ui.UserInfoFragment;

import uk.co.ribot.androidboilerplate.data.model.UserInfo;
import uk.co.ribot.androidboilerplate.ui.base.MvpView;


public interface UserInfoMvpView extends MvpView {
    void showUserInfo(UserInfo info);
    void showError();
}
