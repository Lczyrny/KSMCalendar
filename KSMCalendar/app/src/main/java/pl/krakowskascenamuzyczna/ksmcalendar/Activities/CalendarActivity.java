package pl.krakowskascenamuzyczna.ksmcalendar.Activities;


import android.app.Activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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


public class CalendarActivity extends ListActivity implements AdapterView.OnItemClickListener {

    private List<Concert> concerts;
    private RecyclerView recyclerView;
    private ConcertAdapter recyclerViewAdapter;



    ImageLoader mImageLoader;
    NetworkImageView mNetworkImageView;
    //MyArrayAdapter imageArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        mImageLoader = MySingleton.getInstance(this).getImageLoader();
        ConcertAdapter = new recyclerViewAdapter(this, android.R.layout.simple_list_item_1) {

          public View getView(int position, View convertView, ViewGroup parent) {

              View itemView = convertView;
              if (itemView == null) {
                  itemView = getLayoutInflater().inflate(R.layout.concert_item, parent, false);
              }
            mNetworkImageView = (NetworkImageView) itemView.findViewById(R.id.concerts_rv);
              mNetworkImageView.setDefaultImageResId(R.drawable.no_image);
              mNetworkImageView.setErrorImageResId(R.drawable.error);
              mNetworkImageView.setAdjustViewBounds(true);
              mNetworkImageView.setImageUrl(, mImageLoader);
              return itemView;


          }
        };
        setListAdapter(ConcertAdapter);
        ListView listView = getListView();
        listView.setOnClickListener((View.OnClickListener) this);

}

        public void futureConcerts() {
        ApiClient.getKsmApiClient().getFuture(new Callback<List<Concert>>(){
@Override
        public void success(List<Concert>concerts,Response response){
        setConcerts(concerts);
        }


            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                setConcerts(null);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(CalendarActivity.this , DisplayConcertActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);

    }
}
