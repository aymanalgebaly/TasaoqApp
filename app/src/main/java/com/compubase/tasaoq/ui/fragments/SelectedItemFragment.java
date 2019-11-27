package com.compubase.tasaoq.ui.fragments;


import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.compubase.tasaoq.R;
import com.compubase.tasaoq.helper.TinyDB;
import com.compubase.tasaoq.model.ProductsModel;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.realm.Realm;

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
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.txt_price)
    TextView txtPrice;
    @BindView(R.id.txt_des)
    TextView txtDes;
    @BindView(R.id.btn_fav)
    Button btnFav;
    @BindView(R.id.btn_add_cart)
    Button btnAddCart;

    private TinyDB tinyDB;
    private String pic, name, pricee, rate, des, dis, pic2, pic3;

    private int check = 0;
    private Realm realm;
    private String id;


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
        pic = tinyDB.getString("pic");
        name = tinyDB.getString("name");
        pricee = tinyDB.getString("price");
        rate = tinyDB.getString("rate");
        des = tinyDB.getString("des");
        pic2 = tinyDB.getString("pic2");
        pic3 = tinyDB.getString("pic3");
        dis = tinyDB.getString("dis");
        id = tinyDB.getString("id");

        Realm.init(Objects.requireNonNull(getActivity()));
        realm = Realm.getDefaultInstance();

        Glide.with(Objects.requireNonNull(getActivity())).load(pic).into(imgItemSelected);

        txtPercent.setText(rate);
        txtPrice.setText(pricee);
        title.setText(name);
        txtDes.setText(des);


        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_fav, R.id.btn_add_cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_fav:
                if (btnFav.isSelected()){

                    btnFav.setSelected(false);
                }
                else{

                    btnFav.setSelected(true);
                }
                break;
            case R.id.btn_add_cart:
                addItem();
                break;
        }
    }

    private void addItem() {


        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                ProductsModel productsModel = bgRealm.createObject(ProductsModel.class);

                productsModel.setTitle(name);
                productsModel.setPrice(pricee);
                productsModel.setPriceDiscount(dis);
                productsModel.setDes(des);
                productsModel.setImg1(pic);
                productsModel.setNumberRate(rate);
                productsModel.setId(Integer.valueOf(id));


            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // Transaction was a success.

                Toast.makeText(getActivity(), "data inserted", Toast.LENGTH_SHORT).show();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
