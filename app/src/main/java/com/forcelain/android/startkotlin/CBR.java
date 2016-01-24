package com.forcelain.android.startkotlin;


import com.forcelain.android.startkotlin.models.ValCurs;

import retrofit2.http.GET;
import rx.Observable;

public interface CBR {

    @GET("/scripts/XML_daily.asp")
    Observable<ValCurs> getDaily();
}
