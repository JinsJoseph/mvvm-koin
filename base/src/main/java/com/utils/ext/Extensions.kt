package com.utils.ext

import android.os.Build
import android.support.annotation.DrawableRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.jakewharton.rxbinding2.view.RxView
import com.utils.ListOfSomething
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
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
        this.fromJson<List<T>>(
                json,
                ListOfSomething<T>(T::class.java)
        )

inline fun <reified T> Gson.toList(jsonArr: JsonArray) =
        this.fromJson<List<T>>(
                jsonArr,
                ListOfSomething<T>(T::class.java)
        )

inline fun <reified T> Gson.toList(jsonObject: JsonObject) =
        this.fromJson<List<T>>(
                jsonObject,
                ListOfSomething<T>(T::class.java)
        )

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.isVisible() = this.visibility == View.VISIBLE

fun View.setVisibility(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun TextView.setDrawableStart(@DrawableRes start: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        this.setCompoundDrawablesRelativeWithIntrinsicBounds(start, 0, 0, 0)
    }
}

fun TextView.setDrawableTop(@DrawableRes top: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        this.setCompoundDrawablesRelativeWithIntrinsicBounds(0, top, 0, 0)
    }
}

fun TextView.setDrawableEnd(@DrawableRes end: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        this.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, end, 0)
    }
}

fun TextView.setDrawableBottom(@DrawableRes bottom: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        this.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, bottom)
    }
}
