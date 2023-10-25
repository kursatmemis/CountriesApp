package com.kursatmemis.countriesapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.kursatmemis.countriesapp.view.adapter.CustomAdapter
import com.kursatmemis.countriesapp.databinding.FragmentHomeBinding
import com.kursatmemis.countriesapp.model.DataModel
import com.kursatmemis.countriesapp.util.showToastMessage
import com.kursatmemis.countriesapp.viewmodel.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()
    @Inject
    lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeFragmentViewModel.fetchData()
    }

    override fun createBindingObject(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding? {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun setupUI() {
        binding.countryListView.adapter = customAdapter

        binding.countryListView.setOnItemClickListener { parent, view, position, id ->
            val dataModel = customAdapter.dataList[position]
            goToDetailFragmentWithDataLoader(dataModel, view)
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            homeFragmentViewModel.swipeRefreshData()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()
    }

    private fun goToDetailFragmentWithDataLoader(dataModel: DataModel, view: View) {
        val countryDataLoader = CountryDataLoader(requireContext())
        val navDirections =
            HomeFragmentDirections.actionHomeFragmentToDetailFragment(dataModel, countryDataLoader)
        Navigation.findNavController(view).navigate(navDirections)
    }

    private fun observeLiveData() {
        homeFragmentViewModel.dataModelListLiveData.observe(viewLifecycleOwner, Observer {
            customAdapter.updateAdapter(it)
        })

        homeFragmentViewModel.isDataLoaded.observe(viewLifecycleOwner, Observer {

            if (it) {
                closeProgressBar()
            } else {
                showProgressBar()
            }

        })

        homeFragmentViewModel.isError.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                var errorMessage = homeFragmentViewModel.errorMessage
                if (errorMessage.isNullOrEmpty()) {
                    errorMessage = "Error! Please try again later."
                }
                showToastMessage(requireContext(), errorMessage)
                closeProgressBar()
            }
        })

    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun closeProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

}




