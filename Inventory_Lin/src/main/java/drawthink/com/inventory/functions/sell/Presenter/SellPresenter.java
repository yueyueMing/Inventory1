package drawthink.com.inventory.functions.sell.Presenter;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import drawthink.com.inventory.component.NetComponent;
import drawthink.com.inventory.data.remote.func.ResponseTransformer;
import drawthink.com.inventory.data.remote.server.inventory.service.ProductService;
import drawthink.com.inventory.data.remote.server.inventory.vo.product.ProductCategory;
import drawthink.com.inventory.functions.BasePresenter;
import drawthink.com.inventory.functions.sell.contract.SellContract;
import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;

/**
 * <b>类名称：</b> SellPresenter <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年03月02日 15:00<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public class SellPresenter extends
        BasePresenter<SellContract.View>
        implements SellContract.Presenter {

    @Inject
    ProductService productService;

    public SellPresenter(SellContract.View View) {
        super(View);
    }

    @Override
    public void injectNetComponent(NetComponent netComponent) {
        netComponent.inject(this);

    }

    @Override
    public void loadCategoryInfo() {
        Subscription subscribe = productService.getProductCategory()
                .compose(new ResponseTransformer<>())
                .subscribe(productCategories -> {
                    Collections.sort(productCategories, (o1, o2) -> o1.getSortNum() - o2.getSortNum());
                    ProductCategory productCategory = new ProductCategory();
                    productCategory.setSelected(true);
                    productCategory.setId("all");
                    productCategory.setSortNum(0);
                    productCategory.setTitle("全部");
                    productCategories.add(0, productCategory);
                    view.showProductCategoryInfo(productCategories);
                }, this::showError);
        compositeSubscription.add(subscribe);
    }


    @Override
    public void loadAllProductInfo(int shopId, String category) {
        view.showWait();
        Subscription subscribe = productService.getProductInfo(shopId, category)
                .compose(new ResponseTransformer<>())
                .subscribe(productAll -> {
                            view.showAllProductInfo(productAll);
                        }
                        , throwable -> {
                            showError(throwable);
                            view.hideWait(null);
                        },()->{view.hideWait(null);});
        compositeSubscription.add(subscribe);
    }


}
