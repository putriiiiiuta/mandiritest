package com.arysugiarto.mandiritest.ui.home

import android.widget.ImageView
import com.arysugiarto.mandiritest.base.BaseAdapter
import com.arysugiarto.mandiritest.data.remote.model.NewsResponse
import com.arysugiarto.mandiritest.databinding.ItemNewsBinding
import com.arysugiarto.mandiritest.util.ImageCornerOptions
import com.arysugiarto.mandiritest.util.loadImage
import com.arysugiarto.mandiritest.util.textOrNull

object HomeAdapter {
    val newsAdapter =
        BaseAdapter.adapterOf<NewsResponse.Article, ItemNewsBinding>(
            register = BaseAdapter.Register(
                onBindHolder = { pos, item, view ->
                    view.run {

                        tvTitle.textOrNull = item.title
                        tvDate.textOrNull = item.publishedAt
                        tvSource.textOrNull = item.source?.name

                        ivPatnerNews.loadImage(
                            item.urlToImage,
                            scaleType = ImageView.ScaleType.CENTER_CROP,
                            corner = ImageCornerOptions.ROUNDED,
                        )

                        clItem.setOnClickListener {
                            SetOnClickItem.onClickListener.invoke(item)
                        }

                    }
                }
            ),
            diff = BaseAdapter.Diff(
                areItemsTheSame = { old, new -> old.source?.id == new.source?.id },
                areContentsTheSame = { old, new -> old == new }
            )
        )

    object SetOnClickItem {
        var onClickListener: (NewsResponse.Article) -> Unit = { _ -> }

        fun setOnClickItemListener(listener: (NewsResponse.Article) -> Unit) {
            onClickListener = listener
        }

    }

}