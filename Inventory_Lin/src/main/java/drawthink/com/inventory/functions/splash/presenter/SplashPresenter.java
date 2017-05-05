package drawthink.com.inventory.functions.splash.presenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import drawthink.com.inventory.component.NetComponent;
import drawthink.com.inventory.data.remote.func.ResponseTransformer;
import drawthink.com.inventory.data.remote.server.inventory.service.ShopService;
import drawthink.com.inventory.data.remote.server.inventory.service.UserService;
import drawthink.com.inventory.functions.BasePresenter;
import drawthink.com.inventory.functions.splash.contract.SplashContract;
import rx.Subscription;

public class SplashPresenter
        extends BasePresenter<SplashContract.View>
        implements SplashContract.Presenter {

    @Inject
    UserService userService;

@Inject
    ShopService shopService;
    public SplashPresenter(SplashContract.View view) {
        super(view);
    }

    @Override
    public void injectNetComponent(NetComponent netComponent) {
        netComponent.inject(this);
    }


    @Override
    public void autoLogin(String mobile) {
        Subscription subscribe = userService.splashDate(mobile)
                .delay(2, TimeUnit.SECONDS)
                .compose(new ResponseTransformer<>())
                .subscribe(user -> {
                    view.isFristComing(user);
                }, throwable -> {
                    showError(throwable);
                    view.startLogin();
                });
        compositeSubscription.add(subscribe);
    }

//    @Override
//    public void loadSeller(String shopId) {
//        Subscription subscribe = shopService.getShopForSaler(shopId).compose(new ResponseTransformer<>())
//                .subscribe(shopSellers -> {
//                    view.showSaler(shopSellers);
//
//                }, this::showError);
//        compositeSubscription.add(subscribe);
//    }


}
