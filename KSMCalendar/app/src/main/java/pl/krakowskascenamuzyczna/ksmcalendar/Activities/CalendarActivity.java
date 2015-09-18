package pl.krakowskascenamuzyczna.ksmcalendar.Activities;


import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pl.krakowskascenamuzyczna.ksmcalendar.ApiClient;
import pl.krakowskascenamuzyczna.ksmcalendar.ConcertAdapter;
import pl.krakowskascenamuzyczna.ksmcalendar.MySingleton;
import pl.krakowskascenamuzyczna.ksmcalendar.R;
import pl.krakowskascenamuzyczna.ksmcalendar.data.Concert;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class CalendarActivity extends Activity implements AdapterView.OnItemClickListener {

    private List<Concert> concertList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ConcertAdapter adapter;







    NetworkImageView mNetworkImageView;
    //MyArrayAdapter imageArrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        futureConcerts();
          }

    @Override
    protected void onStart() {
        super.onStart();


    }

   /* public void futureConcerts() {
        ApiClient.getKsmApiClient().getFuture("type", "url", "title", "content", "date", new Callback<List<Concert>>() {
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
    }*/
    public void futureConcerts() {
        StringRequest request = new StringRequest(Request.Method.GET, "http://krakowskascenamuzyczna.pl/api/koncerty/future/",
                new com.android.volley.Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject responseJSONObject = new JSONObject(response);
                            String arrayString = responseJSONObject.getJSONArray("posts").toString();
                            JSONArray responseJSONArray = new JSONArray(arrayString);
                            Concert tempConcert;
                            for(int i = 0 ;  i <responseJSONArray.length(); i++){
                                tempConcert = new Concert(responseJSONArray.getJSONObject(i).getJSONArray("attachments").getJSONObject(0).getJSONObject("images").getJSONObject("full").getString("url"),
                                        responseJSONArray.getJSONObject(i).getString("content"));
                                concertList.add(tempConcert);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("mLog", getClass().getName() + ": "+ String.valueOf(concertList.size()));
                        adapter = new ConcertAdapter(CalendarActivity.this, concertList);
                        recyclerView = (RecyclerView) findViewById(R.id.concerts_rv);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Log.d("mLog", "[LoginActivity] ProcessLogin.Volley error: " + error.getMessage());
                        VolleyLog.d("mLog", "[LoginActivity] ProcessLogin.Error: " + error.getMessage());
                    }
                }
        );
        MySingleton.getInstance().getRequestQueue().add(request);
    }

    public void setConcerts(List<Concert> concerts) {

        adapter = new ConcertAdapter(CalendarActivity.this, concertList);
        recyclerView = (RecyclerView) findViewById(R.id.concerts_rv);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


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
