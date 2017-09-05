package uk.co.ribot.androidboilerplate.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;
import uk.co.ribot.androidboilerplate.data.local.DatabaseHelper;
import uk.co.ribot.androidboilerplate.data.local.PreferencesHelper;
import uk.co.ribot.androidboilerplate.data.model.CreadNewModel;
import uk.co.ribot.androidboilerplate.data.model.ItemNewList;
import uk.co.ribot.androidboilerplate.data.model.NewsModel;
import uk.co.ribot.androidboilerplate.data.model.UserInfo;
import uk.co.ribot.androidboilerplate.data.model.inUtilization.Example;
import uk.co.ribot.androidboilerplate.data.model.RegistModel;
import uk.co.ribot.androidboilerplate.data.model.RegistRespons;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.data.model.inUtilization.Talk;
import uk.co.ribot.androidboilerplate.data.model.inUtilization.Weather;
import uk.co.ribot.androidboilerplate.data.remote.CreadNewsServise;
import uk.co.ribot.androidboilerplate.data.remote.ExampleServise;
import uk.co.ribot.androidboilerplate.data.remote.ListNewServise;
import uk.co.ribot.androidboilerplate.data.remote.RegistServise;
import uk.co.ribot.androidboilerplate.data.remote.RibotsService;
import uk.co.ribot.androidboilerplate.data.remote.UserInfoServese;
import uk.co.ribot.androidboilerplate.data.remote.WeatherService;

@Singleton
public class DataManager {

    private final RibotsService mRibotsService;
    private final DatabaseHelper mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final WeatherService mWeatherService;
    private final ExampleServise mExampleServise;
    private final RegistServise mRegistServise;
    private final CreadNewsServise mCreadNewsServise;
    private final ListNewServise mListNewServise;
    private final UserInfoServese mUserInfoServese;


    @Inject
    public DataManager(RibotsService ribotsService, PreferencesHelper preferencesHelper,
                       DatabaseHelper databaseHelper, WeatherService weatherService, ExampleServise exampleServise, RegistServise registServise, CreadNewsServise creadNewsServise,
                       ListNewServise listNewServise, UserInfoServese userInfoServese) {
        mRibotsService = ribotsService;
        mPreferencesHelper = preferencesHelper;
        mDatabaseHelper = databaseHelper;
        mWeatherService = weatherService;
        mExampleServise = exampleServise;
        mRegistServise = registServise;
        mCreadNewsServise = creadNewsServise;
        mListNewServise = listNewServise;
        mUserInfoServese = userInfoServese;
        // TODO: 28.08.2017 удалить лишнии параметры

    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    //--------------------всё что ниже то не надо----------------------------------------------------------

    public Observable<Ribot> syncRibots() {
        return mRibotsService.getRibots()
                .concatMap(new Func1<List<Ribot>, Observable<Ribot>>() {
                    @Override
                    public Observable<Ribot> call(List<Ribot> ribots) {
                        return mDatabaseHelper.setRibots(ribots);
                    }
                });
    }

    public Observable<List<Ribot>> getRibots() {
        return mDatabaseHelper.getRibots().distinct();
    }

    public Observable<String> getWeather() {
        return
                mWeatherService.getWeather().map(new Func1<Weather, String>() {
                    @Override
                    public String call(Weather weather) {
                        return weather.getCurrentObservation().getTempC().toString();
                    }
                });
    }

    public Observable<List<Talk>> getExample(String page) {
        return
                mExampleServise.getExample(page).map(new Func1<Example, List<Talk>>() {
                    @Override
                    public List<Talk> call(Example example) {
                        return example.getTalks();
                    }
                });
    }


    //--------------------всё что выше то не надо----------------------------------------------------------


    public Observable<RegistRespons> getRegist(String login, String pass, String name
            , String family
            , String city
            , String tel) {
        return
                mRegistServise.getRegist(login, pass, name
                        , family
                        , city
                        , tel).map(new Func1<RegistModel, RegistRespons>() {
                    @Override
                    public RegistRespons call(RegistModel registModel) {
                        return registModel.getRespons();
                    }
                });
    }

    public Observable<CreadNewModel> getCreadNew(String title, String shorts, String text
            , String date
            , String id) {
        return
                mCreadNewsServise.getCreadNew(title
                        , shorts
                        , text
                        , date
                        , id).map(new Func1<CreadNewModel, CreadNewModel>() {
                    @Override
                    public CreadNewModel call(CreadNewModel creadNewModel) {
                        return creadNewModel;
                    }
                });

    }

    public Observable<ItemNewList> getNewsModel(String limit, String offset) {
        return
                mListNewServise.getNews(limit
                        , offset).map(new Func1<ItemNewList,ItemNewList>() {
                    @Override
                    public ItemNewList call(ItemNewList itemNews) {
                        return itemNews;
                    }
                });

    }

    public Observable<UserInfo> getUserInfo (String id){
        return
                mUserInfoServese.getUserInfo(id).map(new Func1<UserInfo, UserInfo>() {
                    @Override
                    public UserInfo call(UserInfo registModel) {
                        return registModel;
                    }
                });

    }



}