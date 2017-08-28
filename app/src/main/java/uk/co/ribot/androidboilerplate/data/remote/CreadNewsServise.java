package uk.co.ribot.androidboilerplate.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import uk.co.ribot.androidboilerplate.data.model.CreadNewModel;
import uk.co.ribot.androidboilerplate.data.model.RegistModel;


public interface CreadNewsServise {
    String ENDPOINT = "http://9834436605.myjino.ru";
    @POST("/api/add-post")
    Observable<CreadNewModel> getCreadNew(@Query("title") String title, @Query("short") String shorts, @Query("text") String text
            , @Query("date") String date
            , @Query("id") String id);
            //, @Query("tel") String tel);
    // TODO: 28.08.2017 Закрепить загрузку файлов


    /******** Helper class that sets up a new services *******/
    class Creator {
        public static CreadNewsServise newCreadNewsServise() {
            Gson gson = new GsonBuilder()
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(CreadNewsServise.class);
        }
    }
}
