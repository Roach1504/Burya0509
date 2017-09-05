package uk.co.ribot.androidboilerplate.ui.NewsFragment;

import java.util.List;

import uk.co.ribot.androidboilerplate.data.model.ItemNewList;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;



public class NewsFragment extends BaseFragment implements NewsMvpView {
    @Override
    public void showNewsServer(List<ItemNewList> news) {
        //отабразить лист на экран
    }

    @Override
    public void showError() {
        //сообщения об ошибки

    }
}
