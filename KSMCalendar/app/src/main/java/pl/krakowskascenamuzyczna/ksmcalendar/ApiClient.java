package pl.krakowskascenamuzyczna.ksmcalendar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import pl.krakowskascenamuzyczna.ksmcalendar.data.Concert;
import pl.krakowskascenamuzyczna.ksmcalendar.data.Thumbnail;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;
import retrofit.RequestInterceptor;


/**
 * Created by Bartos on 2015-08-21.
 */
public class ApiClient {

    private static KsmInterface ksmInterface;
    private static RequestInterceptor requestInterceptor = new HeaderHelpingRequestInterceptor();


    public static KsmInterface getKsmApiClient() {
        if(ksmInterface == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://krakowskascenamuzyczna.pl/api")
                    .setRequestInterceptor(requestInterceptor)
                    .build();

            ksmInterface = restAdapter.create(KsmInterface.class);
        }
        return ksmInterface;
    }
    public interface KsmInterface {
        @GET("/koncerty/future")
                void getFuture(@Query("Typ")String type, @Query("Link")String url,
                               @Query("Tytuł")String title,@Query("Opis")String content,@Query("Data")String date,
                               Callback<List<Concert>> callback);
    }
}

