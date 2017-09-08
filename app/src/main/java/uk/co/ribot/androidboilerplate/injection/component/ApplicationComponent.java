package uk.co.ribot.androidboilerplate.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.SyncService;
import uk.co.ribot.androidboilerplate.data.local.DatabaseHelper;
import uk.co.ribot.androidboilerplate.data.local.PreferencesHelper;
import uk.co.ribot.androidboilerplate.data.remote.AvtorizationServise;
import uk.co.ribot.androidboilerplate.data.remote.CreadNewsServise;
import uk.co.ribot.androidboilerplate.data.remote.ExampleServise;
import uk.co.ribot.androidboilerplate.data.remote.ListNewServise;
import uk.co.ribot.androidboilerplate.data.remote.MessageServise;
import uk.co.ribot.androidboilerplate.data.remote.NewsListServise;
import uk.co.ribot.androidboilerplate.data.remote.RegistServise;
import uk.co.ribot.androidboilerplate.data.remote.RibotsService;
import uk.co.ribot.androidboilerplate.data.remote.UserInfoServese;
import uk.co.ribot.androidboilerplate.data.remote.UserService;
import uk.co.ribot.androidboilerplate.data.remote.WeatherService;
import uk.co.ribot.androidboilerplate.injection.ApplicationContext;
import uk.co.ribot.androidboilerplate.injection.module.ApplicationModule;
import uk.co.ribot.androidboilerplate.util.RxEventBus;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);

    void inject(UserService userService);

    @ApplicationContext Context context();
    Application application();
    RibotsService ribotsService();
    PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    DataManager dataManager();
    RxEventBus eventBus();
    WeatherService newWeatherService();
    ExampleServise newExampleService();
    RegistServise newRegistServise();
    CreadNewsServise newCreadNewsServise();
    ListNewServise newListNewServise();
    UserInfoServese userInfoServese();

    AvtorizationServise avtorizationServise();

    NewsListServise newsListServise();

    MessageServise messageServise();


}
