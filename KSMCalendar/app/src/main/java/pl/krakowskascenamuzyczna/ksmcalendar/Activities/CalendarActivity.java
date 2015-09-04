package pl.krakowskascenamuzyczna.ksmcalendar.Activities;


import android.app.Activity;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;
import java.util.zip.Inflater;

import pl.krakowskascenamuzyczna.ksmcalendar.ApiClient;
import pl.krakowskascenamuzyczna.ksmcalendar.ConcertAdapter;
import pl.krakowskascenamuzyczna.ksmcalendar.MySingleton;
import pl.krakowskascenamuzyczna.ksmcalendar.R;
import pl.krakowskascenamuzyczna.ksmcalendar.data.Category;
import pl.krakowskascenamuzyczna.ksmcalendar.data.Concert;
import pl.krakowskascenamuzyczna.ksmcalendar.data.Thumbnail;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class CalendarActivity extends Activity implements AdapterView.OnItemClickListener {

    private List<Thumbnail> concerts;
    private RecyclerView recyclerView;
    private ConcertAdapter adapter;



    ImageLoader mImageLoader;
    NetworkImageView mNetworkImageView;
    //MyArrayAdapter imageArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        mImageLoader = MySingleton.getInstance(this).getImageLoader();
        recyclerView.setAdapter(adapter);


          }

        public void futureConcerts() {
        ApiClient.getKsmApiClient().getFuture("type","url","title","content","date", new Callback<List<Concert>>() {
            @Override
            public void success(List<Concert> concerts, Response response) {
                setConcerts(concerts);
            }


            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                setConcerts(null);
            }
        });
    }

    public void setConcerts(List<Concert> concerts) {
       adapter = new ConcertAdapter(this, concerts);
       recyclerView.setAdapter(adapter);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();


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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(CalendarActivity.this , DisplayConcertActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);

    }
}
