package uk.co.ribot.androidboilerplate.ui.UserInfoFragment;

import uk.co.ribot.androidboilerplate.data.model.UserInfo;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;


public class UserInfoFragment extends BaseFragment implements UserInfoMvpView {
    //метод userInfo- запроос на сервер по id
    @Override
    public void showUserInfo(UserInfo info) {
        //info информация о юзере

    }

    @Override
    public void showError() {

    }
}
