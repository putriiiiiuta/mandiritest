package com.arysugiarto.mandiritest.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.arysugiarto.mandiritest.R
import com.arysugiarto.mandiritest.data.remote.Result
import com.arysugiarto.mandiritest.databinding.FragmentHomeBinding
import com.arysugiarto.mandiritest.util.navController
import com.arysugiarto.mandiritest.util.navigateOrNull
import com.arysugiarto.mandiritest.util.viewBinding
import com.arysugiarto.mandiritest.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding<FragmentHomeBinding>()
    private val viewModels by hiltNavGraphViewModels<HomeViewModel>(R.id.home)
    private val newsAdapter = HomeAdapter.newsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initCallback()

    }

    private fun initViewModel(){
        viewModels.requestNews()
    }

    private fun initCallback(){
        initNewsCallback()
        initClickAdapter()
    }

    private fun initNewsCallback() =
        viewModels.news.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {

                }
                is Result.Success -> {
                    newsAdapter.items = result.data?.articles.orEmpty()

                }
                is Result.Error<*> -> {

                }
                else -> {
                }
            }
            binding.rvNews.adapter = newsAdapter

        }

    private fun initClickAdapter() {
        HomeAdapter.SetOnClickItem.setOnClickItemListener { item ->
            navController.navigateOrNull(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                    item.title,
                    item.publishedAt,
                    item.content,
                    item.urlToImage,
                    item.description,
                    item.source?.name
                )
            )
        }
    }


}