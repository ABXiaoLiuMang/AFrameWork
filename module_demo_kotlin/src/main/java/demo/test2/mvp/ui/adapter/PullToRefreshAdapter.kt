package demo.test2.mvp.ui.adapter

import com.allen.kotlinapp.data.DataServer
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import demo.R
import demo.test2.mvp.model.entity.Status

/**
 * 文 件 名: PullToRefreshAdapter
 */
class PullToRefreshAdapter : BaseQuickAdapter<Status, BaseViewHolder>(R.layout.layout_animation, DataServer.getSampleData(10)) {

    override fun convert(helper: BaseViewHolder, item: Status) {
        helper.setImageResource(R.id.img, R.mipmap.gank_ic_logo)
        helper.setText(R.id.tweetName, "Hoteis")
    }

}
