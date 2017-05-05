package drawthink.com.inventory.functions.sell.contract;

import drawthink.com.inventory.data.remote.server.inventory.vo.pay.PayForm;
import drawthink.com.inventory.functions.BaseView;
import drawthink.com.inventory.functions.IBasePresenter;

/**
 * <b>类名称：</b> GatherContract<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b> 马文礼 <br/>
 * <b>修改时间:</b> 2017年 03月08日 16:59<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

public interface GatherContract {
    interface View extends BaseView{

        void finish();

    }
    interface Presenter extends IBasePresenter{

        void pay(PayForm payForm);
    }
}
