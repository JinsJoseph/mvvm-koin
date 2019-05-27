package com.utils.ext

import android.os.Build
import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.jakewharton.rxbinding2.view.RxView
import com.utils.ListOfSomething
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by Kaz on 14:13 8/27/18
 */
fun ViewGroup.inflateExt(layoutId: Int) = LayoutInflater.from(context).inflate(layoutId, this, false)

fun View.clickWithDebounce(debounceTime: Long = 600L, action: (view: View) -> Unit): Disposable =
        RxView.clicks(this)
                .throttleFirst(debounceTime, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .subscribe { action(this) }

inline fun <reified T> Gson.toList(json: String) =
        fromJson<List<T>>(
                json,
                ListOfSomething<T>(T::class.java)
        )

inline fun <reified T> Gson.toList(jsonArr: JsonArray) =
        fromJson<List<T>>(
                jsonArr,
                ListOfSomething<T>(T::class.java)
        )

inline fun <reified T> Gson.toList(jsonObject: JsonObject) =
        fromJson<List<T>>(
                jsonObject,
                ListOfSomething<T>(T::class.java)
        )

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.isVisible() = visibility == View.VISIBLE

fun View.setVisibility(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun TextView.setDrawableStart(@DrawableRes start: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) setCompoundDrawablesRelativeWithIntrinsicBounds(start, 0, 0, 0)
}

fun TextView.setDrawableTop(@DrawableRes top: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) setCompoundDrawablesRelativeWithIntrinsicBounds(0, top, 0, 0)
}

fun TextView.setDrawableEnd(@DrawableRes end: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, end, 0)
}

fun TextView.setDrawableBottom(@DrawableRes bottom: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, bottom)
}

fun View.setBackgroundColorz(@ColorRes resId: Int) = setBackgroundColor(ContextCompat.getColor(context, resId))

fun TextView.setTextColorz(@ColorRes resId: Int) = setTextColor(ContextCompat.getColor(context, resId))

fun EditText.onTextChanged(text: (String?) -> Unit) = addTextChangedListener(object : TextWatcher {
    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        text(s.toString())
    }
})

val Fragment.requireArguments: Bundle
    get() = arguments ?: throw Exception("No arguments found!")

fun ViewPager.onPageSelected(position: (Int?) -> Unit) {
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(p0: Int) {
        }

        override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
        }

        override fun onPageSelected(position: Int) {
            position(position)
        }
    })
}

fun String.capitalizedWord(): String {
    val words = replace('_', ' ').toLowerCase(Locale.getDefault())
            .split(" ".toRegex())
            .dropLastWhile { it.isEmpty() }
            .toTypedArray()
    var aString = ""
    for (word in words) {
        aString = aString + word.substring(0, 1).toUpperCase(Locale.getDefault()) + word.substring(1) + " "
    }
    return aString
}