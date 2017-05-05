package drawthink.com.inventory.utils;

import com.blankj.utilcode.utils.EncodeUtils;
import com.blankj.utilcode.utils.StringUtils;

/**
 * <b>类名称：</b> AuthUtils <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年08月19日 下午3:53<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class AuthUtils {

    public static String des3Encode(String data) {
        if(StringUtils.isSpace(data)){
            return "";
        }
        try {
            return EncodeUtils.base64Encode2String(
                    Des3.des3EncodeCBC(
                            Des3.secretKey,
                            Des3.iv,
                            data.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String des3Decode(String data) {
        if(StringUtils.isSpace(data)){
            return "";
        }
        try {
            return new String(
                    Des3.des3DecodeCBC(
                            Des3.secretKey,
                            Des3.iv,
                            EncodeUtils.base64Decode(data)));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
