package com.arysugiarto.mandiritest.ui.applenews

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.arysugiarto.mandiritest.R
import com.arysugiarto.mandiritest.data.remote.Result
import com.arysugiarto.mandiritest.databinding.FragmentAppleNewsBinding
import com.arysugiarto.mandiritest.util.navController
import com.arysugiarto.mandiritest.util.navigateOrNull
import com.arysugiarto.mandiritest.util.viewBinding
import com.arysugiarto.mandiritest.viewmodel.AppleNewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class AppleNewsFragment : Fragment(R.layout.fragment_apple_news) {

    private val binding by viewBinding<FragmentAppleNewsBinding>()
    private val viewModels by hiltNavGraphViewModels<AppleNewsViewModel>(R.id.apple)
    private val newsAdapter = AppleNewsAdapter.newsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initCallback()

    }

    private fun initViewModel() {
        viewModels.requestNews()
    }

    private fun initCallback() {
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
                    Timber.e("test")
                }
                is Result.Error<*> -> {

                }
                else -> {
                }
            }
            binding.rvNews.adapter = newsAdapter

        }

    private fun initClickAdapter() {
        AppleNewsAdapter.SetOnClickItem.setOnClickItemListener { item ->
            navController.navigateOrNull(
                AppleNewsFragmentDirections.actionAppleFragmentToDetailFragment(
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


