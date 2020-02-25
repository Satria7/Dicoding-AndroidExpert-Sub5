package com.putrasamawa.dicodingmade1.adapter;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.putrasamawa.dicodingmade1.DetailActivity;
import com.putrasamawa.dicodingmade1.R;
import com.putrasamawa.dicodingmade1.model.Item;
import com.putrasamawa.dicodingmade1.model.ItemTV;

import java.util.List;

/* Copyright Satria Junanda */

public class AdapterTV extends RecyclerView.Adapter<BaseViewHolder> {
    private static final String TAG = "AdapterTV";
    private Context context;
    private List<ItemTV> mTVList;

    public AdapterTV(List<ItemTV> tvList) {
        mTVList = tvList;
    }
    public AdapterTV(Context context) {
        this.context = context;
    }

    public List<ItemTV> getmTVList() {
        return mTVList;
    }

    public void setmTVList(List<ItemTV> mTVList) {
        this.mTVList = mTVList;
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
        if (mTVList != null && mTVList.size() > 0) {
            return mTVList.size();
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

        public void onBind(int position) {
            super.onBind(position);

            final ItemTV mTV = mTVList.get(position);

            if (mTV.getPoster_path() != null) {
                Glide.with(itemView.getContext())
                        .load("https://image.tmdb.org/t/p/w342/" + mTV.getPoster_path())
                        .into(ivThumbnail);
            }
            try {
                if (mTV.getPoster_path() != null) {
                    Glide.with(itemView.getContext())
                            .load("https://image.tmdb.org/t/p/w342/" + mTV.getPoster_path())
                            .into(ivThumbnail);
                }

                if (mTV.getOriginal_title() != null) {
                    tvTitle.setText(mTV.getOriginal_title());
                }

                if (mTV.getOverview() != null) {
                    tvDescription.setText(mTV.getOverview());
                }
            } catch (Exception e) {

            }


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Item model = new Item(mTV.getOriginal_title(),mTV.getOverview(),mTV.getPoster_path(),String.valueOf(mTV.getId()),"tv");

                    int pos = getAdapterPosition();

                    if(mTV.getPoster_path()==null){
                        Toast.makeText(context,R.string.rusak,Toast.LENGTH_SHORT).show();
                    }else {
                        if (pos != RecyclerView.NO_POSITION) {
                            Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                            intent.putExtra("Model", model);
                            intent.putExtra("key", "tv");
                            itemView.getContext().startActivity(intent);
                        }
                    }
                }
            });
        }
    }

}

/* Copyright Satria Junanda */