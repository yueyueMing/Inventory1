package drawthink.com.inventory.functions.cash.contract;


import java.util.List;

import drawthink.com.inventory.data.remote.server.inventory.vo.cash.CashDate;
import drawthink.com.inventory.data.remote.server.inventory.vo.shop.ShopSeller;
import drawthink.com.inventory.functions.BaseView;
import drawthink.com.inventory.functions.IBasePresenter;

/**
 * <b>类名称：</b> CashContract <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年02月23日 9:12<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public interface CashContract {
    public interface View extends BaseView {
         void showCashInfo(CashDate cashDate);
        void showSaler(List<ShopSeller> ShopSellers);

    }
    public interface Presenter extends IBasePresenter{
        void loadCashInfo(String shopId,String startDate,String endDate,String sellerId,String payType);
        void loadSaller(int shopId);
    }
}
