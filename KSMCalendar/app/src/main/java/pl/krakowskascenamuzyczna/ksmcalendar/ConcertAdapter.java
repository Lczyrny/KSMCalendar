package pl.krakowskascenamuzyczna.ksmcalendar;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Collections;
import java.util.List;

import pl.krakowskascenamuzyczna.ksmcalendar.data.Concert;

/**
 * Created by Bartos on 2015-08-19.
 */
public class ConcertAdapter extends RecyclerView.Adapter<ConcertAdapter, ConcertAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private Context context;

    List<Concert> data = Collections.emptyList();
    public ConcertAdapter(Context context, List<Concert> concertList ) {
       inflater = LayoutInflater.from(context);
       this.data = concertList;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.concert, parent,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Concert current = data.get(position);
        holder.concert.setImageURI(Uri.parse(current.getCategories().get(0).getAttachement().getImages().getThumbnail().getUrl()));

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView concert;
        public MyViewHolder(View itemView) {
            super(itemView);
            concert = (ImageView) itemView.findViewById(R.id.concert_iv);
        }
    }
}
