package drawthink.com.inventory.functions.sell.contract;

import java.util.List;

import drawthink.com.inventory.data.remote.server.inventory.vo.pay.PayForm;
import drawthink.com.inventory.data.remote.server.inventory.vo.pay.Seller;
import drawthink.com.inventory.data.remote.server.inventory.vo.product.ProductDetail;
import drawthink.com.inventory.functions.BaseView;
import drawthink.com.inventory.functions.IBasePresenter;

/**
 * <b>类名称：</b> SellDetailContract <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年03月03日 16:03<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public interface SellDetailContract {
    interface View extends BaseView{
        void showProductDetails(ProductDetail data);
        void showSeller(List<Seller> sellers);
    }
    interface  Presenter extends IBasePresenter{

        void loadProductDetails(int productId);
        void loadSeller(String shopId);

        void pay(PayForm form);

    }
}
