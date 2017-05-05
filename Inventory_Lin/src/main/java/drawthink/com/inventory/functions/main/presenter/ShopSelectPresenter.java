package drawthink.com.inventory.functions.main.presenter;

import javax.inject.Inject;

import drawthink.com.inventory.component.NetComponent;
import drawthink.com.inventory.data.lcoal.preference.UserSpUtils;
import drawthink.com.inventory.functions.BasePresenter;
import drawthink.com.inventory.functions.main.contract.ShopSelectContract;

/**
 * <b>类名称：</b> ShopSelectPresenter <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年03月01日 14:54<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

public class ShopSelectPresenter
        extends BasePresenter<ShopSelectContract.View>
        implements ShopSelectContract.Presenter {

    @Inject
    UserSpUtils userSpUtils;

    public ShopSelectPresenter(ShopSelectContract.View View) {
        super(View);
    }

    @Override
    public void injectNetComponent(NetComponent netComponent) {
        netComponent.inject(this);
    }

    @Override
    public void loadUserShops() {
        view.showUserStore(userSpUtils.getUserStores());
    }
}
