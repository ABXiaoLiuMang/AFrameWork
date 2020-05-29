package demo.component.app.app;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * ================================================
 * 声明 {@link ARouter} 拦截器, 可以根据需求自定义拦截逻辑, 比如用户没有登录就拦截其他页面
 * ================================================
 */
@Interceptor(priority = 8, name = "RouterInterceptor")
public class RouterInterceptor implements IInterceptor {
    private Context mContext;

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        callback.onContinue(postcard);
    }

    @Override
    public void init(Context context) {
        mContext = context;
    }
}
