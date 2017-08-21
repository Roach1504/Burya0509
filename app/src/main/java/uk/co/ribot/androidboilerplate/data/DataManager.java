package uk.co.ribot.androidboilerplate.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;
import uk.co.ribot.androidboilerplate.data.local.DatabaseHelper;
import uk.co.ribot.androidboilerplate.data.local.PreferencesHelper;
import uk.co.ribot.androidboilerplate.data.model.Example;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.data.model.Talk;
import uk.co.ribot.androidboilerplate.data.model.Weather;
import uk.co.ribot.androidboilerplate.data.remote.ExampleServise;
import uk.co.ribot.androidboilerplate.data.remote.RibotsService;
import uk.co.ribot.androidboilerplate.data.remote.WeatherService;

@Singleton
public class DataManager {

    private final RibotsService mRibotsService;
    private final DatabaseHelper mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final WeatherService mWeatherService;
    private final ExampleServise mExampleServise;

    @Inject
    public DataManager(RibotsService ribotsService, PreferencesHelper preferencesHelper,
                       DatabaseHelper databaseHelper, WeatherService weatherService, ExampleServise exampleServise) {
        mRibotsService = ribotsService;
        mPreferencesHelper = preferencesHelper;
        mDatabaseHelper = databaseHelper;
        mWeatherService = weatherService;
        mExampleServise = exampleServise;

    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

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
}
