/*
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package demo.component.app.mvp.ui.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.common.base.BaseActivity;
import com.common.di.component.AppComponent;
import com.common.utils.CommonUtils;
import com.qmuiteam.qmui.widget.QMUITopBar;

import commonsdk.core.RouterHub;
import commonsdk.utils.Utils;
import commonservice.test.service.GankInfoService;
import commonservice.test.service.KTInfoService;
import demo.component.app.R;
import kotlin.jvm.JvmField;

/**
 * ================================================
 * 宿主 App 的主页
 * ================================================
 */
@Route(path = RouterHub.APP_MAINACTIVITY)
public class MainActivity extends BaseActivity {

    @Autowired(name = RouterHub.JAVA_SERVICE_GANKINFOSERVICE)
    @JvmField
    GankInfoService mGankInfoService;

    @Autowired(name = RouterHub.KOTLIN_SERVICE_KTINFOSERVICE)
    @JvmField
    KTInfoService ktInfoService;

    private long mPressedTime;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {

        setSwipeBackEnable(false);

        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        ARouter.getInstance().inject(this);
        //这里想展示组件向外提供服务的功能, 模拟下组件向宿主提供一些必要的信息, 这里为了简单就直接返回本地数据不请求网络了
        loadInfoJav();
        loadInfoKT();
    }



    private void loadInfoJav() {
        //当非集成调试阶段, 宿主 App 由于没有依赖其他组件, 所以使用不了对应组件提供的服务
        if (mGankInfoService == null) {
            findViewById(R.id.bt_java).setEnabled(false);
            return;
        }
        ((Button) findViewById(R.id.bt_java)).setText(mGankInfoService.getInfo().getName());
        ((Button) findViewById(R.id.bt_java)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.navigation(MainActivity.this, RouterHub.JAVA_HOMEACTIVITY);
            }
        });
    }

    private void loadInfoKT() {
        //当非集成调试阶段, 宿主 App 由于没有依赖其他组件, 所以使用不了对应组件提供的服务
        if (ktInfoService == null) {
            findViewById(R.id.bt_kotlin).setEnabled(false);
            return;
        }
        ((Button) findViewById(R.id.bt_kotlin)).setText(ktInfoService.getInfo().getName());
        ((Button) findViewById(R.id.bt_kotlin)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.navigation(MainActivity.this, RouterHub.KOTLIN_DEMOACTIVITY);
            }
        });
    }

    @Override
    public void onBackPressedSupport() {
        //获取第一次按键时间
        long mNowTime = System.currentTimeMillis();
        //比较两次按键时间差
        if ((mNowTime - mPressedTime) > 2000) {
            CommonUtils.makeText(getApplicationContext(),
                    "再按一次退出" + CommonUtils.getString(getApplicationContext(), R.string.public_app_name));
            mPressedTime = mNowTime;
        } else {
            super.onBackPressedSupport();
        }
    }


}
