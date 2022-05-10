package com.llj.baselib.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.viewbinding.ViewBinding

open class BaseActivity<VB : ViewBinding>(private val inflate: (inflater: LayoutInflater) -> VB) :
    AppCompatActivity() {

    private var _mBinding: VB?= null
    var mBinding = _mBinding!!

    open fun initCreate() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = inflate.invoke(layoutInflater)
        setContentView(mBinding.root)
        initCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
        System.gc()
    }

    fun <T : Activity> startCommonActivity(activity: Class<T>) {
        startActivity(Intent(this, activity))
    }

    inline fun <reified T : Activity> startCommonActivity() {
        startCommonActivity(T::class.java)
    }

    fun <T : Activity> startActivityAndFinish(activity: Class<T>) {
        startActivity(Intent(this, activity))
        finish()
    }

    inline fun <reified T : Activity> startActivityAndFinish() {
        startCommonActivity(T::class.java)
        finish()
    }

    fun hideKeyBoard(){
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            currentFocus?.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    fun showDialog(df: DialogFragment, tag: String) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        val prev: Fragment? = supportFragmentManager.findFragmentByTag(tag)
        if (prev != null) {
            ft.remove(prev)
        }
        ft.addToBackStack(null)
        df.show(ft, tag)
    }

}