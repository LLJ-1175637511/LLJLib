package com.llj.baselib.extensions

import android.content.Context
import android.util.Base64
import android.widget.Toast

fun Context.toastShort(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Context.toastLong(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

/**
 * byte 转 base64
 * 建议子线程调用
 */
fun ByteArray.toBase64(): String = Base64.encodeToString(this, Base64.DEFAULT)

fun String.versionToInt(): Int? {
    if (this.contains('.')) {
        val ls = this.split('.')
        if (ls.size == 3) {
            return ls[0].toInt() * 100 + ls[1].toInt() * 10 + ls[2].toInt()
        }
    }
    return null
}

fun String.getMediaName(): String {
    return if (this.contains("/")) {
        val t = this.split("/")
        t[t.lastIndex]
    } else this
}