package com.kursatmemis.countriesapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.viewbinding.ViewBinding
import com.kursatmemis.countriesapp.R
import com.kursatmemis.countriesapp.databinding.ItemCountryBinding
import com.kursatmemis.countriesapp.model.Country
import com.kursatmemis.countriesapp.model.DataModel
import com.kursatmemis.countriesapp.util.downloadFromUrl
import com.kursatmemis.countriesapp.util.placeHolderProgressBar
import javax.inject.Inject

class CustomAdapter @Inject constructor(
    context: Context,
    val dataList: ArrayList<DataModel>,
    private val adapterDataLoader: AdapterDataLoader
) :
    ArrayAdapter<DataModel>(context, R.layout.item_country, dataList) {

    private lateinit var binding: ItemCountryBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        if (convertView == null) {
            val layoutInflater = LayoutInflater.from(context)
            binding = ItemCountryBinding.inflate(layoutInflater, parent, false)

        } else {
            binding = ItemCountryBinding.bind(convertView)
        }

        adapterDataLoader.loadData(binding, dataList, position)

        return binding.root
    }

    fun updateAdapter(newCountryList: List<DataModel>) {
        clear()
        addAll(newCountryList)
        notifyDataSetChanged()
    }

}

interface AdapterDataLoader {
    fun loadData(viewBinding: ViewBinding, dataList: ArrayList<DataModel>, position: Int)
}

class CountryAdapterDataLoader: AdapterDataLoader {
    override fun loadData(binding: ViewBinding, dataList: ArrayList<DataModel>, position: Int) {
        binding as ItemCountryBinding
        dataList as ArrayList<Country>
        val imgUrl = dataList[position].flag
        binding.itemCountryImageImageView.downloadFromUrl(
            imgUrl,
            placeHolderProgressBar(binding.itemCountryImageImageView.context)
        )
        binding.itemCountryNameTextView.text = dataList[position].name
        binding.itemRegionNameTextView.text = dataList[position].region
    }
}