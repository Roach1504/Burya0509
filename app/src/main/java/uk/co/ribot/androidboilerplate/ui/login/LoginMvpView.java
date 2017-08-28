package uk.co.ribot.androidboilerplate.ui.login;


import uk.co.ribot.androidboilerplate.ui.base.MvpView;


public interface LoginMvpView extends MvpView {

    void showWeather(String weather);

    void showWeatherEmpty();

    void showError();
}
