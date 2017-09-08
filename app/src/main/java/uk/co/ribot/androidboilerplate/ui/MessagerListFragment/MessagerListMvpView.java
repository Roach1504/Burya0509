package uk.co.ribot.androidboilerplate.ui.MessagerListFragment;

import java.util.List;

import uk.co.ribot.androidboilerplate.data.model.Message;
import uk.co.ribot.androidboilerplate.ui.base.MvpView;


public interface MessagerListMvpView extends MvpView {
    void showMessageList(List<Message> messages);

    void showMessageEmpty();

    void showError();

}
