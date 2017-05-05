package drawthink.com.inventory.functions.sell.Presenter;

import javax.inject.Inject;

import drawthink.com.inventory.component.NetComponent;
import drawthink.com.inventory.data.remote.func.ResponseTransformer;
import drawthink.com.inventory.data.remote.server.inventory.service.PayService;
import drawthink.com.inventory.data.remote.server.inventory.service.ProductService;
import drawthink.com.inventory.data.remote.server.inventory.service.SellerService;
import drawthink.com.inventory.data.remote.server.inventory.vo.pay.PayForm;
import drawthink.com.inventory.data.remote.server.inventory.vo.pay.PayInfo;
import drawthink.com.inventory.functions.BasePresenter;
import drawthink.com.inventory.functions.sell.contract.SellDetailContract;
import rx.Subscriber;
import rx.Subscription;

/**
 * <b>类名称：</b> SellDetailPresenter <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年03月03日 16:07<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public class SellDetailPresenter
        extends BasePresenter<SellDetailContract.View>
        implements SellDetailContract.Presenter {

    @Inject
    ProductService productService;
    @Inject
    SellerService sellerService;
    @Inject
    PayService payService;

    public SellDetailPresenter(SellDetailContract.View View) {
        super(View);
    }


    @Override
    public void injectNetComponent(NetComponent netComponent) {
        netComponent.inject(this);

    }



    @Override
    public void loadProductDetails(int productId) {
        view.showWait();
        Subscription subscribe = productService.getProductDetail(productId)
                .compose(new ResponseTransformer<>())
                .subscribe(productDetail -> {
                    view.showProductDetails(productDetail);
                },throwable -> {
                    showError(throwable);
                    view.hideWait(null);
                },()->{view.hideWait(null);});
        compositeSubscription.add(subscribe);

    }

    @Override
    public void loadSeller(String shopId) {
        Subscription subscribe =sellerService.getSeller(shopId)
                .compose(new ResponseTransformer<>())
                .subscribe(sellers -> {
                    view.showSeller(sellers);
                }, this::showError);
        compositeSubscription.add(subscribe);
    }

    @Override
    public void pay(PayForm form) {
        view.showWait("正在记录出售信息，请稍候...");
        payService.pay(form)
                .compose(new ResponseTransformer<>())
                .subscribe(new Subscriber<PayInfo>() {
                    @Override
                    public void onCompleted() {
                        view.toast("销售记录成功！");
                    }

                    @Override
                    public void onError(Throwable e) {
                        showError(e);
                        view.hideWait(null);
                    }

                    @Override
                    public void onNext(PayInfo payInfo) {
                        view.hideWait(null);
                    }
                });
    }
}
