package com.compubase.tasaoq.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.compubase.tasaoq.R;
import com.compubase.tasaoq.adapter.CartAdapter;
import com.compubase.tasaoq.model.ProductsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmFragment extends Fragment {


    @BindView(R.id.rcv_confirm)
    RecyclerView rcvConfirm;
    @BindView(R.id.txt_total_value)
    TextView txtTotalValue;
    @BindView(R.id.ed_address)
    EditText edAddress;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    Unbinder unbinder;
    private Realm realm;
    private List<ProductsModel> productsModelList = new ArrayList<>();
    private CartAdapter cartAdapter;

    public ConfirmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirm, container, false);
        unbinder = ButterKnife.bind(this, view);
        Realm.init(Objects.requireNonNull(getContext()));
        realm = Realm.getDefaultInstance();

        showData();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    private void showData() {

        productsModelList.clear();

        RealmResults<ProductsModel> all = realm.where(ProductsModel.class).findAll();
        for (int i = 0; i <all.size() ; i++) {

            ProductsModel productsModel = new ProductsModel();

            productsModel.setTitle(Objects.requireNonNull(all.get(i)).getTitle());
            productsModel.setDes(Objects.requireNonNull(all.get(i)).getDes());
            productsModel.setImg1(Objects.requireNonNull(all.get(i)).getImg1());
            productsModel.setPrice(Objects.requireNonNull(all.get(i)).getPrice());

            productsModelList.add(productsModel);


        }
//        realm.commitTransaction();
        cartAdapter = new CartAdapter(productsModelList);
        rcvConfirm.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

    }


    @OnClick(R.id.btn_confirm)
    public void onViewClicked() {
    }
}
