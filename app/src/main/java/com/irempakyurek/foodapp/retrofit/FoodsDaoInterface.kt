package com.irempakyurek.foodapp.retrofit

import com.irempakyurek.foodapp.entity.CRUDAnswer
import com.irempakyurek.foodapp.entity.FoodsAnswer
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodsDaoInterface {
    @GET("yemekler/tum_yemekler.php")
    fun allFoods(): Call<FoodsAnswer>

    @POST("yemekler/tum_yemekler_arama.php")
    @FormUrlEncoded
    fun searchFood(@Field("yemek_adi") food_name:String): Call<FoodsAnswer>

    @POST("yemekler/delete_sepet_yemek.php")
    @FormUrlEncoded
    fun deleteChartFood(@Field("yemek_adi") chart_food_name:String,
                        @Field("yemek_fiyat") chart_food_price:Int): Call<CRUDAnswer>

    @POST("yemekler/insert_sepet_yemek.php")
    @FormUrlEncoded
    fun addCartFood(@Field("yemek_adi") chart_food_name:String,
                    @Field("yemek_fiyat") chart_food_price:String): Call<CRUDAnswer>

}