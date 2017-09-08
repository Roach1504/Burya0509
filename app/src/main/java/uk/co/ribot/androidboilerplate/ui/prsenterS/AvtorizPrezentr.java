package uk.co.ribot.androidboilerplate.ui.prsenterS;


import android.util.Log;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.model.RegistRespons;
import uk.co.ribot.androidboilerplate.ui.AvtorizFragment.AvtorizMvpView;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;
import uk.co.ribot.androidboilerplate.util.RxUtil;

public class AvtorizPrezentr extends BasePresenter<AvtorizMvpView> {

    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public AvtorizPrezentr(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(AvtorizMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void avtorizLoading() {
        checkViewAttached();
        RxUtil.unsubscribe(mSubscription);
        mSubscription = mDataManager.getAvtoriz("54321", "54321")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<RegistRespons>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading the USER ID.");
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(RegistRespons ribots) {
                        if (ribots.getId().isEmpty()) {

                        } else {
                            Log.e("testr", ribots.getId() + " =id" + ribots.getStatus());
                            //mDataManager.addID(ribots.getId());
                            getMvpView().showStatus(ribots.getStatus());
                            //Log.e("inDB", "to DB" + mDataManager.getUserID());
                        }
                    }
                });
    }
}
