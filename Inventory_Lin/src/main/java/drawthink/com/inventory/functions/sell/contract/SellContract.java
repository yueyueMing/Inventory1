package drawthink.com.inventory.functions.sell.contract;

import java.util.List;

import drawthink.com.inventory.data.remote.server.inventory.vo.product.ProductAll;
import drawthink.com.inventory.data.remote.server.inventory.vo.product.ProductCategory;
import drawthink.com.inventory.functions.BaseView;
import drawthink.com.inventory.functions.IBasePresenter;

/**
 * <b>类名称：</b> SellContract <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年03月02日 15:02<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public interface SellContract {
    interface View extends BaseView {
        void showProductCategoryInfo(List<ProductCategory> categories);

        void showAllProductInfo(List<ProductAll> productAlls);

    }

    interface Presenter extends IBasePresenter {
        void loadCategoryInfo();

        void loadAllProductInfo(int shopId,String category);


    }
}
