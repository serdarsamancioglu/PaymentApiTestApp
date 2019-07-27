package com.serdar.mytestapp.customview

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import com.serdar.mytestapp.R

class CustomProgressDiaog: Dialog {

    constructor(context: Context): super(context, android.R.style.Theme_Translucent) {
        init()
    }

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
    }

    private fun init() {
        val progressView = LayoutInflater.from(context).inflate(R.layout.dialog_progress, null)
        setContentView(progressView)
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
    }
}