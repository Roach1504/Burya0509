package uk.co.ribot.androidboilerplate.ui.prsenterS;

import android.util.Log;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.model.StatusModel;
import uk.co.ribot.androidboilerplate.ui.SendMessageFragment.SendMessageMvpView;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;
import uk.co.ribot.androidboilerplate.util.RxUtil;


public class SendMessagePrezentr extends BasePresenter<SendMessageMvpView> {
    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public SendMessagePrezentr(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(SendMessageMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void sendMessage(String from_id, String to_id, String text, String date) {
        checkViewAttached();
        RxUtil.unsubscribe(mSubscription);
        mSubscription = mDataManager.getStatusMessage(from_id, to_id, text, date)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<StatusModel>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading the ribots.");
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(StatusModel ribots) {
                        if (ribots.getStatus().isEmpty()) {
                            getMvpView().showError();
                            Log.e("Is", "emty");
                        } else {
                            getMvpView().showStatusSendMessage(ribots.getStatus());
                            //for (Comment i : ribots) {
                            Log.e("News", ribots.getStatus() + " ");
                            //}
                        }
                    }
                });
    }

}
