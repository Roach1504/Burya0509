package uk.co.ribot.androidboilerplate.ui.RegistFragment;

import uk.co.ribot.androidboilerplate.ui.base.MvpView;


public interface RegistMvpView extends MvpView {

    void showResponsServer(String status);//вернёт статус регистрации, реализорвать в activity

    void showError();

    //метод для регистрации registr парамектры: registr(String login, String pass, String name
//            , String family
//            , String city
//            , String tel

    //Надо ли в activity id?
}
