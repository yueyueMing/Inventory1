package drawthink.com.inventory.functions.statistics.contract;

import java.util.List;

import drawthink.com.inventory.data.remote.server.inventory.vo.shop.ShopSeller;
import drawthink.com.inventory.data.remote.server.inventory.vo.statistics.Statistic;
import drawthink.com.inventory.functions.BaseView;
import drawthink.com.inventory.functions.IBasePresenter;

/**
 * <b>类名称：</b> StaticContract <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年02月23日 9:13<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public interface StaticContract {
    interface View extends BaseView {
        void showStaticInfo(Statistic statistic);
        void showSaler(List<ShopSeller> ShopSellers);


    }

    interface Presenter extends IBasePresenter {
        void loadSaller(int shopId);


        void loadStaticInfo(String shopId
                , String startDate
                , String endDate
                , int sellerId
                , String productCode
                , String productName);
    }
}
