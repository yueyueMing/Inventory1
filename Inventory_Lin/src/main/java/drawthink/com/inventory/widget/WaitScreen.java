package drawthink.com.inventory.widget;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.utils.ScreenUtils;

import java.util.concurrent.TimeUnit;

import drawthink.com.inventory.R;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * <b>类名称：</b> WaitScreen <br/>
 * <b>类描述：</b> 等待界面<br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 16-9-28 下午5:35<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class WaitScreen {
    private PopupWindow popupWindow;
    private Context context;
    private View view;
    private final View progress;
    private final View wait;
    private final TextView messageView;

    public WaitScreen(Activity context) {
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.weight_wait_screen, null);
        popupWindow = new PopupWindow(view, ScreenUtils.getScreenWidth(), ScreenUtils.getScreenHeight());
        //sdk > 21 解决 标题栏没有办法遮罩的问题
        popupWindow.setClippingEnabled(false);
        progress = view.findViewById(R.id.progress);
        wait = view.findViewById(R.id.wait);
        messageView = (TextView) view.findViewById(R.id.message);
    }

    /**
     * 弹出等待提示框
     */
    public PopupWindow show() {
        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, 0, 0);
        Animation rotateAnim = AnimationUtils.loadAnimation(context, R.anim.rotate_progress);
        progress.startAnimation(rotateAnim);
        return popupWindow;
    }

    /**
     * 弹出等待提示框
     */
    public PopupWindow show(String message) {
        popupWindow.showAsDropDown(view);
        Animation rotateAnim = AnimationUtils.loadAnimation(context, R.anim.rotate_progress);
        progress.startAnimation(rotateAnim);
        messageView.setText(message);
        return popupWindow;
    }

    /**
     * 以动画的方式关闭等待弹屏
     */
    public void close(OnAnimationEnd onAnimationEnd) {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.setFocusable(false);
            Animation alphaAnim = AnimationUtils.loadAnimation(context, R.anim.alpha_hide_progress);
            wait.startAnimation(alphaAnim);
            Observable.timer(alphaAnim.getDuration(), TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(aLong -> {
                        popupWindow.dismiss();
                        if(onAnimationEnd != null) {
                            onAnimationEnd.animationEnd();
                        }
                    });
        }
    }

    /**
     * 关闭弹屏
     */
    public void dismiss() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }

    public interface OnAnimationEnd {
        void animationEnd();
    }
}

