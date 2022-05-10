package com.llj.baselib.extensions

import android.content.Context
import android.content.SharedPreferences

fun SharedPreferences.save(block: SharedPreferences.Editor.() -> Unit): Boolean {
    val edit = edit()
    block(edit)
    return edit.commit()
}

fun Context.getSP(key: String) =
    getSharedPreferences(key, Context.MODE_PRIVATE)

