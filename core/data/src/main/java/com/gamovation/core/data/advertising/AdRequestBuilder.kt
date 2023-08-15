package com.gamovation.core.data.advertising

import com.google.android.gms.ads.AdRequest


class AdRequestBuilder {
    private val requestBuilder = AdRequest.Builder()


    fun createDefault(): AdRequest {
        return requestBuilder.build()
    }
}