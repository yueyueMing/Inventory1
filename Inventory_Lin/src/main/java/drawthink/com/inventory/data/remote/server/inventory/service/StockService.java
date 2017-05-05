package drawthink.com.inventory.data.remote.server.inventory.service;

import java.util.List;

import drawthink.com.inventory.data.remote.server.inventory.vo.LocalResponse;
import drawthink.com.inventory.data.remote.server.inventory.vo.stock.ShopStockInfo;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * <b>类名称：</b> StockService <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年03月02日 17:15<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

/*
商品库存统计信息
 */
public interface StockService {

    String USER_SERVICE_URL = "inventory";

    @GET(USER_SERVICE_URL + "/statistics/{shopId}")
    Observable<Response<LocalResponse<List<ShopStockInfo>>>> getShopStock(@Path("shopId") int shopId,
                                                                          @Query("startDate") String startDate,
                                                                          @Query("endDate") String endDate,

                                                                          @Query("productCode") String productCode,
                                                                          @Query("productName") String productName);



}
