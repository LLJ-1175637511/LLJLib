package com.llj.baselib.extensions

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

/***
 * v表示：
 * b表示-
 */
enum class TimeEnum {
    Y年M月D日,//2020年02月16日
    YYMMDD,//20200216
    YYbMMbDD,//2020-02-16
    YYbMMbDDHHvMMvSS, //2020-02-16 10:10:10
    YYbMMbDDHHcMMcSS
}

@SuppressLint("SimpleDateFormat")
fun Long.convertTime(timeEnum: TimeEnum): String {
    val type = when (timeEnum) {
        TimeEnum.YYMMDD -> {
            "yyyyMMdd"
        }
        TimeEnum.Y年M月D日 -> {
            "yyyy年MM月dd日"
        }
        TimeEnum.YYbMMbDD -> {
            "yyyy-MM-dd"
        }
        TimeEnum.YYbMMbDDHHvMMvSS -> {
            "yyyy-MM-dd HH-mm-ss"
        }
        TimeEnum.YYbMMbDDHHcMMcSS -> {
            "yyyy-MM-dd HH:mm:ss"
        }
    }
    return SimpleDateFormat(type).format(this)
}


/*
格林尼治时间转换
 */
@SuppressLint("SimpleDateFormat")
fun String.convertGeLinTime(): String = this.substring(0..9)

@SuppressLint("SimpleDateFormat")
fun String.convertLongTime(timeEnum: TimeEnum): Long {
    val type = when (timeEnum) {
        TimeEnum.YYMMDD -> {
            "yyyyMMdd"
        }
        TimeEnum.Y年M月D日 -> {
            "yyyy年MM月dd日"
        }
        TimeEnum.YYbMMbDD -> {
            "yyyy-MM-dd"
        }
        TimeEnum.YYbMMbDDHHvMMvSS -> {
            "yyyy-MM-dd HH-mm-ss"
        }
        TimeEnum.YYbMMbDDHHcMMcSS -> {
            "yyyy-MM-dd HH:mm:ss"
        }
    }
    return SimpleDateFormat(type).parse(this).time
}

fun Int.dateAddZero(): String = if (this < 10) "0$this" else this.toString()