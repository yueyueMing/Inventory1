package drawthink.com.inventory.functions.login.contract;

import drawthink.com.inventory.functions.BaseView;
import drawthink.com.inventory.functions.IBasePresenter;

/**
 * <b>类名称：</b> LoginContract <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年02月23日 9:22<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public interface LoginContract {
     interface View extends BaseView{

         void loginComplete();
     }
    interface  Presenter extends IBasePresenter{

        void getValidCode(String s);

        void login(String mobile, String validCode);

    }
}
