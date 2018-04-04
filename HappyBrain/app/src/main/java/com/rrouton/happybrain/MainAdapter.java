package com.rrouton.happybrain;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rrouton.happybrain.models.flickr.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Photo> photoList = new ArrayList();
    private ViewGroup parent;
    private int width;

    public MainAdapter(int width) {
        this.width = width;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        ImageView imageView;

        public ViewHolder(ConstraintLayout layout) {
            super(layout);
            this.layout = layout;
            this.imageView = layout.findViewById(R.id.photo);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        ConstraintLayout layout = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_viewholder, null);

        ViewHolder viewHolder = new ViewHolder(layout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(parent.getContext())
                .load(photoList.get(position).getUrl())
                .resize(width, 0)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }
}
