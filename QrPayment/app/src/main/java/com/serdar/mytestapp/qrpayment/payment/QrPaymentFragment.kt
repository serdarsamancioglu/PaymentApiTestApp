package com.serdar.mytestapp.qrpayment.payment

import com.serdar.mytestapp.R
import com.serdar.mytestapp.base.BaseFragment
import com.serdar.mytestapp.databinding.FragmentQrCodeBinding
import com.serdar.mytestapp.interfaces.IViewState
import com.serdar.mytestapp.qrpayment.result.ResultFragment
import kotlinx.android.synthetic.main.fragment_qr_code.*
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class QrPaymentFragment : BaseFragment<QrPaymentViewModel, FragmentQrCodeBinding>() {

    @Inject
    lateinit var viewModel: QrPaymentViewModel

    override fun getLayoutResId(): Int = R.layout.fragment_qr_code

    override fun getMViewModel() = viewModel

    override fun onStateChanged(state: IViewState) {
        when (state) {
            is QrPaymentVS.Validate -> {
                validatePage()
            }
            is QrPaymentVS.ShowProgress -> {
                if (state.show) showProgress() else hideProgress()
            }
            is QrPaymentVS.PaymentSuccess -> {
                replaceFragment(ResultFragment.newInstance(state.response.posID))
            }
        }
    }

    fun validatePage() {
        val amount = inputAmount.text.toString().toDoubleOrNull()
        var isValid = amount != null && amount > 0
        if (isValid) {
            showProgress()
            viewModel.getQrSale()
        }
        else {
            inputAmount.requestFocus()
            inputAmount.error = getString(R.string.invalid_amount)
        }
    }
}