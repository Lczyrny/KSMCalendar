package pl.krakowskascenamuzyczna.ksmcalendar.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pl.krakowskascenamuzyczna.ksmcalendar.MySingleton;
import pl.krakowskascenamuzyczna.ksmcalendar.R;
import pl.krakowskascenamuzyczna.ksmcalendar.data.Concert;


public class BubbleAdapter extends RecyclerView.Adapter<BubbleAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    NetworkImageView mNetworkImageView;
    private MySingleton mySingleton;
    private List<Concert> concertList = new ArrayList();

    public BubbleAdapter(Context context, List<Concert> concerts) {
        this.inflater = LayoutInflater.from(context);
        this.concertList = concerts;
        this.context = context;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.bubble_concert, parent, false);
            MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(BubbleAdapter.MyViewHolder holder, int position) {

        Concert current = concertList.get(position);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date concertDate = new Date();
        try {
            concertDate = format.parse(current.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateTime dt = new DateTime();
        DateTime currentDate = dt.withZone(DateTimeZone.forID("Europe/Warsaw"));
        int days = Days.daysBetween(new DateTime(currentDate), new DateTime(concertDate)).getDays();
        holder.fav.setText(String.valueOf(days));

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView fav;

        public MyViewHolder(View itemView) {
            super(itemView);
            fav = (TextView) itemView.findViewById(R.id.bubble_tv);
        }
    }
}
