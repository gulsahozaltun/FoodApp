package com.irempakyurek.foodapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.irempakyurek.foodapp.R
import com.irempakyurek.foodapp.adapter.FoodAdapter
import com.irempakyurek.foodapp.databinding.FragmentMainPageBinding
import com.irempakyurek.foodapp.entity.Foods
import com.irempakyurek.foodapp.entity.FoodsAnswer
import com.irempakyurek.foodapp.retrofit.ApiUtils
import com.irempakyurek.foodapp.retrofit.FoodsDaoInterface
import com.irempakyurek.foodapp.viewmodel.MainPageFragmentViewModel
import kotlinx.android.synthetic.main.fragment_main_page.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//, SearchView.OnQueryTextListener
class MainPageFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var design: FragmentMainPageBinding
    private lateinit var adapter: FoodAdapter
    private lateinit var viewModel: MainPageFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        design = DataBindingUtil.inflate(inflater, R.layout.fragment_main_page, container, false)

        design.toolbarTitle = ""
        (activity as AppCompatActivity).setSupportActionBar(design.toolbarMainPage)

        viewModel.foodsList.observe(viewLifecycleOwner, { list ->
            adapter = FoodAdapter(requireContext(), list, viewModel)
            design.foodsAdapter = adapter
        })

        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel: MainPageFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_search_menu, menu)

        val item = menu.findItem(R.id.action_search)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel.search(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        viewModel.search(newText)
        return true
    }
}