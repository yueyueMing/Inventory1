package drawthink.com.inventory.functions.main.presenter;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.utils.SPUtils;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import drawthink.com.inventory.InventoryApplication;
import drawthink.com.inventory.component.NetComponent;
import drawthink.com.inventory.data.lcoal.preference.PreferenceConst;
import drawthink.com.inventory.data.lcoal.preference.UserSpUtils;
import drawthink.com.inventory.data.remote.func.ResponseTransformer;
import drawthink.com.inventory.data.remote.server.inventory.service.ShopService;
import drawthink.com.inventory.data.remote.server.inventory.vo.login.User;
import drawthink.com.inventory.data.remote.server.inventory.vo.shop.ShopSeller;
import drawthink.com.inventory.functions.BasePresenter;
import drawthink.com.inventory.functions.main.contract.MainContract;
import rx.Subscriber;

/**
 * <b>类名称：</b> MainPresenter <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年02月20日 16:59<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public class MainPresenter
        extends BasePresenter<MainContract.View>
        implements MainContract.Presenter {
    @Inject
    UserSpUtils spUtils;

    @Inject
    @Named("app")
    SPUtils appSp;

    @Inject
    ShopService shopService;

    public MainPresenter(MainContract.View View) {
        super(View);
    }

    @Override
    public void injectNetComponent(NetComponent netComponent) {
        netComponent.inject(this);
    }

    @Override
    public void loadUser() {
        List<User.StoresBean> userStores = spUtils.getUserStores();
        User.StoresBean storesBean = userStores.isEmpty() ? new User.StoresBean() :userStores.get(0);
        if(storesBean.getId() != 0) {
            storesBean.setChecked(true);
            InventoryApplication.setShopId(storesBean.getId());
            InventoryApplication.setShopName(storesBean.getStoreName());
            InventoryApplication.setPayImage(storesBean.getPayImage());
            spUtils.updateStoreChecked(userStores);
            loadSellerByShop(storesBean.getId());
        }else{
            storesBean.setStoreName("无店铺");
        }
        String userName = spUtils.getUserName();
        String userId = spUtils.getUserId();
        String userMobile = spUtils.getUserMobile();
        view.showUser(userName,userId,userMobile,storesBean.getStoreName(),storesBean.getStartDate(),storesBean.getEndDate());


    }

    @Override
    public void loadSellerByShop(int shopId) {
        shopService.getShopForSaler(shopId)
                .compose(new ResponseTransformer<>())
                .subscribe(new Subscriber<List<ShopSeller>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        showError(e);
                    }

                    @Override
                    public void onNext(List<ShopSeller> ShopSellers) {
                        appSp.putString(PreferenceConst.SHOP_SELLER , JSON.toJSONString(ShopSellers));

                      List<ShopSeller> shopSellers1 = JSON.parseArray(appSp.getString(PreferenceConst.SHOP_SELLER), ShopSeller.class);
                    }
                });
    }


}
