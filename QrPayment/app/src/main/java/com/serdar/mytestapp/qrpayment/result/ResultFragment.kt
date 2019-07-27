package com.serdar.mytestapp.qrpayment.result

import android.os.Bundle
import android.view.View
import com.serdar.mytestapp.R
import com.serdar.mytestapp.base.BaseFragment
import com.serdar.mytestapp.databinding.FragmentSuccessBinding
import com.serdar.mytestapp.interfaces.IViewState
import com.serdar.mytestapp.qrpayment.payment.QrPaymentFragment
import kotlinx.android.synthetic.main.fragment_success.*
import javax.inject.Inject

class ResultFragment: BaseFragment<ResultViewModel, FragmentSuccessBinding>() {

    @Inject
    lateinit var viewModel: ResultViewModel

    private var posId = ""

    companion object {
        fun newInstance(posId: String): ResultFragment {
            val fragment = ResultFragment()
            fragment.posId = posId
            return fragment
        }
    }

    override fun getLayoutResId(): Int = R.layout.fragment_success

    override fun getMViewModel() = viewModel

    override fun onStateChanged(state: IViewState) {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnNewPayment.setOnClickListener {
            replaceFragment(QrPaymentFragment())
        }
        textPosId.text = posId
    }
}