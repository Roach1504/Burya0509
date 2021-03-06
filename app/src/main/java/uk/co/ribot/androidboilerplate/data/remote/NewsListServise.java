package uk.co.ribot.androidboilerplate.data.remote;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import uk.co.ribot.androidboilerplate.data.model.NewsModel;

public interface NewsListServise {
    String ENDPOINT = "http://9834436605.myjino.ru";

    @POST("/api/get-posts")
    Observable<NewsModel> getNews(@Query("limit") String limit, @Query("offset") String offset);


    /******** Helper class that sets up a new services *******/
    class Creator {
        public static NewsListServise newsListServise() {
            Gson gson = new GsonBuilder()
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(NewsListServise.class);
        }
    }
}
