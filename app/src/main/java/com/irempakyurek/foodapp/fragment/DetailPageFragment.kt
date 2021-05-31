package com.irempakyurek.foodapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.irempakyurek.foodapp.R
import com.irempakyurek.foodapp.databinding.FragmentDetailPageBinding
import com.irempakyurek.foodapp.viewmodel.DetailPageFragmentViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail_page.*

class DetailPageFragment : Fragment() {
    private lateinit var design: FragmentDetailPageBinding
    private lateinit var viewModel: DetailPageFragmentViewModel
    private var price = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_page, container, false)

        val bundle: DetailPageFragmentArgs by navArgs()
        val transmittedFood = bundle.foodObject

        //design.toolbarTitle = transmittedFood.food_name
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/"+"${transmittedFood.food_image_name}"
        Picasso.get().load(url).into(design.imgFoodImage)


        design.foodObject = transmittedFood

        design.detailPageFragment = this

        viewModel.smallSizePrice(transmittedFood.food_price)

        viewModel.result.observe(viewLifecycleOwner,{ s->
            design.foodPrice = s
        })

        design.btnAddToCart.setOnClickListener {
            Snackbar.make(it,"${transmittedFood.food_name} sepete eklensin mi ?", Snackbar.LENGTH_LONG)
                .setAction("Evet"){
                    Snackbar.make(it,"Eklendi", Snackbar.LENGTH_SHORT).show()
                }.show()
        }

        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DetailPageFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun smallChipClicked(price: Int) {
        viewModel.smallSizePrice(price)
    }

    fun mediumChipClicked(price: Int) {
        viewModel.mediumSizePrice(price)
    }

    fun largeChipClicked(price: Int) {
        viewModel.largeSizePrice(price)
    }
}