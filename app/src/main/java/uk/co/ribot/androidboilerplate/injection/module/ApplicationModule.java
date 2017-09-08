package uk.co.ribot.androidboilerplate.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.ribot.androidboilerplate.data.remote.AvtorizationServise;
import uk.co.ribot.androidboilerplate.data.remote.CreadNewsServise;
import uk.co.ribot.androidboilerplate.data.remote.ExampleServise;
import uk.co.ribot.androidboilerplate.data.remote.ListNewServise;
import uk.co.ribot.androidboilerplate.data.remote.MessageServise;
import uk.co.ribot.androidboilerplate.data.remote.NewsListServise;
import uk.co.ribot.androidboilerplate.data.remote.RegistServise;
import uk.co.ribot.androidboilerplate.data.remote.RibotsService;
import uk.co.ribot.androidboilerplate.data.remote.UserInfoServese;
import uk.co.ribot.androidboilerplate.data.remote.WeatherService;
import uk.co.ribot.androidboilerplate.injection.ApplicationContext;

/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    RibotsService provideRibotsService() {
        return RibotsService.Creator.newRibotsService();
    }


    @Provides
    @Singleton
    WeatherService newWeatherService() {
        return WeatherService.Creator.newWeatherService();
    }

    @Provides
    @Singleton
    ExampleServise newExampleService() {
        return ExampleServise.Creator.newExampleService();
    }

    @Provides
    @Singleton
    RegistServise newRegistServise() {
        return RegistServise.Creator.newRegistServise();
    }

    @Provides
    @Singleton
    CreadNewsServise newCreadNewsServise() {
        return CreadNewsServise.Creator.newCreadNewsServise();
    }

    @Provides
    @Singleton
    ListNewServise newListNewServise() {
        return ListNewServise.Creator.newListNewServise();

    }

    @Provides
    @Singleton
    UserInfoServese userInfoServese() {
        return UserInfoServese.Creator.userInfoServese();
    }

    @Provides
    @Singleton
    AvtorizationServise avtorizationServise() {
        return AvtorizationServise.Creator.avtorizationServise();
    }

    @Provides
    @Singleton
    NewsListServise newsListServise() {
        return NewsListServise.Creator.newsListServise();
    }

    @Provides
    @Singleton
    MessageServise messageServise() {
        return MessageServise.Creator.messageServise();
    }

}
