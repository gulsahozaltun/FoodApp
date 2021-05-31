package com.irempakyurek.foodapp.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CRUDAnswer(@SerializedName("message")
                      @Expose
                      var message:String,
                      @SerializedName("success")
                      @Expose
                      var success:Int) {
}