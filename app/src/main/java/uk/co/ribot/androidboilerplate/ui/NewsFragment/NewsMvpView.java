package uk.co.ribot.androidboilerplate.ui.NewsFragment;

import java.util.List;

import uk.co.ribot.androidboilerplate.data.model.ItemNewList;



public interface NewsMvpView {

    void showNewsServer(List<ItemNewList> news);

    void showError();

}
