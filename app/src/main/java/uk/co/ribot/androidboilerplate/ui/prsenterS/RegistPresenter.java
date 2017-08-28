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
import uk.co.ribot.androidboilerplate.ui.RegistFragment.RegistMvpView;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;
import uk.co.ribot.androidboilerplate.ui.main.MainMvpView;
import uk.co.ribot.androidboilerplate.util.RxUtil;


public class RegistPresenter extends BasePresenter<RegistMvpView> {
    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public RegistPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(RegistMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void registr(String login, String pass, String name
            , String family
            , String city
            , String tel){
        RxUtil.unsubscribe(mSubscription);
        mSubscription = mDataManager.getRegist(login, pass, name
                , family
                , city
                , tel)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<RegistRespons>() {
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
                    public void onNext(RegistRespons respons) {
                        if (respons.getId().isEmpty()) {
                            Log.e("TEST", "0000");
                        } else {
                            Log.e("TEST", "id = "+respons.getId() + " status: "+ respons.getStatus());
                            getMvpView().showResponsServer(respons.getStatus());
                        }
                    }
                });
    }
}
