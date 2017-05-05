package drawthink.com.inventory.functions.stock.presenter;

import javax.inject.Inject;

import drawthink.com.inventory.component.NetComponent;
import drawthink.com.inventory.data.remote.func.ResponseTransformer;
import drawthink.com.inventory.data.remote.server.inventory.service.StockService;
import drawthink.com.inventory.functions.BasePresenter;
import drawthink.com.inventory.functions.stock.contract.StockContract;
import rx.Subscription;

/**
 * <b>类名称：</b> StockPresenter <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年03月06日 11:37<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public class StockPresenter extends BasePresenter<StockContract.View> implements StockContract.Presenter {
    public StockPresenter(StockContract.View View) {
        super(View);
    }

    @Override
    public void injectNetComponent(NetComponent netComponent) {
        netComponent.inject(this);
    }

    @Inject
    StockService stockService;


    @Override
    public void loadStockInfo(int shopId,String startDate , String endDate , String productCode ,String productName) {
        Subscription subscribe = stockService.getShopStock(shopId, startDate, endDate, productCode, productName)
                .compose(new ResponseTransformer<>())
                .subscribe(shopStockInfo -> {
                    view.showStockInfo(shopStockInfo);
                }, this::showError);
        compositeSubscription.add(subscribe);


    }
}
