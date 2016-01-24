package com.forcelain.android.startkotlin

import android.app.Application
import retrofit2.Retrofit
import retrofit2.RxJavaCallAdapterFactory
import retrofit2.SimpleXmlConverterFactory

class App : Application() {

    public var service: CBR? = null

    override fun onCreate() {
        super.onCreate()
        service = Retrofit.Builder()
                .baseUrl("http://www.cbr.ru")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build()
                .create(CBR::class.java);
    }
}
