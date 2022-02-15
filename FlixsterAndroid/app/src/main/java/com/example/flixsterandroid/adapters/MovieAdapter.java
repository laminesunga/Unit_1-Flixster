package com.example.flixsterandroid.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Parcel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixsterandroid.MainActivity;
import com.example.flixsterandroid.R;
import com.example.flixsterandroid.detailActivity;
import com.example.flixsterandroid.models.Movie;

import org.parceler.Parcels;

import java.util.List;

public class MovieAdapter  extends  RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies){
        this.context = context;
        this.movies = movies;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onBindViewHolder");

        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie,parent, false);
        return new ViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHoldr" + position );

        Movie movie = movies.get(position);
        
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

    TextView tvTitle;
    TextView tvOverview;
    ImageView ivPoster;
    RelativeLayout container;

       public ViewHolder (@NonNull View itemView){

        super(itemView);
        tvTitle = itemView.findViewById(R.id.tvTitle);
        tvOverview = itemView.findViewById(R.id.tvOverview);
        ivPoster = itemView.findViewById(R.id.ivPoster);
        container = itemView.findViewById(R.id.container);


       }

       public void bind(Movie movie) {

           tvTitle.setText(movie.getTitle());
           tvOverview.setText(movie.getOverview());
           String imageUrl;

           if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
               imageUrl = movie.getBackdropPath();
           }
           else {
               imageUrl = movie.getPosterPath();
           }
           Glide.with(context).load(imageUrl).into(ivPoster);

           // 1 click listener on each movie row

           container.setOnClickListener(new View.OnClickListener(){
               @Override
               public void onClick(View v) {

                   // 2 navigate to a new activity page
                   Intent i = new Intent(context, detailActivity.class);

                   i.putExtra("movie", Parcels.wrap(movie));

                   context.startActivity(i);
               }


           });
       }
    }
}
