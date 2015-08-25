package pl.krakowskascenamuzyczna.ksmcalendar;

import java.util.List;

import pl.krakowskascenamuzyczna.ksmcalendar.data.Concert;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;


/**
 * Created by Bartos on 2015-08-21.
 */
public class ApiClient {

    private static KsmInterface ksmInterface;

    public static KsmInterface getKsmApiClient() {
        if(ksmInterface == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()

                    .setEndpoint("http://krakowskascenamuzyczna.pl/api").build();

            ksmInterface = restAdapter.create(KsmInterface.class);
        }
        return ksmInterface;
    }


    public interface KsmInterface {
        @GET("/koncety/future")
                void getFuture(@Query("Typ")String "type", @Query("Link")String "url",
                @Query("Tytuł")String "title",@Query("Opis")String "content",@Query("Data")String "date",
                Callback<List<Concert>> callback);
    }
}

