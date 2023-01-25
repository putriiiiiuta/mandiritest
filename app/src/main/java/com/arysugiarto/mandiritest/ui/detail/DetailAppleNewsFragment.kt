package com.arysugiarto.mandiritest.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.arysugiarto.mandiritest.R
import com.arysugiarto.mandiritest.databinding.FragmentDetailsBinding
import com.arysugiarto.mandiritest.util.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailAppleNewsFragment : Fragment(R.layout.fragment_details) {

    private val binding by viewBinding<FragmentDetailsBinding>()
    private val args by navArgs<DetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCallback()
        initOnClick()

    }

    private fun initCallback(){
        binding.ivNews.loadImage(
            args.image,
            scaleType = ImageView.ScaleType.CENTER_CROP,
            corner = ImageCornerOptions.ROUNDED,
        )
        binding.tvSource.textOrNull = args.source
        binding.tvTitle.textOrNull = args.title
        binding.tvDate.textOrNull = args.date
        binding.tvContent.textOrNull = args.content
    }

    private fun initOnClick() {
        binding.apply {
            ivBack.setOnClickListener(onClickCallback)
        }
    }

    private val onClickCallback = View.OnClickListener { view ->
        when (view) {
            binding.ivBack -> {
                navController.navigateUp()
            }
        }
    }

}