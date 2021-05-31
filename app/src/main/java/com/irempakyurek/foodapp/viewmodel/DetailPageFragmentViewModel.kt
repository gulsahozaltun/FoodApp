package com.irempakyurek.foodapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irempakyurek.foodapp.repository.FoodsDaoRepo

class DetailPageFragmentViewModel: ViewModel() {
    private val frepo = FoodsDaoRepo()
    var result = MutableLiveData<String>()

    init {
        result = frepo.bringResult()
    }

    fun smallSizePrice(price: Int){
        frepo.getSmallSizePrice(price)
    }

    fun mediumSizePrice(price: Int){
        frepo.getMediumSizePrice(price)
    }

    fun largeSizePrice(price: Int){
        frepo.getLargeSizePrice(price)
    }
}