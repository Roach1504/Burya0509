package uk.co.ribot.androidboilerplate.ui.MessagerListFragment;

import java.util.List;

import uk.co.ribot.androidboilerplate.data.model.Message;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;


public class MessageFragment extends BaseFragment implements MessagerListMvpView {

    //loadMessages - метод запроса сообщений, параметр id

    @Override
    public void showMessageList(List<Message> messages) {
        //лист сообщений
    }

    @Override
    public void showMessageEmpty() {
        //если список сообщений пуст
    }

    @Override
    public void showError() {

        //ошибка запроса
    }
}
