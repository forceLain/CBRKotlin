package com.forcelain.android.startkotlin

import com.forcelain.android.startkotlin.models.Valute

fun Valute.findFlagRes(): Int {
    when (charCode) {
        "RON" -> return R.drawable.flag_ro
        "BRL" -> return R.drawable.flag_br
        "AMD" -> return R.drawable.flag_am
        "USD" -> return R.drawable.flag_us
        "AUD" -> return R.drawable.flag_au
        "AZN" -> return R.drawable.flag_az
        "GBP" -> return R.drawable.flag_gb
        "BYR" -> return R.drawable.flag_by
        "BGN" -> return R.drawable.flag_bg
        "HUF" -> return R.drawable.flag_hu
        "DKK" -> return R.drawable.flag_dk
        "EUR" -> return R.drawable.flag_euro
        "INR" -> return R.drawable.flag_in
        "KZT" -> return R.drawable.flag_kz
        "CAD" -> return R.drawable.flag_ca
        "KGS" -> return R.drawable.flag_kg
        "CNY" -> return R.drawable.flag_cn
        "MDL" -> return R.drawable.flag_md
        "NOK" -> return R.drawable.flag_no
        "NOK" -> return R.drawable.flag_no
        "PLN" -> return R.drawable.flag_pl
        "SGD" -> return R.drawable.flag_sg
        "TJS" -> return R.drawable.flag_tj
        "TRY" -> return R.drawable.flag_tr
        "TMT" -> return R.drawable.flag_tm
        "UZS" -> return R.drawable.flag_uz
        "UAH" -> return R.drawable.flag_ua
        "CZK" -> return R.drawable.flag_cz
        "SEK" -> return R.drawable.flag_se
        "CHF" -> return R.drawable.flag_ch
        "ZAR" -> return R.drawable.flag_za
        "KRW" -> return R.drawable.flag_kr
        "JPY" -> return R.drawable.flag_jp
        else -> return 0;
    }
}
