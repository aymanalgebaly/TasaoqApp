package com.compubase.tasaoq.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.compubase.tasaoq.R;
import com.compubase.tasaoq.helper.TinyDB;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectedItemFragment extends Fragment {


    @BindView(R.id.img_item_selected)
    ImageView imgItemSelected;
    @BindView(R.id.img_star)
    ImageView imgStar;
    @BindView(R.id.txt_percent)
    TextView txtPercent;
    Unbinder unbinder;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.title)
    TextView title;

    private TinyDB tinyDB;

    public SelectedItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_selected_item, container, false);
        unbinder = ButterKnife.bind(this, view);

        tinyDB = new TinyDB(getActivity());
        int pic = tinyDB.getInt("pic");
        String name = tinyDB.getString("name");
        String pricee = tinyDB.getString("price");
        String rate = tinyDB.getString("rate");

        Glide.with(Objects.requireNonNull(getActivity())).load(pic).into(imgItemSelected);

        txtPercent.setText(rate);
        price.setText(pricee);
        title.setText(name);


        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
