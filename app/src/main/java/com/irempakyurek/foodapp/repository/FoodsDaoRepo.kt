package com.irempakyurek.foodapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.irempakyurek.foodapp.adapter.FoodAdapter
import com.irempakyurek.foodapp.entity.Foods
import com.irempakyurek.foodapp.entity.FoodsAnswer
import com.irempakyurek.foodapp.retrofit.ApiUtils
import com.irempakyurek.foodapp.retrofit.FoodsDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodsDaoRepo {
    private val foodsList: MutableLiveData<List<Foods>>
    private val fdaoi : FoodsDaoInterface
    var priceResult = MutableLiveData<String>()

    init {
        priceResult = MutableLiveData<String>("0")
        foodsList = MutableLiveData()
        fdaoi = ApiUtils.getFoodsDaoInterface()
    }

    fun bringResult():MutableLiveData<String>{
        return priceResult
    }

    fun bringFoods():MutableLiveData<List<Foods>>{
        return foodsList
    }

    fun getAllFoods(){
        fdaoi.allFoods().enqueue(object : Callback<FoodsAnswer> {
            override fun onResponse(call: Call<FoodsAnswer>?, response: Response<FoodsAnswer>) {
                val list:List<Foods> = response.body().foods
                foodsList.value = list
            }

            override fun onFailure(call: Call<FoodsAnswer>?, t: Throwable?) {
                Log.e("Error", "failed")
            }
        })
    }

    fun getSmallSizePrice(price:Int){
        priceResult.value = price.toString()
    }

    fun getMediumSizePrice(price:Int){
        val medium = price + 5
        priceResult.value = medium.toString()
    }

    fun getLargeSizePrice(price:Int){
        val large = price + 10
        priceResult.value = large.toString()
    }

    fun searchFood(searchWord: String) {
        fdaoi.searchFood(searchWord).enqueue(object : Callback<FoodsAnswer> {
            override fun onResponse(call: Call<FoodsAnswer>, response: Response<FoodsAnswer>) {
                val list = response.body().foods
                foodsList.value = list
            }
            override fun onFailure(call: Call<FoodsAnswer>, t: Throwable) {}
        })
    }
}