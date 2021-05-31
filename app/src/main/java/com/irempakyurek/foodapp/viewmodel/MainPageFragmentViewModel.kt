package com.irempakyurek.foodapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irempakyurek.foodapp.entity.Foods
import com.irempakyurek.foodapp.repository.FoodsDaoRepo

class MainPageFragmentViewModel: ViewModel() {
    private val frepo = FoodsDaoRepo()
    val foodsList : MutableLiveData<List<Foods>>

    init {
        loadFoods()
        foodsList = frepo.bringFoods()
    }

    fun loadFoods(){
        frepo.getAllFoods()
    }

    fun search(searchWord:String) {
        frepo.searchFood(searchWord)
    }
}