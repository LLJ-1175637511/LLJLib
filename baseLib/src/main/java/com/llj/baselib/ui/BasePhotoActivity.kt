package com.llj.baselib.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.viewbinding.ViewBinding

abstract class BasePhotoActivity<VB : ViewBinding>(
    inflate: (inflater: LayoutInflater) -> VB
) : BaseActivity<VB>(inflate) {

    var uri: Uri? = null

    abstract fun toDo(): (() -> Unit)?

    val launchPhoto =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { ar ->
            if (ar.resultCode != Activity.RESULT_OK) return@registerForActivityResult
            uri = ar.data?.data ?: return@registerForActivityResult
            toDo()?.let { it() }
        }

    private val launchPhotoPermission =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { ar ->
            if (!(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && Environment.isExternalStorageManager())) {
                Toast.makeText(this, "存储权限获取失败", Toast.LENGTH_SHORT)
                finish()
                return@registerForActivityResult
            }
        }

    override fun initCreate() {
        super.initCreate()
        requestPermission()
    }

    /**
     * 选择手机相册图片
     */
    fun choosePhoto() {
        val galleryIntent = Intent(Intent.ACTION_GET_CONTENT)
        galleryIntent.addCategory(Intent.CATEGORY_OPENABLE)
        galleryIntent.type = "image/*"
        launchPhoto.launch(galleryIntent)
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) { //Android11（SDK版本30）
            // 先判断有没有权限
            if (!Environment.isExternalStorageManager()) { //判断是否获取到“允许管理所有文件”权限
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                intent.data = Uri.parse("package:" + getPackageName())
                launchPhotoPermission.launch(intent)
            }
        }
    }
}