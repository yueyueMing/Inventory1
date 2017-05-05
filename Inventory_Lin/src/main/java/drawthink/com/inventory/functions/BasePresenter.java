package drawthink.com.inventory.functions;



import drawthink.com.inventory.InventoryApplication;
import drawthink.com.inventory.component.NetComponent;
import drawthink.com.inventory.data.remote.server.inventory.vo.pay.PayForm;
import rx.subscriptions.CompositeSubscription;

/**
 * <b>类名称：</b> basePresenter <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年09月19日 下午3:05<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public abstract class BasePresenter<T extends BaseView> implements IBasePresenter {

    public T view ;

    protected CompositeSubscription compositeSubscription;

    public BasePresenter(T View) {
        NetComponent netComponent = InventoryApplication.get()
                .getAppComponent()
                .netComponent();
        injectNetComponent(netComponent);
        bindView(View);
        compositeSubscription = new CompositeSubscription();
    }

    private void bindView(T view){
        this.view = view;
    }

    public abstract void injectNetComponent(NetComponent netComponent);


    public void onDestroy(){
        compositeSubscription.unsubscribe();
    }

    public void showError(Throwable throwable){
        throwable.printStackTrace();
        view.toast(throwable.getMessage());
    }


}
