package com.arysugiarto.mandiritest.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LevelListDrawable
import android.os.Build
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AlertDialog
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import androidx.core.view.doOnPreDraw
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.arysugiarto.mandiritest.R
import kotlinx.coroutines.*
import java.security.MessageDigest
import kotlin.math.round

/**
 * This Function will Load any source to an ImageView with Desired format And Request
 * @param source is the imageSource with Type of Bitmap, Drawable, String, Uri , resDrawable, File, ByteArray And CustomModel
 * @param corner give an option of image transform type (Rounded, Circle, Rectangle)
 * @param overrideSize will resize image with desired pixel size
 * @param radius will round corner to desired size of [Int] pixels
 * @param shimmerLoad Condition to give shimmer placeholder load effect if condition set to true
 * @param background give background outside image source
 * @param scaleType is Scaling Type that will be displayed to user
 * @param placeHolder give a default image to ImageView when target image is loading or error
 */
@SuppressLint("CheckResult")
fun ImageView.loadImage(
    source: Any?,
    corner: ImageCornerOptions? = ImageCornerOptions.NORMAL,
    radius: Int? = null,
    shimmerLoad: Boolean = false,
    overrideSize: Int? = null,
    placeHolder: Drawable? = ColorDrawable(Color.LTGRAY),
    @ColorRes background: Int? = null,
    scaleType: ImageView.ScaleType? = null
) {
    val requestOptions = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

    when (corner) {
        ImageCornerOptions.NORMAL -> {
            requestOptions.transform(
                CenterCrop()
            )
        }

        ImageCornerOptions.CIRCLE -> {
            requestOptions.transform(
                CenterCrop(),
                RoundedCorners(
                    resources.getDimensionPixelSize(
                        R.dimen.image_options_circle_radius
                    )
                )
            )
        }

        ImageCornerOptions.ROUNDED -> {
            val cornerRadius = if (radius != null) {
                round(
                    TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        radius.toFloat(),
                        resources.displayMetrics
                    )
                ).toInt()
            } else {
                resources.getDimensionPixelSize(R.dimen.image_options_round_radius)
            }

            requestOptions.transform(
                CenterCrop(),
                RoundedCorners(cornerRadius)
            )
        }
    }

    source?.let {
        if (scaleType == ImageView.ScaleType.FIT_CENTER) requestOptions.fitCenter()
        else if (scaleType == ImageView.ScaleType.CENTER_INSIDE) requestOptions.centerInside()

        if (overrideSize != null) {
            requestOptions.override(overrideSize)
        }

        Glide.with(context)
            .load(it)
            .apply(requestOptions)
            .into(this)

        if (background != null) {
            doOnPreDraw {
                setBackground(
                    context.createCircleDrawable(
                        Pair(measuredWidth, measuredHeight),
                        background
                    )
                )
            }
        }
    }
}

// Image Shape Options
enum class ImageCornerOptions {
    NORMAL, CIRCLE, ROUNDED
}

/**
 * Will Set Text to a TextView same as [TextView.setText] with nullable access reference
 * @param text used for display text to user
 * @param default default value when text inside [text] is Empty
 */
fun TextView.textOrNull(
    text: CharSequence?,
    default: String = ""
) {
    this.text = text.orEmpty(default)
}

/**
 * Setter & Getter for [TextView.textOrNull]
 * it's has Same Functionality as [TextView.setText] or [TextView.getText] with nullable access reference
 */
var TextView.textOrNull: CharSequence?
    get() = text.orEmpty
    set(value) = textOrNull(value)
