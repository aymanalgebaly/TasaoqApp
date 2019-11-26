package com.compubase.tasaoq.ui.fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.compubase.tasaoq.R;
import com.compubase.tasaoq.adapter.CartAdapter;
import com.compubase.tasaoq.data.API;
import com.compubase.tasaoq.helper.RequestHandler;
import com.compubase.tasaoq.helper.RetrofitClient;
import com.compubase.tasaoq.model.ProductsModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.realm.Realm;
import io.realm.RealmResults;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {


    @BindView(R.id.rcv_cart)
    RecyclerView rcvCart;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.total_price_cart)
    TextView totalPriceCart;
    @BindView(R.id.lin_cart)
    LinearLayout linCart;
    Unbinder unbinder;

    private int totalPrice = 0;

    Realm realm;

    private List<ProductsModel>productsModels;

    private List<ProductsModel>productsModelList = new ArrayList<>();

    private CartAdapter cartAdapter;

    private String [] list;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        unbinder = ButterKnife.bind(this, view);

        Realm.init(Objects.requireNonNull(getContext()));
        realm = Realm.getDefaultInstance();

        setupRecycler();
        showData();
//        refreshData();
        return view;
    }

    private void refreshData() {

        RealmResults<ProductsModel> all = realm.where(ProductsModel.class).findAll();

        all.load();
        for (int i = 0; i <all.size() ; i++) {

            productsModelList.clear();

            ProductsModel productsModel = new ProductsModel();

            productsModel.setTitle(Objects.requireNonNull(all.get(i)).getTitle());
            productsModel.setDes(Objects.requireNonNull(all.get(i)).getDes());
            productsModel.setImg1(Objects.requireNonNull(all.get(i)).getImg1());
            productsModel.setPrice(Objects.requireNonNull(all.get(i)).getPrice());


            productsModelList.add(productsModel);
        }

        cartAdapter = new CartAdapter(productsModelList);
        rcvCart.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

    }

    private void setupRecycler() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        rcvCart.setLayoutManager(linearLayoutManager);

    }

    @SuppressLint("SetTextI18n")
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


            if (all.get(i) != null) {
                String price = all.get(i).getPrice();
                int pr = Integer.parseInt(price);
                totalPrice = totalPrice +pr;
                totalPriceCart.setText(String.valueOf(totalPrice));
            }
        }
//        realm.commitTransaction();
        cartAdapter = new CartAdapter(productsModelList);
        rcvCart.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_confirm)
    public void onViewClicked() {
        confirmOrder();
    }

    private void functionVolly()
    {

            String idUser = ""; // Shof B2a Btgebo Mnen
            String address = ""; // Shof B2a Btgebo Mnen
            String totalPrice = ""; // Shof B2a Btgebo Mnen


            StringBuilder GET_JSON_DATA_HTTP_URL = new StringBuilder("http://fastini.alosboiya.com.sa/store_app.asmx/insert_orders?id_user=" + idUser + "&address=" + address + "&totle_price=" + totalPrice);


            for(int i = 0;i<=productsModelList.size();i++)
            {
                GET_JSON_DATA_HTTP_URL.append("&id_product=").append(String.valueOf(productsModelList.get(i).getId()));
            }

            Toast.makeText(getActivity(), GET_JSON_DATA_HTTP_URL, Toast.LENGTH_SHORT).show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, GET_JSON_DATA_HTTP_URL.toString(),

                    new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();

                        }
                    }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {


                    Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();

                }

            });

            RequestHandler.getInstance(getActivity()).addToRequestQueue(stringRequest);


    }

    private void confirmOrder() {

        String[] title = new String[]{"كاجو","لوز"};

        List<String>stringList = new ArrayList<>();

        stringList.add("one");
        stringList.add("two");

        Retrofit retrofit = RetrofitClient.getInstant();
        API api = retrofit.create(API.class);
        Call<ResponseBody> responseBodyCall = api.insertOrders("2","hhhh","5000",stringList);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()){

                    Toast.makeText(getActivity(), "order insert", Toast.LENGTH_SHORT).show();
                    Log.i( "onResponse", String.valueOf(response));

                    try {
                        assert response.body() != null;
                        String string = response.body().string();
                        if (string.equals("True")){
                            Toast.makeText(getActivity(), "response order insert", Toast.LENGTH_SHORT).show();
                            Log.i( "onResponses", String.valueOf(response));

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
