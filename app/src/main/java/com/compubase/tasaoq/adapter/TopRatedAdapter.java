package com.compubase.tasaoq.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.compubase.tasaoq.R;
import com.compubase.tasaoq.helper.TinyDB;
import com.compubase.tasaoq.model.TopRatedModel;
import com.compubase.tasaoq.ui.activities.HomeActivity;
import com.compubase.tasaoq.ui.fragments.SelectedItemFragment;

import java.util.ArrayList;
import java.util.List;

public class TopRatedAdapter extends RecyclerView.Adapter<TopRatedAdapter.ViewHolder> {
    private Context context;
    private List<TopRatedModel>topRatedModelList;

    public TopRatedAdapter(List<TopRatedModel> topRatedModelList) {
        this.topRatedModelList = topRatedModelList;
    }

    public TopRatedAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.top_rated_design, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final TopRatedModel topRatedModel = topRatedModelList.get(i);

        viewHolder.txt_discount.setText(topRatedModel.getTxt_sale());
        viewHolder.offer_sale.setText(topRatedModel.getTxt_sale_offer());
        viewHolder.offer.setText(topRatedModel.getTxt_offer());
        viewHolder.rate_num.setText(topRatedModel.getTxt_rate());
        viewHolder.title.setText(topRatedModel.getTitle());

        Glide.with(context).load(topRatedModel.getImg_heart()).into(viewHolder.heart);
        Glide.with(context).load(topRatedModel.getImg_item()).into(viewHolder.img_item);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeActivity homeActivity = (HomeActivity) context;

                SelectedItemFragment selectedItemFragment = new SelectedItemFragment();
                homeActivity.displaySelectedFragmentWithBack(selectedItemFragment);

                TinyDB tinyDB = new TinyDB(context);
                tinyDB.putInt("pic",topRatedModel.getImg_item());
                tinyDB.putString("name",topRatedModel.getTitle());
                tinyDB.putString("rate",topRatedModel.getTxt_rate());
                tinyDB.putString("price",topRatedModel.getTxt_offer());
            }
        });
    }

    @Override
    public int getItemCount() {
        return topRatedModelList != null ? topRatedModelList.size():0;
    }

    public void setAdapter(ArrayList<TopRatedModel> topRatedModels) {
        this.topRatedModelList = topRatedModels;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,rate_num,offer,offer_sale,txt_discount;
        ImageView heart,img_item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.txt_name_of_item);
            rate_num = itemView.findViewById(R.id.txt_percent);
            offer = itemView.findViewById(R.id.txt_num);
            offer_sale = itemView.findViewById(R.id.txt_num_sale);
            txt_discount = itemView.findViewById(R.id.txt_percent_sale);

            heart = itemView.findViewById(R.id.img_heart);
            img_item = itemView.findViewById(R.id.img_item);


        }
    }
}
