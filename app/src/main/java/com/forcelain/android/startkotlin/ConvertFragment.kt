package com.forcelain.android.startkotlin


import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import kotlinx.android.synthetic.main.fragment_convert.*


class ConvertFragment : DialogFragment() {

    var amount: Int = 1
    var value: Double = 1.0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_convert, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog? {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val amountArg = arguments.getString(ARG_NOMINAL)
        val valueArg = arguments.getString(ARG_VALUE)
        image.setImageResource(arguments.getInt(ARG_IMAGE_RES_ID))
        fromLabel.text = arguments.getString(ARG_CODE)
        amount = amountArg.toInt()
        value = valueArg.replace(",", ".").toDouble()
        convert_from.editableText.append(amount.toString())
        converted.setText(value.toString())
        convert_from.addTextChangedListener(amountTextWatcher)
        converted.addTextChangedListener(valueTextWatcher)
    }

    val amountTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (s != null && s.length > 0) {
                val anAmount = s.toString().toInt()
                val aValue = anAmount / amount * value
                converted.removeTextChangedListener(valueTextWatcher)
                converted.setText(aValue.toString())
                converted.addTextChangedListener(valueTextWatcher)
            } else {
                converted.removeTextChangedListener(valueTextWatcher)
                converted.setText("")
                converted.addTextChangedListener(valueTextWatcher)
            }
        }
    }

    val valueTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (s != null && s.length > 0) {
                val aValue = s.toString().toDouble()
                val anAmount = aValue / value * amount
                convert_from.removeTextChangedListener(amountTextWatcher)
                convert_from.setText(anAmount.toString())
                convert_from.addTextChangedListener(amountTextWatcher)
            } else {
                convert_from.removeTextChangedListener(amountTextWatcher)
                convert_from.setText("")
                convert_from.addTextChangedListener(amountTextWatcher)
            }
        }
    }

    companion object {

        const private val ARG_IMAGE_RES_ID = "ARG_IMAGE_RES_ID"
        const private val ARG_NOMINAL = "ARG_NOMINAL"
        const private val ARG_VALUE = "ARG_VALUE"
        const private val ARG_CODE = "ARG_CODE"

        fun create(nominal: String, value: String, imageResId: Int, valuteCode: String): ConvertFragment {
            val fragment = ConvertFragment()
            val arg = Bundle()
            arg.putString(ARG_NOMINAL, nominal)
            arg.putString(ARG_VALUE, value)
            arg.putString(ARG_CODE, valuteCode)
            arg.putInt(ARG_IMAGE_RES_ID, imageResId)
            fragment.arguments = arg
            return fragment
        }
    }

}
