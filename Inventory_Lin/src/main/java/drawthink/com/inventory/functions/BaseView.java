package drawthink.com.inventory.functions;

import drawthink.com.inventory.widget.WaitScreen;

/**
 * <b>类名称：</b> BaseView <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 16-9-28 下午6:01<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

public interface BaseView {
    void showWait();

    void showWait(String message);

    void hideWait(WaitScreen.OnAnimationEnd onAnimationEnd);

    void closeWait();

    void toast(String message);
}
