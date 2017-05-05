package drawthink.com.inventory.data.remote.server.inventory.service;

import java.util.List;

import drawthink.com.inventory.data.remote.server.inventory.vo.LocalResponse;
import drawthink.com.inventory.data.remote.server.inventory.vo.shop.ShopSeller;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * <b>类名称：</b> ShopService <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年03月02日 16:58<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public interface ShopService {

    String OTHER_SERVICE_URL = "shop";

/*
获取店铺所辖的管理员
 */
    @GET(OTHER_SERVICE_URL + "/seller/{shopId}")
    Observable<Response<LocalResponse<List<ShopSeller>>>>getShopForSaler(@Path("shopId") int shopId);



}
