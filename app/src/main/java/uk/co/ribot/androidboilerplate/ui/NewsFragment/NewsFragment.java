package uk.co.ribot.androidboilerplate.ui.NewsFragment;

import java.util.List;

import uk.co.ribot.androidboilerplate.data.model.News;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;



public class NewsFragment extends BaseFragment implements NewsMvpView {

    //loadNews для загрузки новостей в качестве параметра отправлять с какого элемента выгружать, выгружать будет по 20
    @Override
    public void showNews(List<News> news) {
        //отобразить лист новостей

    }

    @Override
    public void showError() {
        //сообщения об ошибки

    }
}
