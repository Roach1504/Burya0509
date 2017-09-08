package uk.co.ribot.androidboilerplate.data.remote;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import uk.co.ribot.androidboilerplate.data.model.RegistModel;

public interface AvtorizationServise {
    String ENDPOINT = "http://9834436605.myjino.ru";

    @POST("/api/sign-in")
    Observable<RegistModel> getAvtoriz(@Query("login") String login, @Query("pass") String pass);


    /******** Helper class that sets up a new services *******/
    class Creator {
        public static AvtorizationServise avtorizationServise() {
            Gson gson = new GsonBuilder()
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(AvtorizationServise.class);
        }
    }
}

