package com.informatika19100075.ollgaangeliquem_19100075_memo.network

import com.informatika.memos.model.ResponseActionmemos
import com.informatika.memos.model.ResponseAdmin
import com.informatika.memos.model.Responsememos
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {
    @GET("read.php")
    fun getMemos(): Call<ResponseMemos>

    @FormUrlEncoded
    @POST("create.php")
    fun insertMemos(
        @Field("Buat_catatan") buatCatatan: String?,
      ): Call<ResponseAction<Memos>

    @FormUrlEncoded
    @POST("update.php")
    fun updateMemos(
        @Field("id") id: String?,
        @Field("date") date: String?,
        @Field("time") time: String?
    ): Call<ResponseActionMemos>

    @FormUrlEncoded
    @POST("delete.php")
    fun deleteBarang(
        @Field("id") id: String?
    ): Call<ResponseActionMemos>

    @FormUrlEncoded
    @POST("login.php")
    fun logIn(
        @Field("Username") Username : String?,
        @Field("Password") Password : String?
    ):Call<ResponseAdmin>

    @FormUrlEncoded
    @POST("register.php")
    fun register(
        @Field("Username") Username : String?,
        @Field("Password") Password : String?
    ):Call<ResponseAdmin>
}