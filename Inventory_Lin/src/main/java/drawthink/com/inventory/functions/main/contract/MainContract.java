package drawthink.com.inventory.functions.main.contract;

import drawthink.com.inventory.functions.BaseView;
import drawthink.com.inventory.functions.IBasePresenter;

/**
 * <b>类名称：</b> MainContract <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年02月20日 16:58<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public interface MainContract {
    interface View extends BaseView{

        void showUser(String userName, String userId, String userMobile, String storeName, long startDate, long endDate);
    }

    interface Presenter extends IBasePresenter{

        void loadUser();

        void loadSellerByShop(int shopId);
    }
}
