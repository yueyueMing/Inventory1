package drawthink.com.inventory.functions.main.contract;

import java.util.List;

import drawthink.com.inventory.data.remote.server.inventory.vo.login.User;
import drawthink.com.inventory.functions.BaseView;
import drawthink.com.inventory.functions.IBasePresenter;

/**
 * <b>类名称：</b> ShopSelectContract <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年03月01日 14:53<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public interface ShopSelectContract {

    interface View extends BaseView{

        void showUserStore(List<User.StoresBean> userSpUtils);
    }

    interface Presenter extends IBasePresenter{

        void loadUserShops();
    }
}
