package com.semicolon.scientificresearch.Adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.semicolon.scientificresearch.Activities.TrainingActivity;
import com.semicolon.scientificresearch.Models.TrainingModel;
import com.semicolon.scientificresearch.R;
import com.semicolon.scientificresearch.Services.Tags;
import com.semicolon.scientificresearch.databinding.TrainingItemBinding;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class TrainningAdapter extends RecyclerView.Adapter<TrainningAdapter.myHolder> {
    private Context context;
    private List<TrainingModel> trainingModelList;
    private TrainingActivity activity;
    Target target ;

    public TrainningAdapter(Context context, List<TrainingModel> trainingModelList) {
        this.context = context;
        this.trainingModelList = trainingModelList;
        this.activity = (TrainingActivity) context;
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TrainingItemBinding trainingItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.training_item,parent,false);
        return new myHolder(trainingItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final myHolder holder, int position) {
        TrainingModel trainingModel = trainingModelList.get(position);
        holder.trainingItemBinding.setTranModel(trainingModel);

        target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                holder.trainingItemBinding.courseImage.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };

        Picasso.with(context).load(Uri.parse(Tags.ImgPath+trainingModel.getCourse_image())).into(target);
        holder.trainingItemBinding.reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.SetPos(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return trainingModelList.size();
    }

    public class myHolder extends RecyclerView.ViewHolder{
        TrainingItemBinding trainingItemBinding;
        public myHolder(TrainingItemBinding trainingItemBinding) {
            super(trainingItemBinding.getRoot());
            this.trainingItemBinding = trainingItemBinding;
        }
    }
}
