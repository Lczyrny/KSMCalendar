package pl.krakowskascenamuzyczna.ksmcalendar;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.krakowskascenamuzyczna.ksmcalendar.Activities.CalendarActivity;
import pl.krakowskascenamuzyczna.ksmcalendar.Activities.DisplayConcertActivity;
import pl.krakowskascenamuzyczna.ksmcalendar.data.Concert;
import pl.krakowskascenamuzyczna.ksmcalendar.data.Thumbnail;

/**
 * Created by Bartos on 2015-08-19.
 */
public class ConcertAdapter extends RecyclerView.Adapter<ConcertAdapter.MyViewHolder> {



    private LayoutInflater inflater;
    private Context context;

    NetworkImageView mNetworkImageView;

    private MySingleton mySingleton;

    private List<Concert> concertList = new ArrayList();
   // List<Concert> concertList = Collections.emptyList();

    public ConcertAdapter(Context context, List<Concert> concerts) {
        this.inflater = LayoutInflater.from(context);
       // this.concertList = concertList;
        //this.concertList = context;
        this.concertList = concerts;
        this.context=context;

    }
    public void setListConcert(ArrayList<Concert> concertList) {
        this.concertList=concertList;
        notifyItemRangeChanged(0,concertList.size());
    }


        @Override
        public  MyViewHolder onCreateViewHolder (ViewGroup parent,int viewType){

            View view = inflater.inflate(R.layout.concert_item, parent, false);
            MyViewHolder holder = new MyViewHolder(view);


            return holder;
        }

        @Override
        public void onBindViewHolder (final MyViewHolder holder,int position){

            final float screenWidthPx = holder.itemView.getResources().getDisplayMetrics().widthPixels;


        Concert current = concertList.get(position);
            Log.d("mLog",current.getUrl());
            holder.image.setImageUrl(current.getUrl(), MySingleton.getInstance().getImageLoader());
            holder.image.getLayoutParams().height = (int) (screenWidthPx * 0.75);


    }



        @Override
        public int getItemCount () {
            return concertList.size();
        }

    public void  showDisplay(int position){
        Intent intent = new Intent(context, DisplayConcertActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("content", concertList.get(position).getContent());
       // intent.putExtra("title" , concertList.get(position).getTitle());
        //intent.putExtra("date", concertList.get(position).getDate());
        intent.putExtra("url", concertList.get(position).getUrl());

       // intent.ParcelableArrayListExtra("list", concertList);
        context.startActivity(intent);
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

            private NetworkImageView image;
            public MyViewHolder(View itemView) {
                super(itemView);
                image = (NetworkImageView) itemView.findViewById(R.id.concerts_niv);
                image.setOnClickListener(this);
            }
                    @Override
                    public void onClick(View v) {showDisplay(getAdapterPosition());

                    }
                }
            }


