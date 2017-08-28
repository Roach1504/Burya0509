package uk.co.ribot.androidboilerplate.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import uk.co.ribot.androidboilerplate.data.model.inUtilization.Example;



public interface ExampleServise {
    String ENDPOINT = "https://api.ted.com";
    @GET("/v1/talks.json?api-key=umdz5qctsk4g9nmqnp5btsmf&limit=1")


    Observable<Example> getExample(@Query("offset") String offset);

    /******** Helper class that sets up a new services *******/
    class Creator {
        public static ExampleServise newExampleService() {
            Gson gson = new GsonBuilder()
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();


            return retrofit.create(ExampleServise.class);
        }
    }
}
