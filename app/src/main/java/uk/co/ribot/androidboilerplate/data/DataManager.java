package uk.co.ribot.androidboilerplate.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;
import uk.co.ribot.androidboilerplate.data.local.DatabaseHelper;
import uk.co.ribot.androidboilerplate.data.local.PreferencesHelper;
import uk.co.ribot.androidboilerplate.data.model.ComentsListModel;
import uk.co.ribot.androidboilerplate.data.model.Comment;
import uk.co.ribot.androidboilerplate.data.model.CreadNewModel;
import uk.co.ribot.androidboilerplate.data.model.Message;
import uk.co.ribot.androidboilerplate.data.model.MessageModel;
import uk.co.ribot.androidboilerplate.data.model.News;
import uk.co.ribot.androidboilerplate.data.model.NewsModel;
import uk.co.ribot.androidboilerplate.data.model.RegistModel;
import uk.co.ribot.androidboilerplate.data.model.RegistRespons;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.data.model.StatusModel;
import uk.co.ribot.androidboilerplate.data.model.UserInfo;
import uk.co.ribot.androidboilerplate.data.model.inUtilization.Example;
import uk.co.ribot.androidboilerplate.data.model.inUtilization.Talk;
import uk.co.ribot.androidboilerplate.data.model.inUtilization.Weather;
import uk.co.ribot.androidboilerplate.data.remote.AddCommentServise;
import uk.co.ribot.androidboilerplate.data.remote.AvtorizationServise;
import uk.co.ribot.androidboilerplate.data.remote.CreadNewsServise;
import uk.co.ribot.androidboilerplate.data.remote.ExampleServise;
import uk.co.ribot.androidboilerplate.data.remote.ListCommentsServise;
import uk.co.ribot.androidboilerplate.data.remote.ListNewServise;
import uk.co.ribot.androidboilerplate.data.remote.MessageServise;
import uk.co.ribot.androidboilerplate.data.remote.NewsListServise;
import uk.co.ribot.androidboilerplate.data.remote.RegistServise;
import uk.co.ribot.androidboilerplate.data.remote.RibotsService;
import uk.co.ribot.androidboilerplate.data.remote.SendMessageServise;
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
    private final AvtorizationServise mAvtorizationServise;
    private final NewsListServise mNewsListServise;
    private final MessageServise mMessageServise;
    private final ListCommentsServise mListCommentsServise;
    private final AddCommentServise mAddCommentServise;
    private final SendMessageServise mSendMessageServise;


    @Inject
    public DataManager(RibotsService ribotsService, PreferencesHelper preferencesHelper,
                       DatabaseHelper databaseHelper, WeatherService weatherService, ExampleServise exampleServise, RegistServise registServise, CreadNewsServise creadNewsServise,
                       ListNewServise listNewServise, UserInfoServese userInfoServese, AvtorizationServise avtorizationServise, NewsListServise newsListServise, MessageServise messageServise,
                       ListCommentsServise listCommentsServise, AddCommentServise addCommentServise, SendMessageServise sendMessageServise) {
        mRibotsService = ribotsService;
        mPreferencesHelper = preferencesHelper;
        mDatabaseHelper = databaseHelper;
        mWeatherService = weatherService;
        mExampleServise = exampleServise;
        mRegistServise = registServise;
        mCreadNewsServise = creadNewsServise;
        mListNewServise = listNewServise;
        mUserInfoServese = userInfoServese;
        mAvtorizationServise = avtorizationServise;
        mNewsListServise = newsListServise;
        mMessageServise = messageServise;
        mListCommentsServise = listCommentsServise;
        mAddCommentServise = addCommentServise;
        mSendMessageServise = sendMessageServise;
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

    public void addID(String id) {
        mDatabaseHelper.setUSER(id);
    }

    public String getUserID() {
        return mDatabaseHelper.getUSER();
    }


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


    public Observable<UserInfo> getUserInfo(String id) {
        return
                mUserInfoServese.getUserInfo(id).map(new Func1<UserInfo, UserInfo>() {
                    @Override
                    public UserInfo call(UserInfo registModel) {
                        return registModel;
                    }
                });

    }

    public Observable<RegistRespons> getAvtoriz(String log, String pass) {
        return
                mAvtorizationServise.getAvtoriz(log, pass).map(new Func1<RegistModel, RegistRespons>() {
                    @Override
                    public RegistRespons call(RegistModel registModel) {
                        return registModel.getRespons();
                    }
                });
    }

    public Observable<List<News>> getNews(String limit, String offset) {
        return
                mListNewServise.getNews(limit, offset).map(new Func1<NewsModel, List<News>>() {
                    @Override
                    public List<News> call(NewsModel newsModel) {
                        return newsModel.getNews();
                    }
                });

    }

    public Observable<List<Message>> getMessage(String id) {
        return
                mMessageServise.getMessage(id).map(new Func1<MessageModel, List<Message>>() {
                    @Override
                    public List<Message> call(MessageModel messageModel) {
                        return messageModel.getMessages();
                    }
                });

    }

    public Observable<List<Comment>> getComments(String post_id) {
        return
                mListCommentsServise.getComments(post_id).map(new Func1<ComentsListModel, List<Comment>>() {
                    @Override
                    public List<Comment> call(ComentsListModel comentsListModel) {
                        return comentsListModel.getComments();
                    }
                });
    }

    public Observable<StatusModel> getStatus(String post_id, String user_id, String text, String date) {
        return
                mAddCommentServise.getStatus(post_id, user_id, text, date).map(new Func1<StatusModel, StatusModel>() {
                    @Override
                    public StatusModel call(StatusModel statusModel) {
                        return statusModel;
                    }
                });
    }

    public Observable<StatusModel> getStatusMessage(String from_id, String to_id, String text, String date) {
        return
                mSendMessageServise.getStatusMessage(from_id, to_id, text, date).map(new Func1<StatusModel, StatusModel>() {
                    @Override
                    public StatusModel call(StatusModel statusModel) {
                        return statusModel;
                    }
                });
    }
}
