package drawthink.com.inventory.functions.cash.presenter;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.utils.SPUtils;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import drawthink.com.inventory.component.NetComponent;
import drawthink.com.inventory.data.lcoal.preference.PreferenceConst;
import drawthink.com.inventory.data.lcoal.preference.UserSpUtils;
import drawthink.com.inventory.data.remote.func.ResponseTransformer;
import drawthink.com.inventory.data.remote.server.inventory.service.CashService;
import drawthink.com.inventory.data.remote.server.inventory.service.ShopService;
import drawthink.com.inventory.data.remote.server.inventory.service.form.CashForm;
import drawthink.com.inventory.data.remote.server.inventory.vo.shop.ShopSeller;
import drawthink.com.inventory.functions.BasePresenter;
import drawthink.com.inventory.functions.cash.contract.AddCashContract;
import rx.Subscriber;
import rx.Subscription;

/**
 * <b>类名称：</b> AddCashPresenter <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年03月06日 14:08<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public class AddCashPresenter
        extends BasePresenter<AddCashContract.View>
        implements AddCashContract.Presenter {
    @Inject
    CashService cashService;
    @Inject
    ShopService shopService;

    @Inject
    @Named("app")
    SPUtils appSp;

    @Inject
    UserSpUtils userSpUtils;

    public AddCashPresenter(AddCashContract.View View) {
        super(View);
    }

    @Override
    public void injectNetComponent(NetComponent netComponent) {
        netComponent.inject(this);

    }

    //销售员初始化

    @Override
    public void loadSeller(int shopId) {
        view.showSaler(JSON.parseArray(appSp.getString(PreferenceConst.SHOP_SELLER), ShopSeller.class));
    }

    @Override
    public void loadMoney(String date) {
        Subscription subscribe = cashService.calcMoney(date)
                .compose(new ResponseTransformer<>())
                .subscribe(calcCash -> {
                    view.showSellMoney(calcCash);
                }, this::showError);
        compositeSubscription.add(subscribe);
    }

    @Override
    public void addCash(CashForm cashForm) {
        cashForm.setUserName(userSpUtils.getUserName());
        cashService.addCash(cashForm)
                .compose(new ResponseTransformer<>())
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                        view.finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showError(e);
                    }

                    @Override
                    public void onNext(Void avoid) {

                    }
                });
    }





}
