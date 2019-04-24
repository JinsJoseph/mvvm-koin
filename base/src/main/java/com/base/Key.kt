package com.base

import android.os.Parcelable
import android.support.v4.app.Fragment

/**
 * Created by Zhuinden on 2017. 01. 12..
 */

interface Key : Parcelable {
    val fragmentTag: String

    fun newFragment(): Fragment

    fun getIdentify(): String
}
