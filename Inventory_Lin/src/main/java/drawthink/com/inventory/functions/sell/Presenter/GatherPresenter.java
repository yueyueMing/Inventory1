package drawthink.com.inventory.functions.sell.Presenter;

import javax.inject.Inject;

import drawthink.com.inventory.component.NetComponent;
import drawthink.com.inventory.data.remote.func.ResponseTransformer;

import drawthink.com.inventory.data.remote.server.inventory.service.PayService;
import drawthink.com.inventory.data.remote.server.inventory.vo.pay.PayForm;
import drawthink.com.inventory.data.remote.server.inventory.vo.pay.PayInfo;
import drawthink.com.inventory.functions.BasePresenter;
import drawthink.com.inventory.functions.sell.contract.GatherContract;
import rx.Subscriber;
import rx.Subscription;

/**
 * <b>类名称：</b> GatherPresenter<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b> 马文礼 <br/>
 * <b>修改时间:</b> 2017年 03月08日 17:04<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

public class GatherPresenter extends BasePresenter<GatherContract.View>
        implements GatherContract.Presenter {

    @Inject
    PayService payService;

    public GatherPresenter(GatherContract.View View) {
        super(View);
    }

    @Override
    public void injectNetComponent(NetComponent netComponent) {
        netComponent.inject(this);
    }

    @Override
    public void pay(PayForm payForm) {
        Subscription subscribe = payService.pay(payForm)
                .compose(new ResponseTransformer<>())
                .subscribe(new Subscriber<PayInfo>() {
                    @Override
                    public void onCompleted() {
                        view.toast("记录销售信息成功!");
                        view.finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showError(e);
                    }

                    @Override
                    public void onNext(PayInfo payInfo) {

                    }
                });
        compositeSubscription.add(subscribe);
    }
}
