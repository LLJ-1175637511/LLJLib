package com.llj.baselib.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding

open class BaseDialog<VB : ViewBinding>(
    private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB,
    private val style: Int = STYLE_NO_TITLE,
    private val res: Int = android.R.style.Theme_Holo_Light
) : DialogFragment() {

    private var _mBinding: VB ?= null
    var mBinding = _mBinding!!

    open fun initCreate() {}

    open fun initCreateView() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(style, res)
        initCreate()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initCreateView()
        return inflate.invoke(inflater,container,false).also { _mBinding = it }.root
    }

    fun destroyDialog() {
        //隐藏软键盘
        (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            requireActivity().currentFocus?.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
        this.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }

}