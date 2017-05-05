package drawthink.com.inventory.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

/**
 * <b>类名称：</b> BitmapUtil <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年03月03日 11:32<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public class BitmapUtil {
    public static Bitmap getBitmap(InputStream inputStream){

        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);



        return  bitmap;
    }
}
