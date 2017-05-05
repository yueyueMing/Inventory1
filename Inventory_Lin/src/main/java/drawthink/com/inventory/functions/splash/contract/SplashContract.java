package drawthink.com.inventory.functions.splash.contract;


import drawthink.com.inventory.data.remote.server.inventory.vo.login.User;
import drawthink.com.inventory.functions.BaseView;
import drawthink.com.inventory.functions.IBasePresenter;

public interface SplashContract {
    interface View extends BaseView {


        void isFristComing(User user);

        void startLogin();
//        void showSaler(List<ShopSeller> shopSellers);


    }

    interface Presenter extends IBasePresenter {
        void autoLogin(String mobile);
//        void  loadSeller(String shopId);

    }
}
