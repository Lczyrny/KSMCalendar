package pl.krakowskascenamuzyczna.ksmcalendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;


public class EnterActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);



    }

    public void clicked(View view) {
        startActivity(new Intent(getApplicationContext(),CalendarActivity.class));
    }
}