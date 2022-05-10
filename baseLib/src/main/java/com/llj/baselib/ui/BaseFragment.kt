package com.llj.baselib.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

open class BaseFragment<VB : ViewBinding>(
    private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {

    private var _mBinding: VB? = null
    val mBinding get() = _mBinding!!

    open fun initCreateView() {}

    open fun initViewCreate() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initCreateView()
        return inflate.invoke(inflater, container, false).also { _mBinding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewCreate()
    }

    fun <T : Activity> startCommonActivity(activity: Class<T>) {
        startActivity(Intent(requireContext(), activity))
    }

    inline fun <reified T : Activity> startCommonActivity() {
        startCommonActivity(T::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
        hideKeyBoard()
    }

    fun hideKeyBoard() {
        //隐藏软键盘
        (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            requireActivity().currentFocus?.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}