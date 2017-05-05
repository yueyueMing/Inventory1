package drawthink.com.inventory.functions.login.presenter;

import javax.inject.Inject;

import drawthink.com.inventory.component.NetComponent;
import drawthink.com.inventory.data.lcoal.preference.UserSpUtils;
import drawthink.com.inventory.data.remote.func.ResponseTransformer;
import drawthink.com.inventory.data.remote.server.inventory.service.OtherService;
import drawthink.com.inventory.data.remote.server.inventory.service.UserService;
import drawthink.com.inventory.functions.BasePresenter;
import drawthink.com.inventory.functions.login.contract.LoginContract;
import rx.Subscription;

/**
 * <b>类名称：</b> LoginPresenter <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年02月23日 9:09<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public class LoginPresenter extends BasePresenter<LoginContract.View>
        implements LoginContract.Presenter {

    @Inject
    OtherService otherService;

    @Inject
    UserService userService;

    @Inject
    UserSpUtils userSp;

    public LoginPresenter(LoginContract.View View) {
        super(View);
    }

    @Override
    public void injectNetComponent(NetComponent netComponent) {
        netComponent.inject(this);
    }

    @Override
    public void getValidCode(String mobile) {
        view.showWait("正在获取验证码,请稍候……");
        Subscription subscribe = otherService.getValidCode(mobile)
                .compose(new ResponseTransformer<>())
                .subscribe(aVoid -> {
                }, throwable -> {
                    showError(throwable);
                    view.hideWait(null);
                }, () -> view.hideWait(null));
        compositeSubscription.add(subscribe);
    }


    @Override
    public void login(String mobile, String validCode) {
        view.showWait("正在登陆，请稍候……");
        Subscription subscribe = userService.login(mobile, validCode)
                .compose(new ResponseTransformer<>())
                .subscribe(user -> userSp.saveUser(user)
                        , throwable -> {
                            showError(throwable);
                            view.hideWait(null);
                        }
                        , () -> {
                            view.loginComplete();
//                            view.hideWait(null);
                        });
        compositeSubscription.add(subscribe);
    }
}
