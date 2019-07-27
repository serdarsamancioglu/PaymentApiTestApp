package com.serdar.mytestapp.base

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.serdar.mytestapp.BR
import com.serdar.mytestapp.R
import com.serdar.mytestapp.customview.CustomProgressDiaog
import com.serdar.mytestapp.interfaces.IViewState
import com.serdar.mytestapp.qrpayment.payment.QrPaymentFragment
import dagger.android.support.AndroidSupportInjection
import retrofit2.Retrofit
import javax.inject.Inject

abstract class BaseFragment<VM: ViewModel, DB: ViewDataBinding>: Fragment() {

    @Inject
    lateinit var retrofit: Retrofit

    protected var binding: DB? = null

    var progressDialog: CustomProgressDiaog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (getMViewModel() as BaseViewModel).onCreate(retrofit)
        (getMViewModel() as BaseViewModel).state.observe(this, Observer {state ->
            state?.let {
                onStateChanged(it)
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        }
        binding?.setVariable(BR.viewModel, getMViewModel())
        binding?.lifecycleOwner = this
        return binding?.root
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    @LayoutRes
    abstract fun getLayoutResId(): Int

    abstract fun getMViewModel(): VM

    abstract fun onStateChanged(state: IViewState)

    fun showProgress() {
        context?.let {
            if (progressDialog == null) {
                progressDialog = CustomProgressDiaog(it)
            }
            progressDialog?.show()
        }
    }

    fun hideProgress() {
        progressDialog?.dismiss()
    }

    fun replaceFragment(fragment: BaseFragment<*, *>) {
        val ft = fragmentManager?.beginTransaction()
        ft?.replace(R.id.container, fragment)?.commit()
    }
}