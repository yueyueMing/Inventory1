package drawthink.com.inventory.functions.cash.contract;

import java.util.List;

import drawthink.com.inventory.data.remote.server.inventory.service.form.CashForm;
import drawthink.com.inventory.data.remote.server.inventory.vo.shop.ShopSeller;
import drawthink.com.inventory.functions.BaseView;
import drawthink.com.inventory.functions.IBasePresenter;

/**
 * <b>类名称：</b> AddCashContract <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年03月06日 14:06<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public interface AddCashContract {
    interface View extends BaseView {
        void showSaler(List<ShopSeller> ShopSellers);

        void showSellMoney(Double calcCash);

        void finish();
    }

    interface Presenter extends IBasePresenter {
        void loadSeller(int shopId);
        void loadMoney(String date);

        void addCash(CashForm cashForm);
    }
}
