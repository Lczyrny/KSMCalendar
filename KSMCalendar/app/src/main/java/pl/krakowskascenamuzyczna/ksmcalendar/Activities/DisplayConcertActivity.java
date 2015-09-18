package pl.krakowskascenamuzyczna.ksmcalendar.Activities;

import android.app.Activity;

import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.squareup.picasso.Picasso;

import pl.krakowskascenamuzyczna.ksmcalendar.MySingleton;
import pl.krakowskascenamuzyczna.ksmcalendar.R;

public class DisplayConcertActivity extends Activity {

    private TextView contentConcert;
    private ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_concert);
        contentConcert = (TextView) findViewById(R.id.content_tv);
        image = (ImageView) findViewById(R.id.image_iv);
        Bundle extras = getIntent().getExtras();
        int currentPosition;
        String content;
        String url;
        if (extras != null) {
            currentPosition = extras.getInt("position");
            content = extras.getString("content");
            url = extras.getString("url");
            if (content != null) {
                contentConcert.setText(content);
                contentConcert.setText(Html.fromHtml(content));
            }
            if (url !=null) {
                Picasso.with(this).load(url)
                .into(image);
            }
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_concert, menu);
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
