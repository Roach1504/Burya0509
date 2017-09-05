package uk.co.ribot.androidboilerplate.ui.prsenterS;


import android.util.Log;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.model.UserInfo;

import uk.co.ribot.androidboilerplate.ui.UserInfoFragment.UserInfoMvpView;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;
import uk.co.ribot.androidboilerplate.util.RxUtil;

public class UserInfoPresenter extends BasePresenter<UserInfoMvpView> {
    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public UserInfoPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }
    @Override
    public void attachView(UserInfoMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void userInfo(String id) {
        checkViewAttached();
        RxUtil.unsubscribe(mSubscription);
        mSubscription = mDataManager.getUserInfo(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<UserInfo>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading the ribots.");
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(UserInfo info) {
                        if (info == null) {
                            getMvpView().showError();
                        } else {
                            Log.e("Test", "News: "+info.getName() +" "+ info.getFamily());
                            getMvpView().showUserInfo(info);
                            // creadNew("Test2", "Test2","Test2", "26.08.17", "2");
                        }
                    }
                });
    }

}
