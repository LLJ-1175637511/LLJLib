package com.llj.baselib.ui

import android.app.Activity
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

inline fun <reified VB : ViewBinding> Activity.activityInflate(crossinline param: (inflater: LayoutInflater) -> VB) =
    lazy {
        inflateBinding<VB>(layoutInflater, param).apply { setContentView(root) }
    }

inline fun <reified VB : ViewBinding> inflateBinding(
    inflater: LayoutInflater,
    param: (inflater: LayoutInflater) -> VB
) = param.invoke(inflater)