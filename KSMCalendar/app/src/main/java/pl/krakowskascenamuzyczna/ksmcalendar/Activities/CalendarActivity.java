package pl.krakowskascenamuzyczna.ksmcalendar.Activities;


import android.app.Activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import pl.krakowskascenamuzyczna.ksmcalendar.ApiClient;
import pl.krakowskascenamuzyczna.ksmcalendar.ConcertAdapter;
import pl.krakowskascenamuzyczna.ksmcalendar.R;
import pl.krakowskascenamuzyczna.ksmcalendar.data.Concert;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class CalendarActivity extends Activity {


    private List<Concert> concerts;
    private RecyclerView recyclerView;
    private ConcertAdapter recyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        recyclerView = new ConcertAdapter(CalendarActivity.this, )


    }
    public void futureConcerts() {
        ApiClient.getKsmApiClient().getFuture(new Callback<List<Concert>>() {
            @Override
            public void success(List<Concert> concerts, Response response) {
                setConcerts(concerts);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setConcerts(List<Concert> concerts) {
        ConcertAdapter = new recyclerViewAdapter(CalendarActivity.this(), concerts);
        recyclerView.setAdapter(ConcertAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CalendarActivity.this()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calendar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
