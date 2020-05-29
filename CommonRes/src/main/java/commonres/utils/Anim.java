
package commonres.utils;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import commonres.R;


/**
 * ================================================
 * 动画工具类
 * ================================================
 */
public class Anim {
    public static void exit(Activity obj) {
        obj.overridePendingTransition(R.anim.public_translate_left_to_center, R.anim.public_translate_center_to_right);
    }

    public static void in(Activity obj) {
        obj.overridePendingTransition(R.anim.public_translate_right_to_center, R.anim.public_translate_center_to_left);
    }

    public static void cleanAnim(ImageView animView) {
        if (animView == null)
            return;
        animView.setImageResource(0);
        animView.clearAnimation();
        animView.setVisibility(View.GONE);
    }
}
