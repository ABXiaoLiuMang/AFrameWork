package com.allen.kotlinapp.data

import demo.test2.mvp.model.entity.Status
import java.util.*

/**
 * 文 件 名: DataServer
 */
open class DataServer private constructor() {

    companion object {

        fun getSampleData(lenth: Int): List<Status> {
            val list = ArrayList<Status>()
            for (i in 0..lenth - 1) {
                val status = Status()
                status.userName = ("Chad" + i)
                status.createdAt = ("04/05/" + i)
                status.isRetweet = (i % 2 == 0)
                status.userAvatar = ("avatars")
                status.text = ("BaseRecyclerViewAdpaterHelper")
                list.add(status)
            }
            return list
        }

    }


}
