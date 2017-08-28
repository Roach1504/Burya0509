package uk.co.ribot.androidboilerplate.ui.prsenterS;


import android.util.Log;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.model.CreadNewModel;
import uk.co.ribot.androidboilerplate.data.model.RegistRespons;
import uk.co.ribot.androidboilerplate.ui.CreadNewFragment.CreadNewMvpView;
import uk.co.ribot.androidboilerplate.ui.RegistFragment.RegistMvpView;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;
import uk.co.ribot.androidboilerplate.util.RxUtil;

public class CreadNewsPresenter extends BasePresenter<CreadNewMvpView> {
    private final DataManager mDataManager;
    private Subscription mSubscription;
    @Inject
    public CreadNewsPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(CreadNewMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void creadNew(String title
            , String shorts
            , String text
            , String date
            , String id){
        RxUtil.unsubscribe(mSubscription);
        mSubscription = mDataManager.getCreadNew(title, shorts, text
                , date
                , id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<CreadNewModel>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.e("error",e.getMessage()+" ||| ");
                        Timber.e(e, "There was an error loading the ribots.");
                        getMvpView().showError();
                    }
                    @Override
                    public void onNext(CreadNewModel respons) {
                        if (respons.getStatus().isEmpty()) {
                            Log.e("TEST", "0000");
                        } else {
                            Log.e("TEST", "id = "+respons.getPostId() + " status: "+ respons.getStatus());
                            getMvpView().showResponsServer(respons.getStatus());
                        }
                    }
                });
    }

}
