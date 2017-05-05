package drawthink.com.inventory.functions.stock.contract;

import java.util.List;

import drawthink.com.inventory.data.remote.server.inventory.vo.stock.ShopStockInfo;
import drawthink.com.inventory.functions.BaseView;
import drawthink.com.inventory.functions.IBasePresenter;

/**
 * <b>类名称：</b> StockContract <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年03月06日 11:38<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public interface StockContract {
    interface View extends BaseView{
        void showStockInfo(List<ShopStockInfo> shopStockInfos);

    }

    interface  Presenter extends IBasePresenter{

        void loadStockInfo(int shopId, String startDate, String endDate, String productCode, String productName);
    }
}
