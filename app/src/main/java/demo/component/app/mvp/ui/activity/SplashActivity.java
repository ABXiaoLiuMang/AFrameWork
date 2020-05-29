
package demo.component.app.mvp.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.common.base.BaseActivity;
import com.common.di.component.AppComponent;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.webviewlib.InterWebListener;
import com.webviewlib.X5WebView;

import java.util.concurrent.TimeUnit;

import commonsdk.core.RouterHub;
import commonsdk.utils.Utils;
import demo.component.app.R;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * ================================================
 * SplashActivity
 * ================================================
 */
@Route(path = RouterHub.APP_SPLASHACTIVITY)
public class SplashActivity extends BaseActivity {

    private X5WebView mWebView;
    private ProgressBar pb;

    /**
     * 按钮回退
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mWebView.canGoBack() && event.getKeyCode() ==
                KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.clearHistory();
            ViewGroup parent = (ViewGroup) mWebView.getParent();
            if (parent != null) {
                parent.removeView(mWebView);
            }
            mWebView.destroy();
            //mWebView = null;
        }
        super.onDestroy();
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.getSettings().setJavaScriptEnabled(true);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mWebView != null) {
            mWebView.getSettings().setJavaScriptEnabled(false);
        }
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_splash;
    }

    @SuppressLint("CheckResult")
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        mWebView = findViewById(R.id.web_view);
        pb = findViewById(R.id.pb);

//        TBS主要通过共享使用微信手Q的内核而加载x5内核，您的手机是否没有内核源？
//        1. 手机安装微信，微信扫下面的二维码打开网页，如果显示的数字是00000说明您的手机微信没有安装内核，下面我们来给微信安装一个内核
//        2. 您的手机微信扫下面的二维码安装内核43906版本，安装完成后把您的App卸载重装下，看看是否可以加载x5内核了
        mWebView.loadUrl("http://soft.imtt.qq.com/browser/tes/feedback.html");
        mWebView.getX5WebChromeClient().setWebListener(interWebListener);
        mWebView.getX5WebViewClient().setWebListener(interWebListener);

    }

    private InterWebListener interWebListener = new InterWebListener() {
        @Override
        public void hindProgressBar() {
            pb.setVisibility(View.GONE);

            routerM();
        }

        @Override
        public void showErrorView() {

        }

        @Override
        public void startProgress(int newProgress) {
            pb.setProgress(newProgress);
        }
    };

    @SuppressLint("CheckResult")
    public void routerM() {
        Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Utils.navigation(SplashActivity.this, RouterHub.APP_MAINACTIVITY);
                        // 1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
//                        ARouter.getInstance().build(RouterHub.APP_MAINACTIVITY).navigation();
//                        Utils.navigation(SplashActivity.this, RouterHub.GANK_HOMEACTIVITY);
//                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                        startActivity(intent);
                        finish();
                    }
                });
    }
}
