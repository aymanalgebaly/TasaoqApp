package com.compubase.tasaoq.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.compubase.tasaoq.R;
import com.compubase.tasaoq.adapter.CategoriesAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment {


    @BindView(R.id.rcv_fav)
    RecyclerView rcvFav;
    Unbinder unbinder;

    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

//    private void setupRecycler() {
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
//        rcvFav.setLayoutManager(linearLayoutManager);
//        categoriesAdapter = new CategoriesAdapter(getActivity());
//        rcvFav.setAdapter(categoriesAdapter);
//        categoriesAdapter.notifyDataSetChanged();
//
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
