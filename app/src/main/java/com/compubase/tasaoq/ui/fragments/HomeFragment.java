package com.compubase.tasaoq.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.compubase.tasaoq.R;
import com.compubase.tasaoq.adapter.CategoriesAdapter;
import com.compubase.tasaoq.adapter.TopRatedAdapter;
import com.compubase.tasaoq.model.CategoriesModel;
import com.compubase.tasaoq.model.TopRatedModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    @BindView(R.id.imageSlider_flip)
    ViewFlipper imageSliderFlip;
    @BindView(R.id.rcv_categories)
    RecyclerView rcvCategories;
    @BindView(R.id.rcv_top_rated)
    RecyclerView rcvTopRated;
    Unbinder unbinder;

    private int [] images;
    private List<CategoriesModel>categoriesModelList;
    private CategoriesAdapter categoriesAdapter;
    private TopRatedAdapter topRatedAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        images = new int[]{R.drawable.anti_man, R.drawable.avengers, R.drawable.titanic};

        for (int image : images) {

            flipperImage(image);
        }

        setupRecycler();
        setupRecyclerTopRated();
        fetchData();
        fetchDataTopRated();
        return view;
    }

    public void fetchData() {

        ArrayList<CategoriesModel> categoriesModels = new ArrayList<>();

        int[] image = new int[]{R.drawable.titanic,R.drawable.avengers,R.drawable.anti_man,R.drawable.titanic,
        R.drawable.avengers,R.drawable.anti_man};

        String[]title = new String[]{"aaa","bbb","ccc","ddd","eee","fff","ggg"};

        for (int i = 0; i <image.length ; i++) {

            categoriesModels.add(new CategoriesModel(title[i],image[i]));
        }
        categoriesAdapter.setAdapter(categoriesModels);
        categoriesAdapter.notifyDataSetChanged();
    }

    private void setupRecycler() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        rcvCategories.setLayoutManager(linearLayoutManager);
        categoriesAdapter = new CategoriesAdapter(getActivity());
        rcvCategories.setAdapter(categoriesAdapter);
        categoriesAdapter.notifyDataSetChanged();

    }

    private void fetchDataTopRated() {

        ArrayList<TopRatedModel> topRatedModels = new ArrayList<>();

        int[] image1 = new int[]{R.drawable.titanic,R.drawable.avengers,R.drawable.anti_man,R.drawable.titanic,
                R.drawable.avengers,R.drawable.anti_man};

        int[] imageHeart = new int[]{R.drawable.heart,R.drawable.heart,R.drawable.heart,R.drawable.heart,
                R.drawable.heart,R.drawable.heart};

        String[]title = new String[]{"aaa","bbb","ccc","ddd","eee","fff","ggg"};
        String[]offer = new String[]{"200","230","150","260","180","140","300"};
        String[]offer_sale = new String[]{"150","130","100","180","90","110","170"};
        String[]percent = new String[]{"-10%","-50%","-20%","-30%","-40%","-90%","-70%"};
        String[]rate = new String[]{"4.0","5.0","4.5","3.0","3.5","4.5","2.5"};

        for (int i = 0; i <image1.length ; i++) {

            topRatedModels.add(new TopRatedModel(image1[i],imageHeart[i],percent[i],offer_sale[i],offer[i],
                    rate[i],title[i]));
        }
        topRatedAdapter.setAdapter(topRatedModels);
        topRatedAdapter.notifyDataSetChanged();
    }

    private void setupRecyclerTopRated() {

        GridLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(),2);
        rcvTopRated.setLayoutManager(linearLayoutManager);
        topRatedAdapter = new TopRatedAdapter(getActivity());
        rcvTopRated.setAdapter(topRatedAdapter);
        topRatedAdapter.notifyDataSetChanged();

    }

    private void flipperImage(int image) {

        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);

        imageSliderFlip.addView(imageView);
        imageSliderFlip.setFlipInterval(5000);
        imageSliderFlip.setAutoStart(true);

        imageSliderFlip.setInAnimation(getActivity(),android.R.anim.slide_in_left);
        imageSliderFlip.setOutAnimation(getActivity(),android.R.anim.slide_out_right);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
