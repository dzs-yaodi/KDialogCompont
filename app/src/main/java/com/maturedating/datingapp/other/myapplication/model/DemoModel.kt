package com.example.lanyixin.myapplication.model

import com.example.lanyixin.myapplication.api.RetrofitManager
import com.kotlinmvp.rx.scheduler.SchedulerUtils
import com.maturedating.datingapp.other.myapplication.api.ResultBase
import java.util.*
import kotlin.collections.ArrayList
import io.reactivex.Observable

class DemoModel {

    /**
     * 请求数据
     */
    fun requestHotWordData(): Observable<ResultBase<List<String>>> {
        return RetrofitManager.service.getHotsearch()
                .compose(SchedulerUtils.ioToMain())
    }

}
