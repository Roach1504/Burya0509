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
import uk.co.ribot.androidboilerplate.data.model.Comment;
import uk.co.ribot.androidboilerplate.ui.CommentFragment.CommentsMvpView;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;
import uk.co.ribot.androidboilerplate.util.RxUtil;


public class CommentsPrezentr extends BasePresenter<CommentsMvpView> {
    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public CommentsPrezentr(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(CommentsMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void loadComments(String id_post) {
        checkViewAttached();
        RxUtil.unsubscribe(mSubscription);
        mSubscription = mDataManager.getComments(id_post)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Comment>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading the ribots.");
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(List<Comment> ribots) {
                        if (ribots.isEmpty()) {
                            getMvpView().showCommentsEmpty();
                            Log.e("Is", "emty");
                        } else {
                            getMvpView().showComments(ribots);
                            for (Comment i : ribots) {
                                Log.e("News", i.getText() + " ");
                            }
                        }
                    }
                });
    }

}
