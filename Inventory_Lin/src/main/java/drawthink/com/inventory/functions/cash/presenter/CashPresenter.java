package drawthink.com.inventory.functions.cash.presenter;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.utils.SPUtils;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import drawthink.com.inventory.component.NetComponent;
import drawthink.com.inventory.data.lcoal.preference.PreferenceConst;
import drawthink.com.inventory.data.remote.func.ResponseTransformer;
import drawthink.com.inventory.data.remote.server.inventory.service.CashService;
import drawthink.com.inventory.data.remote.server.inventory.vo.shop.ShopSeller;
import drawthink.com.inventory.functions.BasePresenter;
import drawthink.com.inventory.functions.cash.contract.CashContract;
import rx.Subscription;

/**
 * <b>类名称：</b> CashPresenter <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年02月23日 9:12<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public class CashPresenter extends BasePresenter<CashContract.View> implements CashContract.Presenter {
    @Inject
    CashService cashService;

    @Inject
    @Named("app")
    SPUtils appSp;

    public CashPresenter(CashContract.View View) {
        super(View);
    }

    @Override
    public void injectNetComponent(NetComponent netComponent) {
        netComponent.inject(this);
    }




    @Override
    public void loadCashInfo(String shopId, String startDate, String endDate, String sellerId, String payType) {
        Subscription subscribe = cashService.getPayInfo(shopId,startDate,endDate,sellerId,payType)
                .compose(new ResponseTransformer<>())
                .subscribe(cashDate -> {
                    view.showCashInfo(cashDate);
                }, this::showError);
        compositeSubscription.add(subscribe);
    }

    @Override
    public void loadSaller(int shopId) {
        List<ShopSeller> shopSellers = JSON.parseArray(appSp.getString(PreferenceConst.SHOP_SELLER), ShopSeller.class);

        ShopSeller seller = new ShopSeller();
        seller.setId(0);
        seller.setName("全部");
        shopSellers.add(0,seller);

        view.showSaler(shopSellers);

    }


}

