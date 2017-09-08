package uk.co.ribot.androidboilerplate.ui.NewsFragment;

import java.util.List;

import uk.co.ribot.androidboilerplate.data.model.News;
import uk.co.ribot.androidboilerplate.ui.base.MvpView;


public interface NewsMvpView extends MvpView {

    void showNews(List<News> news);

    void showError();

}
