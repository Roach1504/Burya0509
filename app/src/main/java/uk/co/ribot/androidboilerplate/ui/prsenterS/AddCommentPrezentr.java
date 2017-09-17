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
import uk.co.ribot.androidboilerplate.ui.AddCommentFragment.AddCommentMvpView;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;
import uk.co.ribot.androidboilerplate.util.RxUtil;

public class AddCommentPrezentr extends BasePresenter<AddCommentMvpView> {
    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public AddCommentPrezentr(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(AddCommentMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void addComment(String post_id, String user_id, String text, String date) {
        checkViewAttached();
        RxUtil.unsubscribe(mSubscription);
        mSubscription = mDataManager.getStatus(post_id, user_id, text, date)
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
                            //getMvpView().showRibotsEmpty();
                            Log.e("Is", "emty");
                        } else {
                            getMvpView().showStatusAddComents(ribots.getStatus());
                            //getMvpView().showRibots(ribots);
                            //for (Comment i : ribots) {
                            Log.e("News", ribots.getStatus() + " ");
                            //}
                        }
                    }
                });
    }
}
