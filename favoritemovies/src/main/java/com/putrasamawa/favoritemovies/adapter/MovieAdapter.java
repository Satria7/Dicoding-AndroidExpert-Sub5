package com.putrasamawa.favoritemovies.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.putrasamawa.favoritemovies.DetailActivity;
import com.putrasamawa.favoritemovies.R;
import com.putrasamawa.favoritemovies.model.Item;
import com.putrasamawa.favoritemovies.model.ItemMovie;
import com.putrasamawa.favoritemovies.model.MovieFavorite;
import com.putrasamawa.favoritemovies.rest.UtilsConstant;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/* Copyright Satria Junanda */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ItemMovieViewHolder>{

    private List<ItemMovie> movieResultList = new ArrayList<>();
    private Context context;

    public MovieAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ItemMovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemMovieViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_list,
                        parent, false)
        );
    }

    public void setMovieResult(List<ItemMovie> movieResult){
        this.movieResultList = movieResult;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ItemMovieViewHolder holder, int position) {
        holder.bindView(movieResultList.get(position));
    }

    @Override
    public int getItemCount() {
        return movieResultList.size();
    }

    //View Holder
    class ItemMovieViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.imageView)
        ImageView item_poster;
        @BindView(R.id.textView)
        TextView item_title;

        ItemMovieViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
        public void bindView(final ItemMovie movieResult){
            item_title.setText(movieResult.getOriginal_title());
            Glide.with(itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w342/"+movieResult.getPoster_path())
                    .into(item_poster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Item model = new Item(movieResult.getOriginal_title(),movieResult.getOverview(),movieResult.getPoster_path(),movieResult.getId(),"");
                    Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                    intent.putExtra("Model", model);
                    itemView.getContext().startActivity(intent);
                }
            });


        }

    }
}

/* Copyright Satria Junanda */