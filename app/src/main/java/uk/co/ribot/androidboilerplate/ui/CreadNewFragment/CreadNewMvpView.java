package uk.co.ribot.androidboilerplate.ui.CreadNewFragment;

import uk.co.ribot.androidboilerplate.ui.base.MvpView;



public interface CreadNewMvpView extends MvpView{
    void showResponsServer(String status);//вернёт статус добавления поста, реализорвать в activity

    void showError();
}
