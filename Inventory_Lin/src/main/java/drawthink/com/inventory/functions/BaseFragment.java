package drawthink.com.inventory.functions;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.utils.ToastUtils;

import drawthink.com.inventory.InventoryApplication;
import drawthink.com.inventory.component.AppComponent;
import drawthink.com.inventory.widget.WaitScreen;
import rx.subscriptions.CompositeSubscription;

/**
 * <b>类名称：</b> BaseFragment <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年09月19日 下午3:40<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public abstract class BaseFragment extends Fragment {

    private WaitScreen waitScreen;

    private IBasePresenter basePresenter;
    protected CompositeSubscription compositeSubscription;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compositeSubscription = new CompositeSubscription();
        injectAppComponent(InventoryApplication.get(getContext()).getAppComponent());
        waitScreen = new WaitScreen(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(bindLayout(),
                container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView(view);
        basePresenter = initPresenter();
        afterViews();
    }

    public abstract int bindLayout();

    public abstract void bindView(View view);
    
    protected abstract void afterViews();


    public abstract IBasePresenter initPresenter();


    /**
     * Toast message.
     *
     * @param message the message
     */
    public void toast(String message) {
        ToastUtils.showLongToast(message);
    }

    /**
     * Show wait.
     */
    public void showWait() {
        new Handler().postDelayed(() -> {
            waitScreen = new WaitScreen(getActivity());
            waitScreen.show();
        }, 300);
    }

    /**
     * Show wait.
     *
     * @param message the message
     */
    public void showWait(String message) {
        new Handler().postDelayed(() -> {
            waitScreen = new WaitScreen(getActivity());
            waitScreen.show(message);
        }, 300);
    }

    /**
     * Hide wait.
     */
    public void hideWait(WaitScreen.OnAnimationEnd onAnimationEnd) {
        new Handler().postDelayed(() ->
                waitScreen.close(onAnimationEnd), 300);
    }

    /**
     * Close wait.
     */
    public void closeWait() {
        waitScreen.dismiss();
    }

    public abstract void injectAppComponent(AppComponent appComponent) ;

    @Override
    public void onDestroy() {
        if(basePresenter != null){
            basePresenter.onDestroy();
        }
        compositeSubscription.unsubscribe();
        super.onDestroy();
    }
}
