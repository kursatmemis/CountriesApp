package com.kursatmemis.countriesapp.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.kursatmemis.countriesapp.databinding.FragmentDetailBinding
import com.kursatmemis.countriesapp.model.Country
import com.kursatmemis.countriesapp.model.DataModel
import com.kursatmemis.countriesapp.util.downloadFromUrl
import com.kursatmemis.countriesapp.util.placeHolderProgressBar
import java.io.Serializable

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private lateinit var dataModel: DataModel
    private lateinit var detailFragmentUILoader: DetailFragmentUILoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle: DetailFragmentArgs by navArgs()
        dataModel = bundle.dataModel
        detailFragmentUILoader = bundle.loaderDetailUIData
    }

    override fun createBindingObject(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding? {
        return FragmentDetailBinding.inflate(inflater, container, false)
    }

    override fun setupUI() {
        loadUIData()
    }

    private fun loadUIData() {
        detailFragmentUILoader.loadUIData(binding, dataModel)
    }

}

interface DetailFragmentUILoader : Serializable {
    fun loadUIData(binding: FragmentDetailBinding, dataModel: DataModel)
}

class CountryDataLoader(val context: Context) : DetailFragmentUILoader {

    override fun loadUIData(binding: FragmentDetailBinding, dataModel: DataModel) {
        val country = dataModel as Country
        binding.countryImageImageView.downloadFromUrl(
            country.flag,
            placeHolderProgressBar(context)
        )
        binding.countryNameTextView.text = country.name
        binding.capitalTextView.text = country.capital
        binding.regionTextView.text = country.region
        binding.currencyTextView.text = country.currency
        binding.moneyTextView.text = country.language
    }

}

