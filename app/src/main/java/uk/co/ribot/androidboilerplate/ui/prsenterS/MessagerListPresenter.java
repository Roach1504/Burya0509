package uk.co.ribot.androidboilerplate.ui.prsenterS;


import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.model.Message;
import uk.co.ribot.androidboilerplate.ui.MessagerListFragment.MessagerListMvpView;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;
import uk.co.ribot.androidboilerplate.util.RxUtil;

public class MessagerListPresenter extends BasePresenter<MessagerListMvpView> {
    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public MessagerListPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(MessagerListMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }


    public void loadMessages(String id) {
        checkViewAttached();
        RxUtil.unsubscribe(mSubscription);
        mSubscription = mDataManager.getMessage(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Message>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading the ribots.");
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(List<Message> ribots) {
                        if (ribots.isEmpty()) {
                            getMvpView().showMessageEmpty();
                        } else {
                            getMvpView().showMessageList(ribots);
                            for (Message i : ribots) {
                                Log.e("News", i.getText() + " ");
                            }
                        }
                    }
                });
    }

}
