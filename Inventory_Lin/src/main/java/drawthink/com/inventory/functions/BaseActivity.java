package drawthink.com.inventory.functions;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.ConvertUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import drawthink.com.inventory.InventoryApplication;
import drawthink.com.inventory.R;
import drawthink.com.inventory.component.AppComponent;
import drawthink.com.inventory.widget.WaitScreen;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * <b>类名称：</b> BaseActivity <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年09月19日 下午3:02<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static final int BASE_LAYOUT_ID = R.layout.activity_base;

    /**
     * The Toolbar.
     */
    Toolbar toolbar;
    /**
     * The Content.
     */
    LinearLayout content;

    /**
     * The Tool bar title.
     */
    TextView toolBarTitle;

    private WaitScreen waitScreen;

    IBasePresenter basePresenter;
    protected CompositeSubscription compositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compositeSubscription = new CompositeSubscription();
        injectAppComponent(InventoryApplication.get(this).getAppComponent());
        setContentView(BASE_LAYOUT_ID);
        basePresenter = initPresenter();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        this.setContentView(layoutResID, true);
    }


    /**
     * Set content view.
     *
     * @param layoutResID the layout res id
     * @param hasToolbar  the has toolbar
     */
    public void setContentView(@LayoutRes int layoutResID, boolean hasToolbar) {
        if (BASE_LAYOUT_ID == layoutResID) {
            super.setContentView(layoutResID);
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            content = (LinearLayout) findViewById(R.id.content);
            toolBarTitle = (TextView) findViewById(R.id.toolbar_title);
        } else {
            getLayoutInflater().inflate(layoutResID, content, true);
        }
        initToolbar();
        if (!hasToolbar) {
            toolbar.setVisibility(View.GONE);
        }
    }

    /**
     * Init toolbar.
     */
    protected void initToolbar() {
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolBarTitle.setText(setTitle());
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    /**
     * Hide navigation.
     */
    public void hideNavigation() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    /**
     * Gets tool bar.
     *
     * @return the tool bar
     */
    public Toolbar getToolbar() {
        return toolbar;
    }

    /**
     * Hide toolbar.
     */
    public void hideToolbar() {
        toolbar.setVisibility(View.GONE);
    }


    /**
     * Sets title.
     *
     * @return the title
     */
    public abstract String setTitle();

    public abstract IBasePresenter initPresenter();

    /**
     * Inject app component.
     *
     * @param appComponent the app component
     */
    public abstract void injectAppComponent(AppComponent appComponent);

    /**
     * Set on title click.
     *
     * @param resource the resource
     * @param action1  the action 1
     */
    public void setOnTitleClick(@DrawableRes int resource, Action1<Void> action1) {
        Drawable drawable = getResources().getDrawable(resource);
        int px = ConvertUtils.dp2px(15);
        drawable.setBounds(0, 0, px, px);
        toolBarTitle.setCompoundDrawables(null, null, drawable, null);
        RxView.clicks(toolBarTitle)
                .throttleLast(200, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }

    public void setTitle(String title) {
        toolBarTitle.setText(title);
    }

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
            waitScreen = new WaitScreen(this);
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
            waitScreen = new WaitScreen(this);
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

    @Override
    protected void onDestroy() {
        if(basePresenter != null){
            basePresenter.onDestroy();
        }
        compositeSubscription.unsubscribe();
        super.onDestroy();
    }
}
