package drawthink.com.inventory.functions.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.ButterKnife;
import drawthink.com.inventory.R;
import drawthink.com.inventory.component.AppComponent;
import drawthink.com.inventory.data.lcoal.preference.UserSpUtils;
import drawthink.com.inventory.data.remote.server.inventory.vo.login.User;
import drawthink.com.inventory.functions.BaseActivity;
import drawthink.com.inventory.functions.IBasePresenter;
import drawthink.com.inventory.functions.login.LoginActivity;
import drawthink.com.inventory.functions.main.MainActivity;
import drawthink.com.inventory.functions.splash.contract.SplashContract;
import drawthink.com.inventory.functions.splash.presenter.SplashPresenter;
import drawthink.com.inventory.utils.JumpUtil;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class SplashActivity extends BaseActivity implements SplashContract.View {

    SplashContract.Presenter presenter;

    @Inject
    UserSpUtils userSp;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash, false);
        ButterKnife.bind(this);
        if (userSp.hasUserId()) {
            presenter.autoLogin(userSp.getUserMobile());
        } else {
            Subscription splash = Observable.just("splash")
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .delay(2, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(s -> {
                        startActivity(new Intent(this, LoginActivity.class));
                        finish();
                    });
            compositeSubscription.add(splash);
        }
    }

    @Override
    public IBasePresenter initPresenter() {
        presenter = new SplashPresenter(this);
        return presenter;
    }


    @Override
    public String setTitle() {
        return "";
    }

    @Override
    public void injectAppComponent(AppComponent appComponent) {
        appComponent.inject(this);
    }


    @Override
    public void isFristComing(User user) {

        userSp.saveUser(user);

        JumpUtil jumpUtil = new JumpUtil(this, MainActivity.class);
        jumpUtil.start();
        finish();

    }

    @Override
    public void startLogin() {
        new JumpUtil(this, MainActivity.class).start();
    }

}
