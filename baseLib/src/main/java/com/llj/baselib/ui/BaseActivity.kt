package com.llj.baselib.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding>(private val param: (inflater: LayoutInflater) -> VB) :
    AppCompatActivity() {

    lateinit var mBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = param.invoke(layoutInflater)
        setContentView(mBinding.root)
    }

}