package com.afftechlimited.adultdatingapp

import android.app.Application
import android.content.Context
import com.mature.baselib.http.HttpManager
import com.mature.baselib.utils.ServerConfig

class KApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        ServerConfig.BASR_UEL = "https://std-friends.web.app"
        HttpManager.getInstance().init(ServerConfig.BASR_UEL)
    }
}