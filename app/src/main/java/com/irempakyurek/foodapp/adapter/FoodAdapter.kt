package com.irempakyurek.foodapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.irempakyurek.foodapp.databinding.CardDesignBinding
import com.irempakyurek.foodapp.entity.Foods
import com.irempakyurek.foodapp.fragment.MainPageFragmentDirections
import com.irempakyurek.foodapp.viewmodel.MainPageFragmentViewModel
import com.squareup.picasso.Picasso

class FoodAdapter(var mContext: Context,
                  var foodList:List<Foods>,
                  var viewModel: MainPageFragmentViewModel) : RecyclerView.Adapter<FoodAdapter.CategoryDesignHolder>() {

    inner class CategoryDesignHolder(cardDesignBinding: CardDesignBinding): RecyclerView.ViewHolder(cardDesignBinding.root){
        var cardDesignBinding: CardDesignBinding

        init {
            this.cardDesignBinding = cardDesignBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDesignHolder {
        val layoutInflater= LayoutInflater.from(mContext)
        val design= CardDesignBinding.inflate(layoutInflater,parent,false)
        return CategoryDesignHolder(design)
    }

    override fun onBindViewHolder(holder: CategoryDesignHolder, position: Int) {

        val food = foodList.get(position)

        val view = holder.cardDesignBinding
        view.foodObject = food

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/"+"${food.food_image_name}"
        Picasso.get().load(url).into(holder.cardDesignBinding.imgFoodImage)

        holder.cardDesignBinding.cvFood.setOnClickListener {
            val transition = MainPageFragmentDirections.detailAction(food)
            Navigation.findNavController(it).navigate(transition)
        }

        holder.cardDesignBinding.btnDetail.setOnClickListener {
            val transition = MainPageFragmentDirections.detailAction(food)
            Navigation.findNavController(it).navigate(transition)
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}