package uk.co.ribot.androidboilerplate.data.remote;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import uk.co.ribot.androidboilerplate.data.model.AddViewNewModel;

public interface AddViewNew {
    String ENDPOINT = "http://9834436605.myjino.ru";
    @POST("/api/set-mark")
    Observable<AddViewNewModel> getAddViewNew(@Query("id") String id, @Query("post_id") String post_id);

    /******** Helper class that sets up a new services *******/
    class Creator {
        public static AddViewNew newAddViewNew() {
            Gson gson = new GsonBuilder()
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(AddViewNew.class);
        }
    }
}
