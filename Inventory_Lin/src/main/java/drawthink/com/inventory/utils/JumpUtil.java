package drawthink.com.inventory.utils;

import android.app.Activity;
import android.content.Intent;

import java.io.Serializable;

/**
 * <b>类名称：</b> JumpUtil <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2016年07月18日 10:13<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

public class JumpUtil {
    private Intent intent;
    private Activity mCtx;

    public JumpUtil(Activity ctx, Class dst) {
        mCtx = ctx;
        intent = new Intent(ctx, dst);
    }

    public JumpUtil extra(String key, int value) {
        intent.putExtra(key, value);
        return this;
    }

    public JumpUtil extra(String key, String value) {
        intent.putExtra(key, value);
        return this;
    }

    public JumpUtil extra(String key, Serializable value) {
        intent.putExtra(key, value);
        return this;
    }

    public void start() {
        mCtx.startActivity(intent);
    }

    public void startForResult(int requestCode) {
        mCtx.startActivityForResult(intent, requestCode);
    }
}
