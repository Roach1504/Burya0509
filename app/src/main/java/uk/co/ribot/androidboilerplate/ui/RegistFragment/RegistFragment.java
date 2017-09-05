package uk.co.ribot.androidboilerplate.ui.RegistFragment;

import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;



public class RegistFragment extends BaseFragment implements RegistMvpView {

//метод для регистрации registr парамектры: registr(String login, String pass, String name
//            , String family
//            , String city
//            , String tel
    // проверку на включенность инета не делал


    @Override
    public void showResponsServer(String status) {


        //отобразить результат регистрайции, вернёт либо что успешна, либо почему хуйня, типа логин занет и т.п.
    }

    @Override
    public void showError() {

        //сработает тогда когда будет ошибка отправки запроса, типа сервак отвалился и т.п
    }
}
