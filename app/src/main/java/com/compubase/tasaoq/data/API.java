package com.compubase.tasaoq.data;

import com.compubase.tasaoq.model.ProductsModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API {

    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> register (
            @Field("id_admin") String id_admin,
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("phone") String phone,
            @Field("img") String img
    );

    @FormUrlEncoded
    @POST("sendactivemail")
    Call<ResponseBody>SendSMS(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("entercode_activemail")
    Call<ResponseBody>EnterCode(
            @Field("enter_code") String code
    );

    @FormUrlEncoded
    @POST("login_user")
    Call<ResponseBody>UserLogin(
            @Field("email") String email,
            @Field("pass") String pass
    );

    @FormUrlEncoded
    @POST("select_all_product")
    Call<ResponseBody>viewProducts(
            @Field("id_admin") String id_admin
    );

    @FormUrlEncoded
    @POST("insert_orders")
    Call<ResponseBody> insertOrders(
            @Field("id_user") String id_user,
            @Field("address") String address,
            @Field("totle_price") String totle_price,
            @Field("id_product[]") List<String> stringList);

    @FormUrlEncoded
    @POST("insert_fav")
    Call<ResponseBody>insertFav(
            @Field("id_user") String id_user,
            @Field("id_product") String id_product
    );

    @FormUrlEncoded
    @POST("select_all_product_by_category")
    Call<ResponseBody>showCategory(
            @Field("id_admin") String id_admin,
            @Field("category") String category
    );
    @FormUrlEncoded
    @POST("update_user")
    Call<ResponseBody>updateProfile(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String pass,
            @Field("phone") String phone,
            @Field("img") String img,
            @Field("id_user") String id
    );
}
