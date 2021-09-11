package com.kotlinmvp.mvp.contract

import com.kotlinmvp.base.IBaseView
import com.kotlinmvp.base.IPresenter
import com.afftechlimited.adultdatingapp.other.myapplication.api.ResultBase


/**
 * 契约类
 */
interface DemoContract {

    interface View : IBaseView {

        fun showError(errorMsg: String,errorCode:Int)

        /**
         * 获取数据
         */
        fun setHotWordData(string: ResultBase<List<String>>)
    }


    interface Presenter : IPresenter<View> {
        /**
         * 显示数据
         */
        fun requestHotWordData()

    }
}