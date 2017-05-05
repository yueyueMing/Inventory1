package drawthink.com.inventory.data.remote.server.inventory.service;


import java.util.List;

import drawthink.com.inventory.data.remote.server.inventory.vo.LocalResponse;
import drawthink.com.inventory.data.remote.server.inventory.vo.pay.Seller;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * <b>类名称：</b> SellerService<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b> 马文礼 <br/>
 * <b>修改时间:</b> 2017年 03月08日 10:50<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

public interface SellerService {
    String SELLER_SERVICE_URL = "shop";

    @GET(SELLER_SERVICE_URL+"/seller/{shopId}")
    Observable<Response<LocalResponse<List<Seller>>>> getSeller(@Path("shopId") String shopId);
}
