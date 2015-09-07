package pl.krakowskascenamuzyczna.ksmcalendar;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
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

import pl.krakowskascenamuzyczna.ksmcalendar.data.Concert;
import pl.krakowskascenamuzyczna.ksmcalendar.data.Thumbnail;

/**
 * Created by Bartos on 2015-08-19.
 */
public class ConcertAdapter extends RecyclerView.Adapter<ConcertAdapter.MyViewHolder> {



    private LayoutInflater inflater;
    private Context context;
    private ImageLoader mImageLoader;
    private MySingleton mySingleton;

    private ArrayList<Concert> concertList = new ArrayList();
   // List<Concert> concertList = Collections.emptyList();

    public ConcertAdapter(Context context, List<Concert> concerts) {
        inflater = LayoutInflater.from(context);
       // this.concertList = concertList;
        //this.concertList = context;
        mySingleton = MySingleton.getInstance(context);
        mImageLoader = mySingleton.getImageLoader();

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

        Concert current = concertList.get(position);
        //holder.url.setImageUrl(current.getUrl(),mImageLoader);
        String url = current.getUrl();
            if(url != null) {
                mImageLoader.get(url, new ImageLoader.ImageListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }

                    @Override
                    public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                        holder.url.setImageBitmap(imageContainer.getBitmap());
                    }
                });
            }
    }



        @Override
        public int getItemCount () {
            return concertList.size();
        }
        class MyViewHolder extends RecyclerView.ViewHolder {

            private NetworkImageView url;

            public MyViewHolder(View itemView) {
                super(itemView);
                url = (NetworkImageView) itemView.findViewById(R.id.concerts_niv);
            }
        }

    }

