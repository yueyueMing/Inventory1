package drawthink.com.inventory.data.lcoal.preference;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.utils.SPUtils;

import java.util.List;

import drawthink.com.inventory.data.remote.server.inventory.vo.login.User;
import drawthink.com.inventory.data.remote.server.inventory.vo.shop.ShopSeller;
import drawthink.com.inventory.utils.AuthUtils;

/**
 * <b>类名称：</b> UserSpUtils <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年02月13日 15:48<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class UserSpUtils {

    private static final String USER_MOBILE = "userMobile";

    private static final String USER_NAME = "userName";

    private static final String USER_ID = "userId";
    private static final String USER_STORES_JSON = "stores";
    private static final String seller= "seller";
    private static final String sellId= "sellId";

    private SPUtils spUtils;

    public UserSpUtils(SPUtils spUtils) {
        this.spUtils = spUtils;
    }

    public String getUserId(){
        return AuthUtils.des3Decode(spUtils.getString(USER_ID));
    }

    public String getUserMobile(){
        return AuthUtils.des3Decode(spUtils.getString(USER_MOBILE));
    }

    public String getUserName(){
        return AuthUtils.des3Decode(spUtils.getString(USER_NAME));
    }



    public List<User.StoresBean> getUserStores(){
        return JSON.parseArray(AuthUtils.des3Decode(spUtils.getString(USER_STORES_JSON, "")), User.StoresBean.class);
    }
    public void saveUser(User user){
        spUtils.putString(USER_MOBILE, AuthUtils.des3Encode(user.getMobile()));
        spUtils.putString(USER_NAME, AuthUtils.des3Encode(user.getName()));
        spUtils.putString(USER_ID, AuthUtils.des3Encode(user.getId()));
        spUtils.putString(USER_STORES_JSON , AuthUtils.des3Encode(JSON.toJSONString(user.getStores())));
    }
    public  void saveSeller(ShopSeller shopSellers){

        spUtils.putString(seller, shopSellers.getName());
        spUtils.putInt(sellId, shopSellers.getId());


    }


    public void updateStoreChecked(List<User.StoresBean> storesBeanList){
        spUtils.putString(USER_STORES_JSON , AuthUtils.des3Encode(JSON.toJSONString(storesBeanList)));

    }


    public boolean hasUserId() {
        return spUtils.contains(USER_ID);
    }

    public void clear() {
        spUtils.clear();
    }
}
