package com.compubase.tasaoq.data;

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
}
