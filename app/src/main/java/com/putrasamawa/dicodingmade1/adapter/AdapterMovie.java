package com.putrasamawa.dicodingmade1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.putrasamawa.dicodingmade1.DetailActivity;
import com.putrasamawa.dicodingmade1.R;
import com.putrasamawa.dicodingmade1.model.Item;
import com.putrasamawa.dicodingmade1.model.ItemMovie;
import java.util.List;

/* Copyright Satria Junanda */

public class AdapterMovie extends RecyclerView.Adapter<BaseViewHolder> {
    private static final String TAG = "AdapterMovie";
    private List<ItemMovie> mMovieList;
    private Context context;

    public AdapterMovie(List<ItemMovie> movieList) {
        mMovieList = movieList;
    }
    public AdapterMovie(Context context) {
        this.context = context;
    }

    public List<ItemMovie> getmMovieList() {
        return mMovieList;
    }

    public void setmMovieList(List<ItemMovie> mMovieList) {
        this.mMovieList = mMovieList;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item2, parent, false));


    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        if (mMovieList != null && mMovieList.size() > 0) {
            return mMovieList.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends BaseViewHolder {

        ImageView ivThumbnail;
        TextView tvTitle;
        TextView tvDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            ivThumbnail = itemView.findViewById(R.id.imageView);
            tvTitle = itemView.findViewById(R.id.textView);
            tvDescription = itemView.findViewById(R.id.textView2);


        }

        protected void clear() {
            ivThumbnail.setImageDrawable(null);
            tvTitle.setText("");

        }

        public void onBind(final int position) {
            super.onBind(position);

            final ItemMovie mMovie = mMovieList.get(position);

//            if(mMovie.getPoster_path()==null){
//                notifyItemRemoved(position);
//                notifyAll();
//
//            }

            if (mMovie.getPoster_path() != null) {
                Glide.with(itemView.getContext())
                        .load("https://image.tmdb.org/t/p/w342/" + mMovie.getPoster_path())
                        .into(ivThumbnail);
            }

            if (mMovie.getOriginal_title() != null) {
                tvTitle.setText(mMovie.getOriginal_title());
            }

            if (mMovie.getOverview() != null) {
                tvDescription.setText(mMovie.getOverview());
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Item model = new Item(mMovie.getOriginal_title(),mMovie.getOverview(),mMovie.getPoster_path(),mMovie.getId(),"movie");

                    int pos = getAdapterPosition();

                    if(mMovie.getPoster_path()==null){
                        Toast.makeText(context,R.string.rusak,Toast.LENGTH_SHORT).show();
                    }else {
                        if(pos != RecyclerView.NO_POSITION) {
                            Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                            intent.putExtra("Model", model);
                            intent.putExtra("key", "movie");
                            itemView.getContext().startActivity(intent);
                        }
                    }
                }
            });

        }

    }

}

/* Copyright Satria Junanda */