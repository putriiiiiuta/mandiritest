package com.arysugiarto.mandiritest.util

import android.os.Build
import android.text.Html
import android.util.Patterns
import androidx.core.text.buildSpannedString
import com.arysugiarto.mandiritest.util.Const.PRODUCT.NOT_AVAILABLE
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

const val emptyString = ""
const val emptyInt = -1
const val emptyFloat = 0F
const val emptyLong = 0L
const val emptyDouble = 0.0
const val emptyBoolean = false

/**
 * Proceed and Return Non-Null Value of Nullable String
 * @param default is the replacement of nullable value of any word in the string
 * @param condition is the condition which nullable can be replaced with non-null value
 * @return [String]
 */
fun String?.orEmpty(
    default: String = emptyString,
    condition: Regex? = null
): String {
    val regex = condition ?: Regex("^(null|NULL|Null)")
    return if(this?.contains(regex) == true) replace(regex, default) else this ?: default
}

/**
 * Proceed and Return Non-Null Value of Nullable String
 * @return [String]
 */
val String?.orEmpty get() = orEmpty(emptyString)

/**
 * Proceed and Return Non-Null Value of Nullable CharSequence
 * @param default is the replacement of nullable value of any word in the CharSequence
 * @param condition is the condition which nullable can be replaced with non-null value
 * @return [CharSequence]
 */
fun CharSequence?.orEmpty(
    default: String = emptyString,
    condition: Regex? = null
): CharSequence {
    val regex = condition ?: Regex("^(null|NULL|Null)")
    return if(this?.contains(regex) == true) replace(regex, default) else this ?: default
}

/**
 * Proceed and Return Non-Null Value of Nullable CharSequence
 * @return [CharSequence]
 */
val CharSequence?.orEmpty get() = orEmpty(emptyString)

/**
 * Proceed and Return Non-Null Value of Nullable Long
 * @param default is the replacement of nullable value of Long
 * @return [Long]
 */
fun Long?.orEmpty(default: Long = emptyLong) = this ?: default

val Long?.orNotAvailable: String get() =
    if (this == null || this == emptyLong) NOT_AVAILABLE else this.toString()

/**
 * Proceed and Return Non-Null Value of Nullable Long
 * @return [Long]
 */
val Long?.orEmpty get() = orEmpty(emptyLong)

/**
 * Proceed and Return Non-Null Value of Nullable Float
 * @param default is the replacement of nullable value of Float
 * @return [Float]
 */
fun Float?.orEmpty(default: Float = emptyFloat) = this ?: default

/**
 * Proceed and Return Non-Null Value of Nullable Int
 * @param default is the replacement of nullable value of Int
 * @return [Int]
 */
fun Int?.orEmpty(default: Int = emptyInt) = this ?: default

/**
 * Proceed and Return Non-Null Value of Nullable Int
 * @return [Int]
 */
val Int?.orEmpty get() = orEmpty()

/**
 * Proceed and Return Non-Null Value of Nullable Double
 * @param default is the replacement of nullable value of Double
 * @return [Double]
 */
fun Double?.orEmpty(default: Double = emptyDouble) = this ?: default

/**
 * Proceed and Return Non-Null Value of Nullable Boolean
 * @param default is the replacement of nullable value of Boolean
 * @return [Boolean]
 */
fun Boolean?.orEmpty(default: Boolean = emptyBoolean) = this ?: default

/**
 * Proceed and Return Non-Null Value of Nullable Boolean
 * @return [Boolean]
 */
val Boolean?.orEmpty get() = orEmpty()

/**
 * @param prefix is for the currency format, default on RP
 * @param suffix is written on the end of price
 * **/

fun Int?.currencyFormat(
    prefix: String = "Rp",
    suffix: String = ""
) : String {
    val currencyFormat: NumberFormat = DecimalFormat("#,###")
    return try {
        val currency = if (orEmpty > 0) this else 0

        "${prefix}${currencyFormat.format(currency).toString()
            .replace(',', '.')} $suffix"
    } catch (e: Exception) {
        "$prefix 0"
    }
}



















